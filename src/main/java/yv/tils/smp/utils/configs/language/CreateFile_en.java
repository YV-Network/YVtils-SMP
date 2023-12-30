package yv.tils.smp.utils.configs.language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;
import java.io.IOException;

/**
 * @since 4.6.7
 * @version 4.6.8
 */
public class CreateFile_en {
    File file = new File(YVtils.getInstance().getDataFolder() + "/Language", "en.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("Language File", "EN");
        ymlfile.addDefault("#1", "Please always use given variables! You can recognize them by looking for full capslock words");
        ymlfile.addDefault("START_MESSAGE", "PREFIXENABLE §aPlugin is starting!");
        ymlfile.addDefault("START_COMPLETED_MESSAGE", "PREFIXENABLE §2Plugin has started successfully!");
        ymlfile.addDefault("STOP_MESSAGE", "PREFIXDISABLE §cPlugin is getting stopped!");
        ymlfile.addDefault("STOP_COMPLETED_MESSAGE", "PREFIXDISABLE §4Plugin got stopped!");
        ymlfile.addDefault("PLUGIN_UP_TO_DATE", "PREFIXNOUPDATE §fThe Plugin has no Updates available!");
        ymlfile.addDefault("PLUGIN_UPDATE_AVAILABLE", "PREFIXUPDATE §eThe Plugin Version NEWVERSION is now available. The Server is using OLDVERSION. Download the newest version here LINK");
        ymlfile.addDefault("UNKNOWN_TIME_FORMAT", "This time format is not available!");
        ymlfile.addDefault("WHITELIST_EMPTY", "The whitelist is empty!");
        ymlfile.addDefault("PLAYER_NOT_ONLINE", "This player is not online!");
        ymlfile.addDefault("PLAYER_UNKNOWN", "PREFIX §4Unkown Player");
        ymlfile.addDefault("PLAYER_ALREADY_BANNED", "This player is already banned!");
        ymlfile.addDefault("MISSING_PERMISSION", "§cMissing Permission:");
        ymlfile.addDefault("SPAWN_ELYTRA_BOOST", "§fTo boost yourself press");
        ymlfile.addDefault("SMP_START_MESSAGE", "§dHave fun at project name!");
        ymlfile.addDefault("SMP_ALREADY_STARTED", "§dThis SMP has already started!");
        ymlfile.addDefault("FLY_COMMAND_ENABLE", "§7You can fly now!");
        ymlfile.addDefault("FLY_COMMAND_DISABLE", "§7You can't fly anymore!");
        ymlfile.addDefault("FLY_COMMAND_ENABLE_OTHER", "§7PLAYER can now fly!");
        ymlfile.addDefault("FLY_COMMAND_DISABLE_OTHER", "§7PLAYER can't fly anymore!");
        ymlfile.addDefault("HEAL_PLAYER_HEALED", "§7You got §8§lhealed!");
        ymlfile.addDefault("HEAL_OTHER_PLAYER_HEALED", "§7PLAYER got §8§lhealed!");
        ymlfile.addDefault("GODMODE_COMMAND_ENABLE", "§7God Mode is now §8activated§7!");
        ymlfile.addDefault("GODMODE_COMMAND_DISABLE", "§7God Mode is now §8deactivated§7!");
        ymlfile.addDefault("GODMODE_COMMAND_ENABLE_OTHER", "§7PLAYER is now in the God Mode!");
        ymlfile.addDefault("GODMODE_COMMAND_DISABLE_OTHER", "§7PLAYER is not in the God Mode anymore!");
        ymlfile.addDefault("GAMEMODE_SWITCH_SURVIVAL", "§7Your game mode got changed to §aSurvival§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_CREATIVE", "§7Your game mode got changed to §aCreative§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_ADVENTURE", "§7Your gamemode got changed to §aAdventure§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_SPECTATOR", "§7Your game mode got changed to §aSpectator§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_SURVIVAL_OTHER", "§7The game mode of PLAYER got changed to §aSurvival§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_CREATIVE_OTHER", "§7The game mode of PLAYER got changed to §aCreative§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_ADVENTURE_OTHER", "§7The game mode of PLAYER got changed to §aAdventure§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_SPECTATOR_OTHER", "§7The game mode of PLAYER got changed to §aSpectator§7!");
        ymlfile.addDefault("GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE", "§cYou are already in this Gamemode!");
        ymlfile.addDefault("GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE_OTHER", "§cPLAYER is already in this game mode!");
        ymlfile.addDefault("MSG_NOTE", "§e[§cNote§e]§f MESSAGE");
        ymlfile.addDefault("MSG_PLAYER_WENT_OFFLINE", "PREFIX §4The player with which you have written, has gone offline!");
        ymlfile.addDefault("MSG_HAVENT_MESSAGED_A_PLAYER", "PREFIX §4You haven't written to anybody yet!");
        ymlfile.addDefault("MOD_NO_REASON", "No Reason was given!");
        ymlfile.addDefault("MOD_PLAYER_NOT_BANNED", "PREFIXMODERATION §8PLAYER §7 is not banned!");
        ymlfile.addDefault("MOD_ANNOUNCEMENT_KICK", "'PREFIXMODERATION §8PLAYER §7got kicked from §8MODERATOR§7! Reason");
        ymlfile.addDefault("MOD_ANNOUNCEMENT_BAN", "'PREFIXMODERATION §8PLAYER §7got banned from §8MODERATOR§7! Reason");
        ymlfile.addDefault("MOD_ANNOUNCEMENT_TEMPBAN", "'PREFIXMODERATION §8PLAYER §7got banned temporary from §8MODERATOR§7! Reason");
        ymlfile.addDefault("MOD_ANNOUNCEMENT_UNBAN", "PREFIXMODERATION §8PLAYER §7got unbanned from §8MODERATOR§7!");
        ymlfile.addDefault("VANISH_ACTIVATE", "PREFIX §7Vanish is now activated!");
        ymlfile.addDefault("VANISH_DEACTIVATE", "PREFIX §7Vanish is now deactivated!");
        ymlfile.addDefault("VANISH_ITEM_PICKUP_NOT_IN_VANISH", "PREFIX §7You have not vanished! Please use first /vanish before using /v itempickup!");
        ymlfile.addDefault("VANISH_ITEM_PICKUP_ACTIVATE", "PREFIX §7Item Pick Up is now activated!");
        ymlfile.addDefault("VANISH_ITEM_PICKUP_DEACTIVATE", "PREFIX §7Item Pick Up is now deactivated!");
        ymlfile.addDefault("VANISH_ALREADY_ACTIVATED", "You are already vanished!");
        ymlfile.addDefault("VANISH_ALREADY_DEACTIVATED", "You have not vanished!");
        ymlfile.addDefault("MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE", "You are not allowed to join the server while maintenance works!");
        ymlfile.addDefault("MAINTENANCE_COMMAND_DEACTIVATE", "§7The maintenance mode is now §adeactivated§7!");
        ymlfile.addDefault("MAINTENANCE_COMMAND_ACTIVATE", "§7The maintenance mode is now §aactivated§7!");
        ymlfile.addDefault("MAINTENANCE_ALREADY_DEACTIVATED", "§7The maintenance mode is already §adeactivated§7!");
        ymlfile.addDefault("MAINTENANCE_ALREADY_ACTIVATED", "§7The maintenance mode is already §aactivated§7!");
        ymlfile.addDefault("MAINTENANCE_ILLEGAL_CONFIG_VALUE", "This config value is not valid!");
        ymlfile.addDefault("MAINTENANCE_STATUS_ENABLED", "§7The maintenance mode is §aactivated§7!");
        ymlfile.addDefault("MAINTENANCE_STATUS_DISABLED", "§7The maintenance mode is §adactivated§7!");
        ymlfile.addDefault("COMMAND_USAGE", "§7Use:");
        ymlfile.addDefault("COMMAND_REPLACE_COPY_COMMAND_TO_CLIPBOARD", "§7Click to copy!");
        ymlfile.addDefault("COMMAND_REPLACE_NEW_COMMAND_INFO", "§7Please use \\nCOMMAND");
        ymlfile.addDefault("TAB_COMPLETER_MOD_COMMAND_DURATION", "Duration");
        ymlfile.addDefault("TAB_COMPLETER_MOD_COMMAND_REASON", "[Reason]");
        ymlfile.addDefault("MODULE_DISCORD_NO_BOT_TOKEN_GIVEN", "No bot token was found. Please enter one or disable this module!");
        ymlfile.addDefault("MODULE_DISCORD_STARTUP_FAILED", "Bot startup has failed! Please check your configurations!");
        ymlfile.addDefault("MODULE_DISCORD_STARTUP_FINISHED", "Bot startup was successful! The bot should be online now!");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_CHANGE", "Discord user 'DISCORDUSER' has changed his whitelisted account from 'OLDNAME' to 'NEWNAME'");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_ADD", "Discord user 'DISCORDUSER' has added his Minecraft account 'NAME' to the whitelist");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_WRONG", "Discord user 'DISCORDUSER' has tried to whitelist the Minecraft account 'NAME', but it failed! -> Account does not exist");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_SERVERERROR_CHECK_INPUT", "Discord user 'DISCORDUSER' has tried to whitelist the Minecraft account 'NAME', but it failed! -> Authentication server is not reachable");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_CHANGE", "You successfully changed your Minecraft account!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_CHANGE", "OLDNAME -> NEWNAME");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_ADD", "You successfully whitelisted your account!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_ADD", "Account Name");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_NOTEXISTING", "This Minecraft account does not exist!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING", "Account Name");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_SERVER_ERROR", "Authentication servers are not available!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_SERVER_ERROR", "Account Name");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_UNALLOWED_CHARACTERS", "This Minecraft account does not exist!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_UNALLOWED_CHARACTERS", "Account Name");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED", "This account is already whitelisted!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED", "Account Name");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_TITLE", "Whitelist Check");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_WHITELISTED_DESC", "Account Name");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_NOT_WHITELISTED_DESC", "Account Name");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_ADD_TITLE", "You successfully whitelisted this account!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_ADD_DESC", "Minecraft Account");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REPLACE_TITLE", "You changed successfully changed the whitelisted account of DCNAME!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REPLACE_DESC", "OLDNAME -> NEWNAME");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVE_TITLE", "Which account do you want to remove from the whitelist?");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVE_DESC", "￬ Choose it down here ￬");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVED_TITLE", "You successfully removed the Minecraft account MCNAME (DCNAME) from the whitelist!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVED_DESC", "Do you want to remove a second account? \\n ￬ Choose it down here ￬");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_ADD", "Discord user 'DISCORDUSER' has whitelisted the Minecraft account 'MCNAME' (linked with 'DCNAME')");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_CHANGE", "Discord user 'DISCORDUSER' has changed the linked account from 'DCNAME' from 'OLDNAME' to 'NEWNAME'");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_REMOVE", "Discord user 'DISCORDUSER' has removed the Minecraft account 'MCNAME' (linked with 'DCNAME') from the whitelist");
        ymlfile.addDefault("EMBED_CMD_ROLE_ADD_ERROR_TITLE", "There occurred an error while adding the role!");
        ymlfile.addDefault("EMBED_CMD_ROLE_ADD_ERROR_DESC", "The role that should be given (ROLE), could not be given to the user because of the wrong hierarchy! Please set the bot role over the role which should be given to the user.");
        ymlfile.addDefault("MODULE_STATUS_OTHER_PLAYER_HAS_NO_STATUS", "This player has no status set!");
        ymlfile.addDefault("MODULE_STATUS_CLEAR_OTHER_UNALLOWED", "§cYou can't clear the status of other players!");
        ymlfile.addDefault("MODULE_STATUS_CLEAR_OTHER_CLEARED", "§7You successfully cleared the status of §ePLAYER §7!");
        ymlfile.addDefault("MODULE_STATUS_CLEAR_CLEARED", "§7You successfully cleared the status!");
        ymlfile.addDefault("MODULE_STATUS_PLAYER_HAS_NO_STATUS", "§7You have no status set!");
        ymlfile.addDefault("MODULE_STATUS_CUSTOM_STATUS_TOO_LONG", "§cThis custom status is too long!");
        ymlfile.addDefault("MODULE_STATUS_NOT_ALLOWED_TO_SET_CUSTOM_STATUS", "§7You are missing permissions to set a custom status!");
        ymlfile.addDefault("MODULE_STATUS_NO_DEFAULT_STATUS", "§7This is no default status!");
        ymlfile.addDefault("MODULE_STATUS_SET", "§7You successfully set the status STATUS§7!");
        ymlfile.addDefault("MODULE_STATUS_SELECTED_STATUS_JOIN_ANNOUNCEMENT", "§7The status 'STATUS§7' is set!");
        ymlfile.addDefault("MODULE_CCR_ACCEPT_RECIPE", "§aAccept crafting recipe");
        ymlfile.addDefault("MODULE_INVSEE_INVENTORY", "§eInventory of PLAYER");
        ymlfile.addDefault("MODULE_INVSEE_ENDERCHEST", "§5Enderchest of PLAYER");

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        ymlfile.addDefault("PLAYER_ARGUMENT_MISSING", "§cTo use this command here, a player is required!");

        ymlfile.addDefault("GLOBALMUTE_ENABLE_ANNOUNCEMENT", "PREFIXGLOBALMUTE §7The global mute got §aactivated§7!");
        ymlfile.addDefault("GLOBALMUTE_DISABLE_ANNOUNCEMENT", "PREFIXGLOBALMUTE §7The global mute got §adeactivated§7!");
        ymlfile.addDefault("GLOBALMUTE_ENABLE_FEEDBACK", "PREFIXFEEDBACK §7You §adeactivated §7the chat!");
        ymlfile.addDefault("GLOBALMUTE_DISABLE_FEEDBACK", "PREFIXFEEDBACK §7You §aactivated §7the chat!");
        ymlfile.addDefault("GLOBALMUTE_TRY_TO_WRITE", "PREFIXGLOBALMUTE §7The global mute is activated!");
        ymlfile.addDefault("GLOBALMUTE_STATUS_ENABLED", "PREFIXFEEDBACK §7The global mute is §aactivated§7!");
        ymlfile.addDefault("GLOBALMUTE_STATUS_DISABLED", "PREFIXFEEDBACK §7The global mute is §adisabled§7!");
        ymlfile.addDefault("GLOBALMUTE_ALREADY_ENABLED", "PREFIXFEEDBACK §7The global mute is already §aactivated§7!");
        ymlfile.addDefault("GLOBALMUTE_ALREADY_DISABLED", "PREFIXFEEDBACK §7The global mute is already §adisabled§7!");

        ymlfile.addDefault("SPEED_CHANGE_SELF", "§7Your speed got changed to §aSPEED§7!");
        ymlfile.addDefault("SPEED_CHANGE_OTHER", "§7The speed of §ePLAYER §7got changed to §aSPEED§7!");
        ymlfile.addDefault("SPEED_RESET_SELF", "§7Your speed got reset!");
        ymlfile.addDefault("SPEED_RESET_OTHER", "§7The speed of §ePLAYER §7got reset!");

        ymlfile.addDefault("ADVANCEMENT_ANNOUNCEMENT", "§fPLAYER has made the advancement ADVANCEMENT");

        ymlfile.options().copyDefaults(true);
        fileSave();
    }

    public void fileSave() {
        try {
            ymlfile.save(file);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }
}
