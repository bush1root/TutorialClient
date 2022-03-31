package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", Keyboard.KEY_NONE, Category.MOVEMENT);

        ArrayList<String> options = new ArrayList<>();

        options.add("Matrix");
        options.add("Dolphin");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        String Mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();

        BlockPos blockPos = new BlockPos(mc.player.posX, mc.player.posY - 0.1, mc.player.posZ);
        Block block = mc.world.getBlockState(blockPos).getBlock();

        if (Objects.equals(Mode, "Matrix")) {
            if (Block.getIdFromBlock(block) == 9) {
                if (!mc.player.onGround) {
                    speed(2.5);

                    if (mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY + 0.0000001, mc.player.posZ)).getBlock() == Block.getBlockById(9)) {
                        mc.player.fallDistance = 0.0f;
                        mc.player.motionX = 0.0;
                        mc.player.motionY = 0.06f;
                        mc.player.jumpMovementFactor = 0.01f;
                        mc.player.motionZ = 0.0;
                    }
                }
            }
        } else {
            if (mc.player.isInWater() || mc.player.isInLava()) {
                if (!mc.player.collidedHorizontally) {
                    mc.player.motionY = 0.1;
                }
            }
        }
    }

    public static void speed(double speed) {
        Minecraft mc = Minecraft.getMinecraft();

        double forward = mc.player.movementInput.moveForward;
        double strafe = mc.player.movementInput.moveStrafe;
        float yaw = mc.player.rotationYaw;

        if (forward == 0.0 && strafe == 0.0) {
            mc.player.motionX = 0.0;
            mc.player.motionZ = 0.0;
        } else {
            if (forward != 0.0) {
                if (strafe < 0.0) {
                    yaw += (float) (forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float) (forward > 0.0 ? 45 : -45);
                }

                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }

                mc.player.motionX = forward * speed * Math.cos(Math.toRadians(yaw + 90.0)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0));
                mc.player.motionZ = forward * speed * Math.sin(Math.toRadians(yaw + 90.0)) + strafe * speed * Math.cos(Math.toRadians(yaw + 90.0));
            }
        }
    }
}
