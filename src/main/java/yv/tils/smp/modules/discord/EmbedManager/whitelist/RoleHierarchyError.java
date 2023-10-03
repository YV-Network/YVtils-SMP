package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @since 4.6.8
 * @version 4.6.8.1
 */
public class RoleHierarchyError {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String role, Guild guild) {
        role = role.replace(" ", "");
        String[] roles = role.split(",");
        Role role1;

        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ROLE");

        for (String s : roles) {
            role1 = guild.getRoleById(s);
            list2.add(role1.getAsMention());
        }

        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_ROLE_ADD_ERROR_TITLE))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_ROLE_ADD_ERROR_DESC), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}