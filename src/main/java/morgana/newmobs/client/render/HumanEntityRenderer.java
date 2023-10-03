package morgana.newmobs.client.render;

import morgana.newmobs.entity.HumanEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.PlayerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;
import morgana.newmobs.client.model.HumanEntityModel;

@Environment(EnvType.CLIENT)
public class HumanEntityRenderer extends BipedEntityRenderer<HumanEntity, HumanEntityModel<HumanEntity>> {
    private static final Identifier TEXTURE = new Identifier( "newmobs","textures/entity/human/human.png");
    //private static final Identifier TEXTURE = new Identifier("textures/entity/zombie/zombie.png");
    public HumanEntityRenderer(EntityRendererFactory.Context ctx, boolean slim) {
        //this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
        this(ctx, EntityModelLayers.PLAYER, EntityModelLayers.PLAYER_INNER_ARMOR, EntityModelLayers.PLAYER_OUTER_ARMOR, slim);
    }

    public HumanEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer, boolean slim) {
        super(ctx, new HumanEntityModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, new ArmorEntityModel(ctx.getPart(slim ? EntityModelLayers.PLAYER_SLIM_INNER_ARMOR : EntityModelLayers.PLAYER_INNER_ARMOR)), new ArmorEntityModel(ctx.getPart(slim ? EntityModelLayers.PLAYER_SLIM_OUTER_ARMOR : EntityModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
        //this.addFeature(new ArmorFeatureRenderer(this, new HumanEntityModel(ctx.getPart(legArmorLayer)), new HumanEntityModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
        this.addFeature(new PlayerHeldItemFeatureRenderer(this, ctx.getHeldItemRenderer()));
    }

    public Identifier getTexture(HumanEntity abstractSkeletonEntity) {
        return TEXTURE;
    }

    protected boolean isShaking(HumanEntity abstractSkeletonEntity) {
        return false;//abstractSkeletonEntity.isShaking();
    }
}