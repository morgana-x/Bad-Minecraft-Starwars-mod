package morgana.newmobs.entity;
import morgana.newmobs.entity.ai.goal.BlasterAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

import static morgana.newmobs.NewMobStuff.BLASTER_SOUND_EVENT;
import static morgana.newmobs.NewMobStuff.LASER;
import static morgana.newmobs.items.RegisterItems.BLASTER_BASIC;

/*
 * Our Cube Entity extends MobEntityWithAi, which extends MobEntity, which extends LivingEntity.
 *
 * LivingEntity has health and can deal damage.
 * MobEntity has movement controls and AI capabilities.
 * MobEntityWithAi has pathfinding favor and slightly tweaked leash behavior.
 */
public class HumanEntity extends PathAwareEntity implements RangedAttackMob { //, Targeter {

    //private final BowAttackGoal<HumanEntity> bowAttackGoal = new BowAttackGoal( this, 1.0, 20, 15.0F);
    private final BlasterAttackGoal<HumanEntity> blasterAttackGoal = new BlasterAttackGoal(this, 1.0, 20, 15.0F);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2, false) {
        public void stop() {
            super.stop();
            HumanEntity.this.setAttacking(false);
        }

        public void start() {
            super.start();
            HumanEntity.this.setAttacking(true);
        }

    };
    public HumanEntity(EntityType<? extends HumanEntity> entityType, World world) {
        super(entityType, world);
        this.initGoals();
        Random rnd = Random.create();
        BlockPos p = new BlockPos(0,0,0);
        this.initEquipment(rnd, world.getLocalDifficulty(p));
        this.updateAttackType();

    }
    public void updateAttackType() {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            this.goalSelector.remove(this.meleeAttackGoal);
            this.goalSelector.remove(this.blasterAttackGoal);
            ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, BLASTER_BASIC));
            if (itemStack.isOf(BLASTER_BASIC)) {
                int i = 5;

                this.blasterAttackGoal.setAttackInterval(i);
                this.goalSelector.add(4, this.blasterAttackGoal);
            } else {
                this.goalSelector.add(4, this.meleeAttackGoal);
            }

        }
    }

    public void tick()
    {
        super.tick();
    }
    public void tickMovement()
    {
        super.tickMovement();
    }
    protected void initDataTracker() {
        super.initDataTracker();
    }
    @Override
    public boolean canTarget(EntityType<?> type) {
        return type != this.getType();
    }

    public LaserEntity createLaserProjectile(LivingEntity entity){
        return new LaserEntity( LASER, entity.getWorld(), entity);
    }
    @Override
    public void shootAt(LivingEntity target, float pullProgress)
    {
        this.shootAtNew(target);
    }
    public void shootAtNew(LivingEntity target) { //, float pullProgress) {
        LaserEntity spawnedLaser = this.createLaserProjectile(this);
        spawnedLaser.setPosition(this.getEyePos());
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - spawnedLaser.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        //spawnedLaser.setVelocity(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        spawnedLaser.setVelocity(d, e + g * 0.20000000298023224, f, 1F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        spawnedLaser.setRange( spawnedLaser.defaultRange);
        //this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.playSound(BLASTER_SOUND_EVENT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(spawnedLaser);
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
    }
    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }


    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        /*if (allowDrops){
            this.dropItem(Items.BOW);
        }*/
        //super.dropEquipment(source, lootingMultiplier, allowDrops);

    }
    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }
    @Override
    public boolean cannotDespawn() {
        return this.hasVehicle();
    }
    @Override
    protected boolean isDisallowedInPeaceful() {
        return false;
    }

    protected void initGoals() {
        //this.goalSelector.add(2, new AvoidSunlightGoal(this));
        //this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0));
        //this.goalSelector.add(3, new FleeEntityGoal(this, WolfEntity.class, 6.0F, 1.0, 1.2));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, this.getClass(), 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        //this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        //this.targetSelector.add(3, new ActiveTargetGoal(this, SkeletonEntity.class, true));
        //this.targetSelector.add(3, new ActiveTargetGoal(this, HostileEntity.class, true));
        this.initCustomGoals();
    }
    protected void initCustomGoals()
    {
        //this.targetSelector.add(2, new ActiveTargetGoal(this, HostileEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createHumanAttributes() {
        return PathAwareEntity.createLivingAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5).add(EntityAttributes.GENERIC_ATTACK_SPEED,2);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_PLAYER_DEATH;}

    SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

}
