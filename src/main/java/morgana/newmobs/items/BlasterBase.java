package morgana.newmobs.items;

import morgana.newmobs.entity.LaserEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

import static morgana.newmobs.NewMobStuff.BLASTER_SOUND_EVENT;
import static morgana.newmobs.NewMobStuff.LASER;

public class BlasterBase extends Item {
    public static final Predicate<ItemStack> LASER_PROJECTILES = (stack) -> {
        return stack.isIn(ItemTags.ARROWS); // CHANGE
    };
    public BlasterBase(Item.Settings settings) {
        super(settings);
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
    public LaserEntity createLaserProjectile(LivingEntity entity){
        return new LaserEntity( LASER, entity.getWorld(), entity);
    }
    public void shoot( World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }

        LaserEntity spawnedLaser = this.createLaserProjectile(user);

        spawnedLaser.setPosition(user.getEyePos());
        spawnedLaser.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1 * 3.0F, 1.0F);
        spawnedLaser.setRange( spawnedLaser.defaultRange);

        user.playSound(BLASTER_SOUND_EVENT, 1.0F, 1.0F / (user.getRandom().nextFloat() * 0.4F + 0.8F));

        world.spawnEntity(spawnedLaser);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        shoot(user.getWorld(), user);
        return TypedActionResult.success(user.getStackInHand(hand));
        /*ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            shoot(itemStack, user.getWorld(), user);
            //return TypedActionResult.consume(itemStack);
            return TypedActionResult.success(user.getStackInHand(hand));
        }*/
        //shoot(ItemStack.EMPTY, user.getWorld(), user);
        //return TypedActionResult.consume(ItemStack.EMPTY);
    }
}
