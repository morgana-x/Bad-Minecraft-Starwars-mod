package morgana.newmobs.entity;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import static morgana.newmobs.items.RegisterItems.BLASTER_BASIC;

/*
 * Our Cube Entity extends MobEntityWithAi, which extends MobEntity, which extends LivingEntity.
 *
 * LivingEntity has health and can deal damage.
 * MobEntity has movement controls and AI capabilities.
 * MobEntityWithAi has pathfinding favor and slightly tweaked leash behavior.
 */
public class RebelTrooperEntity extends HumanEntity {

    public RebelTrooperEntity(EntityType<? extends HumanEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public boolean canTarget(EntityType<?> type) {
        return type != this.getType();
    }


    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(BLASTER_BASIC));
        this.equipStack(EquipmentSlot.CHEST, new ItemStack((Items.LEATHER_CHESTPLATE)));
        this.equipStack(EquipmentSlot.HEAD, new ItemStack((Items.LEATHER_HELMET)));
        this.equipStack(EquipmentSlot.LEGS, new ItemStack((Items.LEATHER_LEGGINGS)));
        this.equipStack(EquipmentSlot.FEET, new ItemStack((Items.LEATHER_BOOTS)));
    }


    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        if (allowDrops){
            this.dropItem(BLASTER_BASIC);
        }

    }

    @Override
    protected void initCustomGoals()
    {
        this.targetSelector.add(2, new ActiveTargetGoal(this, StormTrooperEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal(this, HostileEntity.class, true));
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

}
