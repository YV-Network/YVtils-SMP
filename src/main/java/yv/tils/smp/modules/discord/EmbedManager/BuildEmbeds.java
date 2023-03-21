package yv.tils.smp.modules.discord.EmbedManager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.StringReplacer;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class BuildEmbeds {
    EmbedBuilder builder = new net.dv8tion.jda.api.EmbedBuilder();

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mc_dcbridge = YamlConfiguration.loadConfiguration(file1);

    String st;

    public String urlisempty() {
        if (Objects.equals(mc_dcbridge.getString("EmbedAuthorIcon"), "" ) || Objects.equals(mc_dcbridge.getString("EmbedAuthorIcon"), " ")) {
        }else {
            st = mc_dcbridge.getString("EmbedAuthorIcon");
        }
        return st;
    }

    public EmbedBuilder namechanged(String oldname, String newname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("OLDNAME");
        list2.add(oldname);
        list1.add("NEWNAME");
        list2.add(newname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_NAME_CHANGE))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_NAME_CHANGE), list1, list2))
                .setColor(new Color(7719960))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
    public EmbedBuilder namewhitelisted(String accname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_NAME_ADD))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_NAME_ADD), list1, list2))
                .setColor(new Color(7719960))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
    public EmbedBuilder namenotexisting(String accname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_NAME_NOTEXISTING))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
    public EmbedBuilder namecheckservererror(String accname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_SERVER_ERROR))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_SERVER_ERROR), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
    public EmbedBuilder namehasunallowedcharacters(String accname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_NAME_UNALLOWED_CHARACTERS))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_NAME_UNALLOWED_CHARACTERS), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
    public EmbedBuilder accountalreadywhitelisted(String accname, Guild guild) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
    }
}