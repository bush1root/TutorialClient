package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;

public class AirJump extends Module {
    public AirJump() {
        super("AirJump", Keyboard.KEY_NONE, Category.MOVEMENT);

        ArrayList<String> options = new ArrayList<>();

        options.add("Default");
        options.add("Ground");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        String Mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();

        if (Objects.equals(Mode, "Default")) {
            if (mc.gameSettings.keyBindJump.isPressed()) {
                mc.player.jump();
            }
        } else {
            mc.player.onGround = true;
        }
    }
}
