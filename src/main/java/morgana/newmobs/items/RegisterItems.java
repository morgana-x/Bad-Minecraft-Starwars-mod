package morgana.newmobs.items;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import morgana.newmobs.materials.stormtrooperArmorMaterial;
public class RegisterItems {
    public static final ArmorMaterial stormtrooperArmorMaterial = new stormtrooperArmorMaterial();
    // ERRORS BELOW
    public static final Item STORMTROOPER_HELMET = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.HELMET, new Item.Settings());
    public static final Item STORMTROOPER_CHESTPLATE = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    public static final Item STORMTROOPER_LEGGINGS = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.LEGGINGS, new Item.Settings());
    public static final Item STORMTROOPER_BOOTS = new ArmorItem(stormtrooperArmorMaterial, ArmorItem.Type.BOOTS, new Item.Settings());
    public static final BlasterBase BLASTER_BASIC = new BlasterBase(new FabricItemSettings());
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_helmet"), STORMTROOPER_HELMET);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_chestplate"), STORMTROOPER_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_leggings"), STORMTROOPER_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "stormtrooper_boots"), STORMTROOPER_BOOTS);
        Registry.register(Registries.ITEM, new Identifier("newmobs", "blasterbasic"), BLASTER_BASIC);
    }
}
