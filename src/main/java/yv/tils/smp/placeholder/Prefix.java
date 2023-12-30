package yv.tils.smp.placeholder;

import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

/**
 * @version 4.6.6
 * @since 4.6.6
 */
public class Prefix {
    public static String PREFIX = YVtils.getInstance().getConfig().getString("Placeholder.PREFIX");
    public static String PREFIXCONNECT = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXCONNECT");
    public static String PREFIXDISCONNECT = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXDISCONNECT");
    public static String PREFIXENABLE = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXENABLE");
    public static String PREFIXDISABLE = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXDISABLE");
    public static String PREFIXUPDATE = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXUPDATE");
    public static String PREFIXNOUPDATE = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXNOUPDATE");
    public static String PREFIXSTART = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXSTART");
    public static String PREFIXSEED = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXSEED");
    public static String PREFIXFEEDBACK = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXFEEDBACK");
    public static String PREFIXGLOBALMUTE = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXGLOBALMUTE");
    public static String PREFIXMODERATION = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXMODERATION");
    public static String PREFIXDC = YVtils.getInstance().getConfig().getString("Placeholder.PREFIXDC");
    public static String PERMISSIONERROR = LanguageFile.getMessage(LanguageMessage.MISSING_PERMISSION);
}
