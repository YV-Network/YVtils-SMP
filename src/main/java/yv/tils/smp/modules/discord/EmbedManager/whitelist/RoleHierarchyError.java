package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class RoleHierarchyError {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String role, Guild guild) {
        role = role.replace(" ", "");
        String[] roles = role.split(",");
        Role role1 = null;
        List rolelist = new List();
        for (int i = 0; i < roles.length; i++) {
            role1 = guild.getRoleById(roles[i]);
            rolelist.add(role1.getAsMention());
        }

        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ROLE");
        list2.add(String.valueOf(rolelist));
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_ROLE_ADD_ERROR_TITLE))
                .setDescription(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_ROLE_ADD_ERROR_DESC), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}