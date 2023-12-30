package yv.tils.smp.mods.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class AccountCheckError {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String accName) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("ACCOUNTNAME");
        list2.add(accName);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_SERVER_ERROR))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_SERVER_ERROR), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}