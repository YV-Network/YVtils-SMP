package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class AccountAlreadyListed {

    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String accname) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}