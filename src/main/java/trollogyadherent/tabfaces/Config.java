package trollogyadherent.tabfaces;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {
    public static Configuration config = new Configuration(TabFaces.confFile);
    static boolean loaded = false;
    
    private static class Defaults {
        /* client */
        public static final int maxAcceptedSkinBytes = 500000;
        public static final boolean showQuestionMarkIfUnknown = true;
        public static final boolean alignToLeftIfNoQuestion = false;

        /* common */
        public static final boolean debugMode = false;
    }

    public static class Categories {
        public static final String client = "client";
        public static final String server = "server";
        public static final String common = "common";
    }

    /* client */
    public static int maxAcceptedSkinBytes = Defaults.maxAcceptedSkinBytes;

    /* comon */
    public static boolean debugMode = Defaults.debugMode;
    public static boolean showQuestionMarkIfUnknown = Defaults.showQuestionMarkIfUnknown;
    public static boolean alignToLeftIfNoQuestion = Defaults.alignToLeftIfNoQuestion;

    public static void synchronizeConfigurationClient(File configFile, boolean force, boolean load) {
        if (!loaded || force) {
            if (load) {
                config.load();
            }
            loaded = true;

            synchronizeConfigurationCommon();

            Property maxAcceptedSkinBytesProperty = config.get(Categories.client, "maxAcceptedSkinBytes", Defaults.maxAcceptedSkinBytes, "Max skin bytes accepted from the server");
            maxAcceptedSkinBytes = maxAcceptedSkinBytesProperty.getInt();

            Property showQuestionMarkIfUnknownProperty = config.get(Categories.client, "showQuestionMarkIfUnknown", Defaults.showQuestionMarkIfUnknown, "Should show question mark if player skin unknown?");
            showQuestionMarkIfUnknown = showQuestionMarkIfUnknownProperty.getBoolean();

            Property alignToLeftIfNoQuestionProperty = config.get(Categories.client, "alignToLeftIfNoQuestion", Defaults.alignToLeftIfNoQuestion, "Should align usernames with missing skins to the left?");
            alignToLeftIfNoQuestion = alignToLeftIfNoQuestionProperty.getBoolean();
        }
        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void synchronizeConfigurationServer(File configFile, boolean force) {
        if (!loaded || force) {
            if (loaded) {
                config.load();
            }
            loaded = true;

            synchronizeConfigurationCommon();
        }
        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void synchronizeConfigurationCommon() {
        Property debugModeProperty = config.get(Categories.common, "debugMode", Defaults.debugMode, "Enable/disable debug logs");
        debugMode = debugModeProperty.getBoolean();
    }
}