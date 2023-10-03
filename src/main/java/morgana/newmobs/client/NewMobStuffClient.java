package  morgana.newmobs.client;


import morgana.newmobs.NewMobStuff;
import morgana.newmobs.client.render.HumanEntityRenderer;
import morgana.newmobs.client.render.LaserEntityRenderer;
import morgana.newmobs.entity.HumanEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.*;

import javax.swing.*;

@Environment(EnvType.CLIENT)
public class NewMobStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        /*EntityRendererRegistry.register(newmobs.CUBE, (dispatcher, context) -> {
            return new CubeEntityRenderer(dispatcher);
        });*/
        EntityRendererRegistry.register(NewMobStuff.HUMAN, (context) -> {
            return new HumanEntityRenderer(context, false) {};
        });
        EntityRendererRegistry.register(NewMobStuff.CITIZEN, (context) -> {
            return new HumanEntityRenderer(context, false) {};
        });
        EntityRendererRegistry.register(NewMobStuff.REBEL, (context) -> {
            return new HumanEntityRenderer(context, false) {};
        });
        EntityRendererRegistry.register(NewMobStuff.STORMTROOPER, (context) -> {
            return new HumanEntityRenderer(context,false) {};
        });

        EntityRendererRegistry.register(NewMobStuff.LASER, (context) -> {
            return new LaserEntityRenderer(context) {};
        });
    }
}
