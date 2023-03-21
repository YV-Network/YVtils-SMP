package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class AccountAdded {

    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String desc) {
        return builder
                .setTitle("Log")
                .setDescription(desc)
                .setColor(new Color(0x2C5F4B))
                .setFooter("YVtils • https://yvnetwork.de/yvtils/", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("YVtils Discord", null, url);
    }

    public EmbedBuilder namewhitelisted(String accname) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ACCOUNTNAME");
        list2.add(accname);
        //return builder
                //.setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_TITLE_NAME_ADD))
                //.setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_BUILDER_DESCRIPTION_NAME_ADD), list1, list2))
                //.setColor(new Color(7719960))
                //.setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                //.setAuthor("Whitelist Adminstration", null, urlisempty());
        return null;
    }

}
