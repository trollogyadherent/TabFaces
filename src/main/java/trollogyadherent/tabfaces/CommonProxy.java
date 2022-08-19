package trollogyadherent.tabfaces;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.MinecraftForge;
import trollogyadherent.tabfaces.event.ServerEventHandler;
import trollogyadherent.tabfaces.packet.PacketHandler;
import trollogyadherent.tabfaces.util.Util;
import trollogyadherent.tabfaces.varinstances.VarInstanceServer;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) 	{
        if (Util.isServer()) {
            TabFaces.varInstanceServer = new VarInstanceServer();
        }

        TabFaces.confFile = event.getSuggestedConfigurationFile();
        if (Util.isServer()) {
            Config.synchronizeConfigurationServer(event.getSuggestedConfigurationFile(), false);
        } else {
            Config.synchronizeConfigurationClient(event.getSuggestedConfigurationFile(), false, true);
        }
        TabFaces.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);

        PacketHandler.initPackets();

        if (Util.isServer()) {
            ServerEventHandler serverEventHandler = new ServerEventHandler();
            MinecraftForge.EVENT_BUS.register(serverEventHandler);
            FMLCommonHandler.instance().bus().register(serverEventHandler);
        }
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {

    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {

    }

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public void serverStarted(FMLServerStartedEvent event) {

    }

    public void serverStopping(FMLServerStoppingEvent event) {

    }

    public void serverStopped(FMLServerStoppedEvent event) {

    }
}
