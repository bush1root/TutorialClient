package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class KillAura extends Module {
    public KillAura() {
        super("KillAura", Keyboard.KEY_R, Category.COMBAT);

        ArrayList<String> options = new ArrayList<>();

        options.add("Rage");
        options.add("Rotation");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Range", this, 4.2, 1, 5, false));
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        String Mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();
        double range = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Range").getValDouble();

        EntityPlayer target  = mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.player).min(Comparator.comparing(entityPlayer ->
                entityPlayer.getDistance(mc.player))).filter(entityPlayer -> entityPlayer.getDistance(mc.player) <= range).orElse(null);

        if (target != null) {
            if (Objects.equals(Mode, "Rotation")) {
                mc.player.rotationYaw = rotations(target)[0];
                mc.player.rotationPitch = rotations(target)[1];
            }

            if (mc.player.getCooledAttackStrength(0) == 1) {
                mc.playerController.attackEntity(mc.player, target);
                mc.player.swingArm(EnumHand.MAIN_HAND);
                mc.player.resetCooldown();
            }
        }
    }

    public float[] rotations(EntityPlayer entity) {
        double x = entity.posX - mc.player.posX;
        double y = entity.posY - (mc.player.posY + mc.player.getEyeHeight());
        double z = entity.posZ - mc.player.posZ;

        double u = MathHelper.sqrt(x * x + z * z);

        float u2 = (float) (MathHelper.atan2(z, x) * (180D / Math.PI) - 90.0F);
        float u3 = (float) (-MathHelper.atan2(y, u) * (180D / Math.PI));

        return new float[]{u2, u3};
    }
}
