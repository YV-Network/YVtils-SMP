package yv.tils.smp.versionfiles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.placeholder.LanguagePlaceholder;

import java.util.ArrayList;
import java.util.List;

public class CustomCraftingRecipes {

    public void addToCraftingManager() {
        Effi10Tools();
        LightBlock();
        DebugStick();
        BetterProt();
    }

    public ItemStack getResult() {
        return null;
    }

    private void Effi10Tools() {
        ItemStack pick = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta1 = pick.getItemMeta();

        meta1.setDisplayName(ChatColor.AQUA + "NETHERITE PICKAXE");
        List<String> lore1 = new ArrayList<>();
        lore1.add(LanguagePlaceholder.LegalTool());
        lore1.add(LanguagePlaceholder.Effi10Tool());
        meta1.setLore(lore1);
        meta1.addEnchant(Enchantment.DIG_SPEED, 10, true);
        pick.setItemMeta(meta1);

        ShapedRecipe epick = new ShapedRecipe(NamespacedKey.minecraft("effipick"), new ItemStack(pick));
        epick.shape(" T ", " B ", "DDD");

        epick.setIngredient('T', Material.NETHERITE_PICKAXE);
        epick.setIngredient('B', Material.BEACON);
        epick.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(epick);

        //

        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta2 = axe.getItemMeta();

        meta2.setDisplayName(ChatColor.AQUA + "NETHERITE AXE");
        List<String> lore2 = new ArrayList<>();
        lore2.add(LanguagePlaceholder.LegalTool());
        lore2.add(LanguagePlaceholder.Effi10Tool());
        meta2.setLore(lore2);
        meta2.addEnchant(Enchantment.DIG_SPEED, 10, true);
        axe.setItemMeta(meta2);

        ShapedRecipe eaxe = new ShapedRecipe(NamespacedKey.minecraft("effiaxe"), new ItemStack(axe));
        eaxe.shape(" T ", " B ", "DDD");

        eaxe.setIngredient('T', Material.NETHERITE_AXE);
        eaxe.setIngredient('B', Material.BEACON);
        eaxe.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(eaxe);

        //

        ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta meta3 = shovel.getItemMeta();

        meta3.setDisplayName(ChatColor.AQUA + "NETHERITE SHOVEL");
        List<String> lore3 = new ArrayList<>();
        lore3.add(LanguagePlaceholder.LegalTool());
        lore3.add(LanguagePlaceholder.Effi10Tool());
        meta3.setLore(lore3);
        meta3.addEnchant(Enchantment.DIG_SPEED, 10, true);
        shovel.setItemMeta(meta3);

        ShapedRecipe eshovel = new ShapedRecipe(NamespacedKey.minecraft("effishovel"), new ItemStack(shovel));
        eshovel.shape(" T ", " B ", "DDD");

        eshovel.setIngredient('T', Material.NETHERITE_SHOVEL);
        eshovel.setIngredient('B', Material.BEACON);
        eshovel.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(eshovel);

        //

        ItemStack hoe = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta4 = hoe.getItemMeta();

        meta4.setDisplayName(ChatColor.AQUA + "NETHERITE HOE");
        List<String> lore4 = new ArrayList<>();
        lore4.add(LanguagePlaceholder.LegalTool());
        lore4.add(LanguagePlaceholder.Effi10Tool());
        meta4.setLore(lore4);
        meta4.addEnchant(Enchantment.DIG_SPEED, 10, true);
        hoe.setItemMeta(meta4);

        ShapedRecipe ehoe = new ShapedRecipe(NamespacedKey.minecraft("effihoe"), new ItemStack(hoe));
        ehoe.shape(" T ", " B ", "DDD");

        ehoe.setIngredient('T', Material.NETHERITE_HOE);
        ehoe.setIngredient('B', Material.BEACON);
        ehoe.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(ehoe);

        //

        ItemStack shear = new ItemStack(Material.SHEARS);
        ItemMeta meta5 = shear.getItemMeta();

        meta5.setDisplayName(ChatColor.BLUE + "SHEARS");
        List<String> lore5 = new ArrayList<>();
        lore5.add(LanguagePlaceholder.LegalTool());
        lore5.add(LanguagePlaceholder.Effi10Tool());
        meta5.setLore(lore5);
        meta5.addEnchant(Enchantment.DIG_SPEED, 10, true);
        shear.setItemMeta(meta5);

        ShapedRecipe eshear = new ShapedRecipe(NamespacedKey.minecraft("effishear"), new ItemStack(shear));
        eshear.shape(" T ", " B ", "DDD");

        eshear.setIngredient('T', Material.SHEARS);
        eshear.setIngredient('B', Material.BEACON);
        eshear.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.getServer().addRecipe(eshear);
    }
    private void LightBlock () {

        ItemStack lighto = new ItemStack(Material.LIGHT);
        ItemMeta meta = lighto.getItemMeta();

        meta.setDisplayName(ChatColor.BLUE + "LIGHT");
        List<String> lore = new ArrayList<>();
        lore.add(LanguagePlaceholder.LegalTool());
        lore.add(LanguagePlaceholder.LightBlock());
        meta.setLore(lore);
        lighto.setItemMeta(meta);

        ShapedRecipe lighto1 = new ShapedRecipe(NamespacedKey.minecraft("lighto"), new ItemStack(lighto));
        lighto1.shape("GGG", "GLG", "GGG");

        lighto1.setIngredient('G', Material.GLASS_PANE);
        lighto1.setIngredient('L', Material.LANTERN);
        Bukkit.getServer().addRecipe(lighto1);

        //

        ItemStack lightb = new ItemStack(Material.LIGHT);
        ItemMeta meta1 = lightb.getItemMeta();

        meta1.setDisplayName(ChatColor.BLUE + "LIGHT");
        List<String> lore1 = new ArrayList<>();
        lore1.add(LanguagePlaceholder.LegalTool());
        lore1.add(LanguagePlaceholder.LightBlock());
        meta1.setLore(lore1);
        lightb.setItemMeta(meta1);

        ShapedRecipe lightb1 = new ShapedRecipe(NamespacedKey.minecraft("lightb"), new ItemStack(lightb));
        lightb1.shape("GGG", "GLG", "GGG");

        lightb1.setIngredient('G', Material.GLASS_PANE);
        lightb1.setIngredient('L', Material.SOUL_LANTERN);
        Bukkit.getServer().addRecipe(lightb1);

    }
    public void DebugStick () {

        ItemStack debug = new ItemStack(Material.DEBUG_STICK);
        ItemMeta meta1 = debug.getItemMeta();

        meta1.setDisplayName(ChatColor.BLUE + "DEBUG STICK");
        List<String> lore1 = new ArrayList<>();
        lore1.add(LanguagePlaceholder.LegalTool());
        lore1.add(LanguagePlaceholder.DebugStick());
        meta1.setLore(lore1);
        debug.setItemMeta(meta1);

        ShapedRecipe debug1 = new ShapedRecipe(NamespacedKey.minecraft("debugs"), new ItemStack(debug));
        debug1.shape("EBE", "ESE", "ENE");

        debug1.setIngredient('E', Material.EXPERIENCE_BOTTLE);
        debug1.setIngredient('B', Material.BLAZE_ROD);
        debug1.setIngredient('S', Material.STICK);
        debug1.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(debug1);

    }
    public void BetterProt () {
        ItemStack brust = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta meta5 = brust.getItemMeta();

        meta5.setDisplayName(ChatColor.BLUE + "Chestplate of Protection");
        List<String> lore5 = new ArrayList<>();
        lore5.add(LanguagePlaceholder.LegalTool());
        lore5.add(LanguagePlaceholder.BetterProt());
        meta5.setLore(lore5);
        meta5.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta5.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
        meta5.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
        meta5.addEnchant(Enchantment.PROTECTION_PROJECTILE, 10, true);
        brust.setItemMeta(meta5);

        ShapedRecipe ebrust = new ShapedRecipe(NamespacedKey.minecraft("protchestplate"), new ItemStack(brust));
        ebrust.shape("N N", "NNN", "NNN");

        ebrust.setIngredient('N', Material.NETHERITE_SCRAP);
        Bukkit.getServer().addRecipe(ebrust);

        //

        ItemStack hose = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta meta1 = hose.getItemMeta();

        meta1.setDisplayName(ChatColor.BLUE + "Leggins of Protection");
        List<String> lore2 = new ArrayList<>();
        lore2.add(LanguagePlaceholder.LegalTool());
        lore2.add(LanguagePlaceholder.BetterProt());
        meta1.setLore(lore2);
        meta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta1.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
        meta1.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
        meta1.addEnchant(Enchantment.PROTECTION_PROJECTILE, 10, true);
        hose.setItemMeta(meta1);

        ShapedRecipe ehose = new ShapedRecipe(NamespacedKey.minecraft("protleggins"), new ItemStack(hose));
        ehose.shape("NNN", "N N", "N N");

        ehose.setIngredient('N', Material.NETHERITE_SCRAP);
        Bukkit.getServer().addRecipe(ehose);

        //

        ItemStack schuhe = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta meta2 = schuhe.getItemMeta();

        meta2.setDisplayName(ChatColor.BLUE + "Boots of Protection");
        List<String> lore3 = new ArrayList<>();
        lore3.add(LanguagePlaceholder.LegalTool());
        lore3.add(LanguagePlaceholder.BetterProt());
        meta2.setLore(lore3);
        meta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta2.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
        meta2.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
        meta2.addEnchant(Enchantment.PROTECTION_PROJECTILE, 10, true);
        schuhe.setItemMeta(meta2);

        ShapedRecipe eschuhe = new ShapedRecipe(NamespacedKey.minecraft("protboots"), new ItemStack(schuhe));
        eschuhe.shape("   ", "N N", "N N");

        eschuhe.setIngredient('N', Material.NETHERITE_SCRAP);
        Bukkit.getServer().addRecipe(eschuhe);

        //

        ItemStack helm = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta meta3 = helm.getItemMeta();

        meta3.setDisplayName(ChatColor.BLUE + "Helmet of Protection");
        List<String> lore4 = new ArrayList<>();
        lore4.add(LanguagePlaceholder.LegalTool());
        lore4.add(LanguagePlaceholder.BetterProt());
        meta3.setLore(lore4);
        meta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta3.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
        meta3.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
        meta3.addEnchant(Enchantment.PROTECTION_PROJECTILE, 10, true);
        helm.setItemMeta(meta3);

        ShapedRecipe ehelm = new ShapedRecipe(NamespacedKey.minecraft("prothelmet"), new ItemStack(helm));
        ehelm.shape("NNN", "N N", "   ");

        ehelm.setIngredient('N', Material.NETHERITE_SCRAP);
        Bukkit.getServer().addRecipe(ehelm);
    }
}
