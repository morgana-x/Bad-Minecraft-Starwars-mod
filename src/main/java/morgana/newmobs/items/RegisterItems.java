package morgana.newmobs.items;
import morgana.newmobs.entity.StormTrooperEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import morgana.newmobs.materials.stormtrooperArmorMaterial;
import net.minecraft.util.Pair;

import java.util.List;

import static morgana.newmobs.NewMobStuff.*;


public class RegisterItems {
    public static final ArmorMaterial stormtrooperArmorMaterial = new stormtrooperArmorMaterial();
    // ERRORS BELOW
    public static final Item STORMTROOPER_HELMET = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.HELMET, new Item.Settings());
    public static final Item STORMTROOPER_CHESTPLATE = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    public static final Item STORMTROOPER_LEGGINGS = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.LEGGINGS, new Item.Settings());
    public static final Item STORMTROOPER_BOOTS = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.BOOTS, new Item.Settings());

    public static final SpawnEggItem STORMTROOPER_SPAWNEGG = new SpawnEggItem(STORMTROOPER, 2,1, new Item.Settings());
    public static final SpawnEggItem HUMAN_SPAWNEGG = new SpawnEggItem(HUMAN, 2,1, new Item.Settings());
    public static final SpawnEggItem REBEL_SPAWNEGG = new SpawnEggItem(REBEL, 2,1, new Item.Settings());
    public static final SpawnEggItem CITIZEN_SPAWNEGG = new SpawnEggItem(CITIZEN, 2,1, new Item.Settings());
    public static final BlasterBase BLASTER_BASIC = new BlasterBase(new FabricItemSettings());

    public static final MilkBucketItem BLUE_MILK = new MilkBucketItem(new Item.Settings());

    //public static final FoodComponent ANAKIN_NABOO_PEAR = new FoodComponent.Builder().build();  //2, 1, false, true, true, new java.util.List<Pair<StatusEffectInstance, Float>>());

    public static final ItemGroup STARWARS_GROUP_ARMOR = FabricItemGroup.builder()
            .icon(() -> new ItemStack(STORMTROOPER_HELMET))
            .displayName(Text.translatable("itemGroup.newmobs.starwars_group_armor"))
            .entries((context, entries) -> {
                entries.add(STORMTROOPER_HELMET);
                entries.add(STORMTROOPER_CHESTPLATE);
                entries.add(STORMTROOPER_LEGGINGS);
                entries.add(STORMTROOPER_BOOTS);
            })

            .build();
    public static final ItemGroup STARWARS_GROUP_WEAPONS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BLASTER_BASIC))
            .displayName(Text.translatable("itemGroup.newmobs.starwars_group_weapons"))
            .entries((context, entries) -> {
                entries.add(BLASTER_BASIC);
            })

            .build();
    public static final ItemGroup STARWARS_GROUP_ITEMS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BLUE_MILK))
            .displayName(Text.translatable("itemGroup.newmobs.starwars_group_items"))
            .entries((context, entries) -> {
                entries.add(BLUE_MILK);
                entries.add(HUMAN_SPAWNEGG);
                entries.add(STORMTROOPER_SPAWNEGG);
                entries.add(REBEL_SPAWNEGG);
                entries.add(CITIZEN_SPAWNEGG);
            })

            .build();
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_helmet"), STORMTROOPER_HELMET);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_chestplate"), STORMTROOPER_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_leggings"), STORMTROOPER_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_boots"), STORMTROOPER_BOOTS);

        Registry.register(Registries.ITEM, new Identifier("newmobs", "blasterbasic"), BLASTER_BASIC);

        Registry.register(Registries.ITEM, new Identifier("newmobs", "blue_milk_bucket"), BLUE_MILK);

        Registry.register(Registries.ITEM, new Identifier("newmobs", "human_spawn_egg"), HUMAN_SPAWNEGG);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "citizen_spawn_egg"), CITIZEN_SPAWNEGG);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "rebel_spawn_egg"), REBEL_SPAWNEGG);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_spawn_egg"), STORMTROOPER_SPAWNEGG);

        Registry.register(Registries.ITEM_GROUP, new Identifier("newmobs", "starwars_group"), STARWARS_GROUP_ARMOR);
        Registry.register(Registries.ITEM_GROUP, new Identifier("newmobs", "starwars_group_weapons"), STARWARS_GROUP_WEAPONS);
        Registry.register(Registries.ITEM_GROUP, new Identifier("newmobs", "starwars_group_items"), STARWARS_GROUP_ITEMS);
    }
}
