package morgana.newmobs.entity;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

import static morgana.newmobs.NewMobStuff.LASER;

public class LaserEntity extends ThrownEntity {
    @Nullable
    private UUID ownerUuid;
    @Nullable
    private Entity owner;
    private boolean leftOwner;
    private boolean shot;

    public float defaultRange = 100;
    public int life;
    public LaserEntity(World world, double x, double y, double z) {
        super(LASER, world);
        this.setPosition(x,y,z);
    }

    public LaserEntity(EntityType<LaserEntity> type,World world, LivingEntity owner) {
        super(type, owner, world);
        this.owner = owner;
    }

    public LaserEntity(EntityType<LaserEntity> entityEntityType, World world) {
        super(entityEntityType, world);


    }
    public void setLife( int life)
    {
        this.life = life;
    }
    public void setRange(float range)
    {
        var ticksToLive = (int)(range / getVelocity().length());
        setLife(ticksToLive);
    }
    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
            if (hitResult.getType() != HitResult.Type.MISS)
                this.setPosition(hitResult.getPos());
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                var blockHit = (BlockHitResult) hitResult;

                var blockPos = blockHit.getBlockPos();

                var state = getWorld().getBlockState(blockPos);
            }
            else if (hitResult.getType() == HitResult.Type.ENTITY)
            {
                var entityHit = (EntityHitResult)hitResult;
                var entity = entityHit.getEntity();
                damage(entity);
            }

        this.discard();
    }
    protected void damage(Entity target)
    {

        target.damage(getDamageSource(this, this.getOwner()), 2); //(float)(double)damageFunction.apply((double)2));
    }

    public static DamageSource getDamageSource(Entity projectile, Entity attacker)
    {
        return projectile.getDamageSources().mobProjectile(projectile, (LivingEntity) attacker);
        //return new DamageSource( DamageTypes.ARROW.getRegistry() /*.entryOf(SwgDamageTypes.BLASTER)*/, projectile, attacker ); //(attacker.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(SwgDamageTypes.BLASTER), projectile, attacker);
    }


    @Override
    public void tick()
    {
        if (!getWorld().isClient && this.age > this.life)
        {
            this.discard();
            return;
        }

        /*if (getWorld().isClient && (this.age > (this.life / 1.25f)))
        {
            var vec = getPos();
            var vel = getVelocity();
            var n = 10;
            var dVel = vel.multiply(1f / n);

            for (var i = 0; i < n; i++)
            {
                var dx = 0.01 * getWorld().random.nextGaussian();
                var dy = 0.01 * getWorld().random.nextGaussian();
                var dz = 0.01 * getWorld().random.nextGaussian();

                getWorld().addParticle(ParticleTypes.FLAME, vec.x, vec.y, vec.z, dx, dy, dz);

                vec = vec.add(dVel);
            }
        }*/

        super.tick();
    }
}
