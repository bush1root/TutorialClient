package com.example.examplemod.Module.RENDER;

import com.example.examplemod.Module.CLIENT.Panic;
import com.example.examplemod.Module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import org.lwjgl.input.Keyboard;

public class FakePlayer extends Module {
    EntityOtherPlayerMP fakePlayer;

    public FakePlayer() {
        super("FakePlayer", Keyboard.KEY_NONE, Category.RENDER);
    }

    @Override
    public void onEnable() {
        fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
        fakePlayer.setEntityId(-1882);
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
    }

    @Override
    public void onDisable() {
        if (!Panic.isPanic) {
            mc.world.removeEntityFromWorld(fakePlayer.getEntityId());
        }
    }
}
