package yv.tils.smp.modules.ccr.recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 4.6.8.1
 * @since 4.6.8.1
 * @Use: Create an unbreakable Elytra, costs you four Netherite Ingot and three Beacon
 */
public class GlowingNetheriteElytra {

    ItemStack InPut = new ItemStack(Material.ELYTRA);
    ItemStack Upgrade1 = new ItemStack(Material.NETHERITE_INGOT, 4);
    ItemStack Upgrade2 = new ItemStack(Material.BEACON, 3);

    public boolean on_Input(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new ConsoleLog(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        ItemStack G1 = new ItemStack(Material.AIR);
        ItemStack G2 = new ItemStack(Material.AIR);
        ItemStack G3 = new ItemStack(Material.AIR);

        if (inv.getItem(20) != null) G1 = inv.getItem(20);
        if (inv.getItem(13) != null) G2 = inv.getItem(13);
        if (inv.getItem(31) != null) G3 = inv.getItem(31);

        if (Objects.equals(G1.getType(), InPut.getType()) && Objects.equals(G2.getType(), Upgrade1.getType()) && Objects.equals(G3.getType(), Upgrade2.getType())) {
            if (inv.getItem(20).getAmount() >= InPut.getAmount() && inv.getItem(13).getAmount() >= Upgrade1.getAmount() && inv.getItem(31).getAmount() >= Upgrade2.getAmount()) {
                return true;
            }
        }
        return false;
    }

    public ItemStack on_Output_create(Inventory inv) {
        ItemStack GInPut = inv.getItem(20);
        ItemStack GUpgrade1 = inv.getItem(13);
        ItemStack GUpgrade2 = inv.getItem(31);

        int InN = GInPut.getAmount() / InPut.getAmount();
        int U1N = GUpgrade1.getAmount() / Upgrade1.getAmount();
        int U2N = GUpgrade2.getAmount() / Upgrade2.getAmount();
        double smallest = InN;
        if (smallest > U1N) smallest = U1N;
        if (smallest > U2N) smallest = U2N;
        int multiplier = (int) smallest;

        ItemStack item = new ItemStack(Material.ELYTRA);
        item.setAmount(1*multiplier);
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§cPreview §8(§e" + 1*multiplier + "x§8)");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("§eGlowing Netherite Elytra");
            lore.add("Unbreaking ထ");
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§cVorschau §8(§e" + 1*multiplier + "x§8)");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("§eLeuchtende Netherite Elytra");
            lore.add("Haltbarkeit ထ");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        //Recipe Checker
        ItemStack recipe_check = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta_recipe_check = recipe_check.getItemMeta();
        meta_recipe_check.setDisplayName(" ");
        recipe_check.setItemMeta(meta_recipe_check);

        for (int i : new int[]{10,19,28,16,25,34}) {
            inv.setItem(i, recipe_check);
        }

        return item;
    }

    public void outputDrop(Inventory inv, Player player) {
        ItemStack GInPut = inv.getItem(20);
        ItemStack GUpgrade1 = inv.getItem(13);
        ItemStack GUpgrade2 = inv.getItem(31);

        int InN = GInPut.getAmount() / InPut.getAmount();
        int U1N = GUpgrade1.getAmount() / Upgrade1.getAmount();
        int U2N = GUpgrade2.getAmount() / Upgrade2.getAmount();
        double smallest = InN;
        if (smallest > U1N) smallest = U1N;
        if (smallest > U2N) smallest = U2N;
        int multiplier = (int) smallest;

        GInPut.setAmount((int) (GInPut.getAmount()-smallest));
        GUpgrade1.setAmount((int) (GUpgrade1.getAmount()-(smallest*4)));
        GUpgrade2.setAmount((int) (GUpgrade2.getAmount()-(smallest*3)));

        ItemStack item = new ItemStack(Material.ELYTRA);
        item.setAmount(1*multiplier);
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§eGlowing Netherite Elytra");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Unbreaking ထ");
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§eLeuchtende Netherite Elytra");
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Haltbarkeit ထ");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), item);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GInPut);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GUpgrade1);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GUpgrade2);
    }
}