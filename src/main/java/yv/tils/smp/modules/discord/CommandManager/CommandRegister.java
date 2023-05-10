package yv.tils.smp.modules.discord.CommandManager;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class CommandRegister extends ListenerAdapter {

    public void onReady(ReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        ServerInfoCommand(commandData);
        //HelpCommand(commandData);
        WhitelistCommand(commandData);
        e.getJDA().updateCommands().addCommands(commandData).queue();
    }


    private void ServerInfoCommand(List<CommandData> commandData) {
        try {
            commandData.add(Commands.slash("mcinfo", LanguageFile.DirectFormatter("Sends a helpful overview about the Minecraft Server",
                            "Sendet Hilfreiche Information über den Minecraft Server"))
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.valueOf(new DiscordConfigManager().ConfigRequest().getString("ServerInfoCommand.Permission")))));
        } catch (IllegalArgumentException ignored) {
            commandData.add(Commands.slash("mcinfo", LanguageFile.DirectFormatter("Sends a helpful overview about the Minecraft Server",
                            "Sendet Hilfreiche Information über den Minecraft Server"))
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_SEND)));
        }
    }

    /*
    private void HelpCommand(List<CommandData> commandData) {
        //Ingame
        List<Command.Choice> ingamehelplist = new ArrayList<>();
        ingamehelplist.add(new Command.Choice("CCR", "CCR"));
        ingamehelplist.add(new Command.Choice("Sit", "SIT"));
        OptionData ingamehelp = new OptionData(OptionType.STRING, "ingame", "Send an helpful overview about Ingame Functions", true).addChoices(ingamehelplist);

        //Discord
        List<Command.Choice> discordhelplist = new ArrayList<>();
        discordhelplist.add(new Command.Choice("whitelist", "WHITELIST"));
        discordhelplist.add(new Command.Choice("commands", "COMMANDS"));
        OptionData discordhelp = new OptionData(OptionType.STRING, "discord", "Send an helpful overview about Discord Bot Functions", true).addChoices(discordhelplist);

        commandData.add(Commands.slash("help", "Sends a helpful overview about Features")
                .addOptions(ingamehelp, discordhelp)
                .setGuildOnly(true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
    }
     */

    private void WhitelistCommand(List<CommandData> commandData) {
        SubcommandData subCommandData1 = new SubcommandData("forceadd",
                LanguageFile.DirectFormatter("Let you add Minecraft Accounts to the Whitelist. You can link an Discord Account too, if you want to",
                        "Lässt dich einen Minecraft Account zur whitelist hinzufügen. Du kannst freiwillig einen Discord Account verlinken"));
        SubcommandData subCommandData2 = new SubcommandData("forceremove",
                LanguageFile.DirectFormatter("Gives you an menu, where you can select one account, which will be removed from the whitelist",
                        "Gibt ein Menu zurück, in dem du einen Account auswählen kannst, welcher von der whitelist entfernt wird"));
        SubcommandData subCommandData3 = new SubcommandData("check",
                LanguageFile.DirectFormatter("Let you check if a Minecraft Account is whitelisted, when yes with wich discord account it is linked",
                        "Lässt dich überprüfen ob ein Minecraft Account auf der whitelist ist, wenn ja siehst du auch mit welchem Discord Account dieser gelinkt ist"));

        List<OptionData> options1 = new ArrayList<>();
        List<OptionData> options2 = new ArrayList<>();
        List<OptionData> options3 = new ArrayList<>();

        //ForceAdd
        options1.add(new OptionData(OptionType.STRING, "mc_name",
                LanguageFile.DirectFormatter("Enter a Minecraft Ingame Name to whitelist this Name.",
                        "Schreibe hier, welcher Minecraft Account zur whitelist hinzugefügt werden soll"), true));
        options1.add(new OptionData(OptionType.USER, "dc_name",
                LanguageFile.DirectFormatter("When you want to link an Discord Account to an MC Account, select here the Account of the User",
                        "Wenn du einen Discord Account linken willst, setze ihn hiermit fest"), false));

        subCommandData1.addOptions(options1);

        //ForceRemove
        subCommandData2.addOptions(options2);

        //Check
        options3.add(new OptionData(OptionType.STRING, "name",
                LanguageFile.DirectFormatter("Input a Name from Minecraft to check it",
                        "Setzte hier einen Minecraft Namen fest, welchen du überprüfen willst"), true));

        subCommandData3.addOptions(options3);


        try {
            commandData.add(Commands.slash("whitelist",
                            LanguageFile.DirectFormatter("Sends a Whitelist Request to the Minecraft Server",
                                    "Sendet eine whitelist Anfrage an den Minecraft Server"))
                    .addSubcommands(subCommandData1)
                    .addSubcommands(subCommandData2)
                    .addSubcommands(subCommandData3)
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.valueOf(new DiscordConfigManager().ConfigRequest().getString("WhitelistCommand.Permission")))));
        } catch (IllegalArgumentException ignored) {
            commandData.add(Commands.slash("whitelist",
                            LanguageFile.DirectFormatter("Sends a Whitelist Request to the Minecraft Server",
                                    "Sendet eine whitelist Anfrage an den Minecraft Server"))
                    .addSubcommands(subCommandData1)
                    .addSubcommands(subCommandData2)
                    .addSubcommands(subCommandData3)
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_CHANNEL)));
        }
    }
}
