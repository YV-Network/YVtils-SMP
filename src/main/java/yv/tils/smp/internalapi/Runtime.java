package yv.tils.smp.internalapi;

import yv.tils.smp.YVtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Runtime {
    public List<String> loadedMods() {
        List<String> mods = new ArrayList<>();

        if (YVtils.getInstance().getConfig().getBoolean("Modules.Discord")) mods.add("Discord");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Status")) mods.add("Status");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Sit")) mods.add("Sit");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.MultiMine")) mods.add("MultiMine");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.CCR")) mods.add("CCR");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.OldVersion")) mods.add("OldVersion");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Server")) mods.add("Server");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Admin")) mods.add("Admin");

        return mods;
    }
}