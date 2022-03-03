package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public Fly() {
        super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        float speed = (float) 2.11;

        mc.player.noClip = true;
        mc.player.fallDistance = 0;
        mc.player.onGround = false;

        mc.player.capabilities.isFlying = false;

        mc.player.motionX = 0;
        mc.player.motionY = 0;
        mc.player.motionZ = 0;

        mc.player.jumpMovementFactor = speed;

        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.player.motionY += speed;
        } if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.player.motionY -= speed;
        }
    }
}
