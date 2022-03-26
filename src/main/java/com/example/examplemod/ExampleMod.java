package com.example.examplemod;

import com.example.examplemod.Menu.onGuiOpenEvent;
import com.example.examplemod.UI.ui;
import com.example.examplemod.keys.key;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.Session;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import yea.bushroot.clickgui.ClickGuiManager;
import yea.bushroot.clickgui.SettingsManager;

import java.lang.reflect.Field;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "tutorial";
    public static final String NAME = "TutorialClient Mod";
    public static final String VERSION = "1.0";

    public static ExampleMod instance;
    public SettingsManager settingsManager;
    public ClickGuiManager clickGui;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle("Loading " + Client.name);
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        clickGui = new ClickGuiManager();
        instance = this;
        settingsManager = new SettingsManager();

        Client.startup();

        MinecraftForge.EVENT_BUS.register(new key());
        MinecraftForge.EVENT_BUS.register(new ui());
        MinecraftForge.EVENT_BUS.register(new onGuiOpenEvent());
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    public static void setSession(Session s) {
        Class<? extends Minecraft> mc = Minecraft.getMinecraft().getClass();

        try {
            Field session = null;

            for (Field f : mc.getDeclaredFields()) {
                if (f.getType().isInstance(s)) {
                    session = f;
                }
            }

            if (session == null) {
                throw new IllegalStateException("Session Null");
            }

            session.setAccessible(true);
            session.set(Minecraft.getMinecraft(), s);
            session.setAccessible(false);

            Client.name = "TutorialClient 1.12.2 | User: " + Minecraft.getMinecraft().getSession().getUsername();
            Display.setTitle(Client.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
