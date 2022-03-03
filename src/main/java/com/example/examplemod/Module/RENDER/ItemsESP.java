package com.example.examplemod.Module.RENDER;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ItemsESP extends Module {
    AxisAlignedBB box;

    public ItemsESP() {
        super("ItemsESP", Keyboard.KEY_NONE, Category.RENDER);
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e) {
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityItem) {
                box = new AxisAlignedBB(
                        entity.getEntityBoundingBox().minX
                                - 0.05
                                - entity.posX
                                + ((float) ((double) ((float) entity.lastTickPosX) + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosX),
                        entity.getEntityBoundingBox().minY
                                - entity.posY
                                + ((float) ((double) ((float) entity.lastTickPosY) + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosY),
                        entity.getEntityBoundingBox().minZ
                                - 0.05
                                - entity.posZ
                                + ((float) ((double) ((float) entity.lastTickPosZ) + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosZ),
                        entity.getEntityBoundingBox().maxX
                                + 0.05
                                - entity.posX
                                + ((float) ((double) ((float) entity.lastTickPosX) + (entity.posX - entity.lastTickPosX) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosX),
                        entity.getEntityBoundingBox().maxY
                                + 0.1
                                - entity.posY
                                + ((float) ((double) ((float) entity.lastTickPosY) + (entity.posY - entity.lastTickPosY) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosY),
                        entity.getEntityBoundingBox().maxZ
                                + 0.05
                                - entity.posZ
                                + ((float) ((double) ((float) entity.lastTickPosZ) + (entity.posZ - entity.lastTickPosZ) * Minecraft.getMinecraft().getRenderPartialTicks()) - Minecraft.getMinecraft()
                                .getRenderManager().viewerPosZ));

                RenderUtils.FillLine(entity, box);
            }
        }
    }
}
