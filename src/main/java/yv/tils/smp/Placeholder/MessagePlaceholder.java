package yv.tils.smp.Placeholder;

import yv.tils.smp.SMPPlugin;

public class MessagePlaceholder{
    public static String PREFIX = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIX");
    public static String PREFIXSMP = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXSMP");
    public static String PREFIXLOBBY = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXLOBBY");
    public static String PREFIXKICK = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXKICK");
    public static String PREFIXCONNECT = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXCONNECT");
    public static String PREFIXDISCONNECT = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXDISCONNECT");
    public static String PREFIXENABLE = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXENABLE");
    public static String PREFIXDISABLE = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXDISABLE");
    public static String PREFIXERROR = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXERROR");
    public static String PREFIXERELOAD = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXERELOAD");
    public static String PREFIXUPDATE = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXUPDATE");
    public static String PREFIXNOUPDATE = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXNOUPDATE");
    public static String PREFIXSTART = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXSTART");
    public static String PREFIXSEED = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXSEED");
    public static String PREFIXANNOUNCEMENT = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXANNOUNCEMENT");
    public static String PREFIXHELP = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXHELP");
    public static String PREFIXFEEDBACK = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXFEEDBACK");
    public static String PREFIXGLOBALMUTE = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXGLOBALMUTE");
    public static String PREFIXMODERATION = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXMODERATION");
    public static String PREFIXDC = SMPPlugin.getInstance().getConfig().getString("Placeholder.PREFIXDC");
    public static String PREFIXTHANKS = "§9[YVtils-THANKS]";
    public static String PERMISSIONERROR = LanguagePlaceholder.PermissionError();
}
