package trollogyadherent.tabfaces.varinstances;

import trollogyadherent.tabfaces.TabFaces;
import trollogyadherent.tabfaces.registries.ServerRegistry;

import java.io.File;

public class VarInstanceServer {
    public File skinCachePath = new File(TabFaces.rootPath, "cache" + File.separator + "client" + File.separator + "skins");
    public ServerRegistry serverRegistry = new ServerRegistry();
}
