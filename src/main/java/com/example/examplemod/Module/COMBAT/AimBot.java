package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.Comparator;

public class AimBot extends Module {
    public AimBot() {
        super("AimBot", Keyboard.KEY_NONE, Category.COMBAT);
        ExampleMod.instance.settingsManager.rSetting(new Setting("Range", this, 5, 1, 100, true));
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        double range = (float) ExampleMod.instance.settingsManager.getSettingByName(this.name, "Range").getValDouble();
        EntityPlayer target  = mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.player).min(Comparator.comparing(entityPlayer ->
                entityPlayer.getDistance(mc.player))).filter(entityPlayer -> entityPlayer.getDistance(mc.player) <= range).orElse(null);

        if (target != null && mc.player.canEntityBeSeen(target)) {
            mc.player.rotationYaw = rotations(target)[0];
            mc.player.rotationPitch = rotations(target)[1];
        }
    }

    public float[] rotations(EntityPlayer entity) {
        double x = entity.posX - mc.player.posX;
        double y = entity.posY - (mc.player.posY + mc.player.getEyeHeight()) + 1.5;
        double z = entity.posZ - mc.player.posZ;

        double u = MathHelper.sqrt(x * x + z * z);

        float u2 = (float) (MathHelper.atan2(z, x) * (180D / Math.PI) - 90.0F);
        float u3 = (float) (-MathHelper.atan2(y, u) * (180D / Math.PI));

        return new float[]{u2, u3};
    }
}
