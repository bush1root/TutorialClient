package com.example.examplemod.keys;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.CLIENT.Panic;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class key {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if (Keyboard.isKeyDown(Keyboard.getEventKey())) {
            if (Keyboard.getEventKey() != Keyboard.KEY_NONE) {
                Client.keyPress(Keyboard.getEventKey());
                if (Keyboard.getEventKey() == Keyboard.KEY_RSHIFT && !Panic.isPanic) {
                    Minecraft.getMinecraft().displayGuiScreen(ExampleMod.instance.clickGui);
                }
            }
        }
    }
}
