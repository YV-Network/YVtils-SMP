package yv.tils.smp.modules.discord.BotManager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.discord.CommandManager.CommandHandler;
import yv.tils.smp.modules.discord.CommandManager.CommandRegister;
import yv.tils.smp.modules.discord.EventListener.WhitelistMessageGetter;
import yv.tils.smp.modules.discord.sync.ChatSync;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.8
 */
public class BotStartStop {
    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/config.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    String token = modifyFile1.getString("BotToken");
    String mainGuild = modifyFile1.getString("MainGuild");
    String status = modifyFile1.getString("BotSettings.OnlineStatus");
    String activity = modifyFile1.getString("BotSettings.Activity");
    String activitymessage = modifyFile1.getString("BotSettings.ActivityMessage");
    String logChannel = modifyFile1.getString("LogChannel");

    public JDA jda;
    JDABuilder builder = JDABuilder.createDefault("");

    private static BotStartStop instance;

    //DEVELOPMENT VERSION
   public void TokenCheck() {
       instance = this;
       BotSettings();
   }

    public static BotStartStop getInstance() {
        return instance;
    }
/*         if (token.equals(LanguageFile.DirectFormatter("YOUR TOKEN HERE","DEINEN BOT TOKEN"))) {
            Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_NO_BOT_TOKEN_GIVEN));
            Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_STARTUP_FAILED));
        }else {
            BotSettings();
        }
    }

    JDABuilder builder = JDABuilder.createDefault(token);

 */

    public void BotSettings() {

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        //Activity -> Streaming; Watching; Playing; Competing; None;

        switch (activity.toLowerCase()) {
            case "watching" -> builder.setActivity(Activity.watching(activitymessage));
            case "playing" -> builder.setActivity(Activity.playing(activitymessage));
            case "competing" -> builder.setActivity(Activity.competing(activitymessage));
            case "none" -> builder.setActivity(null);
            default -> builder.setActivity(Activity.playing("Minecraft"));
        }

        //Status -> Online; Idle; DND; Offline; Invisible; Unknown

        switch (status.toLowerCase()) {
            case "online" -> builder.setStatus(OnlineStatus.ONLINE);
            case "idle" -> builder.setStatus(OnlineStatus.IDLE);
            case "dnd" -> builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            case "offline" -> builder.setStatus(OnlineStatus.OFFLINE);
            case "invisible" -> builder.setStatus(OnlineStatus.INVISIBLE);
            default -> builder.setStatus(OnlineStatus.ONLINE);
        }

        //register

        builder.addEventListeners(new CommandRegister());
        builder.addEventListeners(new CommandHandler());
        builder.addEventListeners(new WhitelistMessageGetter());
        builder.addEventListeners(new ChatSync());

        jda = builder.build();

        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_STARTUP_FINISHED));
    }

    public void onStop() {
        if (jda != null) {
            try {
                builder.setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("------------------------");
                e.getCause();
                System.out.println("------------------------");
            }}}}