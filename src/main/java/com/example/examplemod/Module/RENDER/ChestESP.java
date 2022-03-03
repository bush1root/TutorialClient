package com.example.examplemod.Module.RENDER;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ChestESP extends Module {
    public ChestESP() {
        super("ChestESP", Keyboard.KEY_NONE, Category.RENDER);
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e) {
        for (Object c : mc.world.loadedTileEntityList) {
            if (c instanceof TileEntityChest) {
                RenderUtils.blockESP(((TileEntityChest) c).getPos());
            }
        }
    }
}
