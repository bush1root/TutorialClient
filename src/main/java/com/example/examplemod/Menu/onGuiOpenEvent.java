package com.example.examplemod.Menu;

import com.example.examplemod.Module.CLIENT.Panic;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class onGuiOpenEvent {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent e) {
        if (e.getGui() instanceof GuiMainMenu && !Panic.isPanic) {
            e.setGui(new TutorialMenu());
        }
    }
}
