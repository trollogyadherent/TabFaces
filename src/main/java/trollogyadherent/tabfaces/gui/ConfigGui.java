package trollogyadherent.tabfaces.gui;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import trollogyadherent.tabfaces.Config;
import trollogyadherent.tabfaces.TabFaces;

public class ConfigGui extends GuiConfig {

    private static IConfigElement ce = new ConfigElement(Config.config.getCategory(Config.Categories.client));
    private static IConfigElement ceCMM = new ConfigElement(Config.config.getCategory(Config.Categories.common));

    public ConfigGui(GuiScreen parent) {
        //this.parentScreen = parent;
        super(parent, ImmutableList.of(ce, ceCMM), "tabfaces", "tabfaces", false, false, I18n.format("tabfaces.configgui.title"), TabFaces.confFile.getAbsolutePath());
        TabFaces.debug("Instantiating config gui");
    }

    @Override
    public void initGui()
    {
        // You can add buttons and initialize fields here
        super.initGui();
        TabFaces.debug("Initializing config gui");
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        // You can do things like create animations, draw additional elements, etc. here
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton b) {
        TabFaces.debug("Config button id " + b.id + " pressed");
        super.actionPerformed(b);
        /* "Done" button */
        if (b.id == 2000) {
            /* Syncing config */
            Config.synchronizeConfigurationClient(TabFaces.confFile, true, false);
        }
    }

}
