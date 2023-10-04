package morgana.newmobs;

import morgana.newmobs.entity.HumanEntity;
import morgana.newmobs.entity.CitizenEntity;
import morgana.newmobs.entity.RebelTrooperEntity;
import morgana.newmobs.entity.StormTrooperEntity;
import morgana.newmobs.entity.LaserEntity;
import morgana.newmobs.items.BlasterBase;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import morgana.newmobs.items.RegisterItems;

import static morgana.newmobs.items.RegisterItems.*;
import static net.minecraft.item.Items.register;

public class NewMobStuff implements ModInitializer {

    public static final Identifier BLASTER_SOUND_ID = new Identifier("newmobs:blaster");
    public static SoundEvent BLASTER_SOUND_EVENT = SoundEvent.of(BLASTER_SOUND_ID);
    public static final EntityType<HumanEntity> HUMAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier("newmobs", "human"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HumanEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build()
    );
    public static final EntityType<RebelTrooperEntity> REBEL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier("newmobs", "rebel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RebelTrooperEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build()
    );
    public static final EntityType<StormTrooperEntity> STORMTROOPER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier("newmobs", "stormtrooper"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, StormTrooperEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build()
    );

    public static final EntityType<CitizenEntity> CITIZEN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier("newmobs", "citizen"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CitizenEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build()
    );

    public static final EntityType<LaserEntity> LASER = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("newmobs", "spawned_laser"),
            FabricEntityTypeBuilder.<LaserEntity>create(SpawnGroup.MISC, LaserEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
            );



    /*public static final Item HUMAN_SPAWN_EGG = new SpawnEggItem(HUMAN, 0xc4c4c4, 0xadadad, new FabricItemSettings());

    public static final Item REBEL_SPAWN_EGG = new SpawnEggItem(REBEL, 0xc4c4c4, 0xadadad, new FabricItemSettings());

    public static final Item STORMTROOPER_SPAWN_EGG = new SpawnEggItem(STORMTROOPER, 0xc4c4c4, 0xadadad, new FabricItemSettings());
*/

    public static Item register (Identifier id, Item item){ // Item register(Identifier id, Item item) {
            return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }
    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item)Registry.register(Registries.ITEM, key, item);
    }
    @Override
    public void onInitialize() {
        /*
         * Register our Cube Entity's default attributes.
         * Attributes are properties or stats of the mobs, including things like attack damage and health.
         * The game will crash if the entity doesn't have the proper attributes registered in time.
         *
         * In 1.15, this was done by a method override inside the entity class.
         * Most vanilla entities have a static method (eg. ZombieEntity#createZombieAttributes) for initializing their attributes.
         */
        RegisterItems.register();

        Registry.register(Registries.SOUND_EVENT, BLASTER_SOUND_ID, BLASTER_SOUND_EVENT);
        FabricDefaultAttributeRegistry.register(HUMAN, HumanEntity.createHumanAttributes());
        FabricDefaultAttributeRegistry.register(CITIZEN, CitizenEntity.createHumanAttributes());
        FabricDefaultAttributeRegistry.register(REBEL, RebelTrooperEntity.createHumanAttributes());
        FabricDefaultAttributeRegistry.register(STORMTROOPER, StormTrooperEntity.createHumanAttributes());




        /*public static Item register(String id, Item item) {
            return register(new Identifier(id), item);
        }*/





        // THIS IS CAUSING CRAHES FIX
        Item HUMAN_SPAWN_EGG = register((Identifier) new Identifier("newmobs", "human"), (Item)(new SpawnEggItem(HUMAN, 56063, 44543, new Item.Settings())));
        Item CITIZEN_SPAWN_EGG = register((Identifier) new Identifier("newmobs", "citizen"), (Item)(new SpawnEggItem(CITIZEN, 56063, 44543, new Item.Settings())));

        Item REBEL_SPAWN_EGG = register((Identifier) new Identifier("newmobs", "rebel"), (Item)(new SpawnEggItem(REBEL, 56063, 44543, new Item.Settings())));

        Item STORMTROOPER_SPAWN_EGG = register((Identifier) new Identifier("newmobs", "stormtrooper"), (Item)(new SpawnEggItem(STORMTROOPER, 56063, 44543, new Item.Settings())));



    }
}
