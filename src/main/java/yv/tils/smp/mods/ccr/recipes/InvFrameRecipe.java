package yv.tils.smp.mods.ccr.recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.internalapi.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @version 4.6.7
 * @since 4.6.7
 */
public class InvFrameRecipe {

    ItemStack InPut = new ItemStack(Material.ITEM_FRAME);
    ItemStack InPut2 = new ItemStack(Material.GLOW_ITEM_FRAME);
    ItemStack Upgrade1 = new ItemStack(Material.GLASS_PANE);
    ItemStack Upgrade2 = new ItemStack(Material.IRON_NUGGET);

    public boolean on_Input_NF(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new Log(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        ItemStack G1 = new ItemStack(Material.AIR);
        ItemStack G2 = new ItemStack(Material.AIR);
        ItemStack G3 = new ItemStack(Material.AIR);

        if (inv.getItem(20) != null) G1 = inv.getItem(20);
        if (inv.getItem(13) != null) G2 = inv.getItem(13);
        if (inv.getItem(31) != null) G3 = inv.getItem(31);

        if ((Objects.equals(G1.getType(), InPut.getType()) || Objects.equals(G1.getType(), InPut2.getType())) && Objects.equals(G2.getType(), Upgrade1.getType()) && Objects.equals(G3.getType(), Upgrade2.getType())) {
            return inv.getItem(20).getAmount() >= InPut.getAmount() || inv.getItem(20).getAmount() >= InPut.getAmount() && inv.getItem(13).getAmount() >= Upgrade1.getAmount() && inv.getItem(31).getAmount() >= Upgrade2.getAmount();
        }
        return false;
    }

    public boolean on_Input_GF(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut2.setItemMeta(inv.getItem(20).getItemMeta());

            new Log(inv.getItem(20).getItemMeta() + " " + InPut2.getItemMeta());
        }

        ItemStack G1 = new ItemStack(Material.AIR);
        ItemStack G2 = new ItemStack(Material.AIR);
        ItemStack G3 = new ItemStack(Material.AIR);

        if (inv.getItem(20) != null) G1 = inv.getItem(20);
        if (inv.getItem(13) != null) G2 = inv.getItem(13);
        if (inv.getItem(31) != null) G3 = inv.getItem(31);

        if ((Objects.equals(G1.getType(), InPut.getType()) || Objects.equals(G1.getType(), InPut2.getType())) && Objects.equals(G2.getType(), Upgrade1.getType()) && Objects.equals(G3.getType(), Upgrade2.getType())) {
            return inv.getItem(20).getAmount() >= InPut.getAmount() || inv.getItem(20).getAmount() >= InPut2.getAmount() && inv.getItem(13).getAmount() >= Upgrade1.getAmount() && inv.getItem(31).getAmount() >= Upgrade2.getAmount();
        }
        return false;
    }

    @NotNull
    private static BukkitObjectInputStream getDataInput() throws IOException {
        String invisMeta = "rO0ABXNyABpvcmcuYnVra2l0LnV0aWwuaW8uV3JhcHBlcvJQR+zxEm8FAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcgA1Y29tLmdvb2dsZS5jb21tb24uY29sbGVjdC5JbW11dGFibGVNYXAkU2VyaWFsaXplZEZvcm0AAAAAAAAAAAIAAkwABGtleXN0ABJMamF2YS9sYW5nL09iamVjdDtMAAZ2YWx1ZXNxAH4ABHhwdXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAABHQAAj09dAABdnQABHR5cGV0AARtZXRhdXEAfgAGAAAABHQAHm9yZy5idWtraXQuaW52ZW50b3J5Lkl0ZW1TdGFja3NyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAwwdAAKSVRFTV9GUkFNRXNxAH4AAHNxAH4AA3VxAH4ABgAAAANxAH4ACHQACW1ldGEtdHlwZXQACGludGVybmFsdXEAfgAGAAAAA3QACEl0ZW1NZXRhdAAKRU5USVRZX1RBR3QAREg0c0lBQUFBQUFBQS8rTmlZT0JpNEhUTks4a3NxUXhKVEdkazRQVE1LOHNzemt6S1NXVmtZQUFBd3RIM3BoNEFBQUE9";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(invisMeta));
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        return dataInput;
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

        ItemStack item = new ItemStack(Material.ITEM_FRAME);
        item.setAmount(4 * multiplier);
        List<String> lore = new ArrayList<>();

        ItemStack invis = null;
        try {
            //Gives Item Meta for the Invisible Item Frame
            BukkitObjectInputStream dataInput = getDataInput();

            invis = (ItemStack) dataInput.readObject();
            dataInput.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ItemMeta meta = invis.getItemMeta();

        if (YVtils.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§cPreview §8(§e" + 4 * multiplier + "x§8)");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("§eInvisible Item Frame");
            lore.add("Invisibility I");
        } else if (YVtils.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§cVorschau §8(§e" + 4 * multiplier + "x§8)");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("§eUnsichtbarer Rahmen");
            lore.add("Unsichtbarkeit I");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        //Recipe Checker
        ItemStack recipe_check = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta_recipe_check = recipe_check.getItemMeta();
        meta_recipe_check.setDisplayName(" ");
        recipe_check.setItemMeta(meta_recipe_check);

        for (int i : new int[]{10, 19, 28, 16, 25, 34}) {
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

        GInPut.setAmount((int) (GInPut.getAmount() - smallest));
        GUpgrade1.setAmount((int) (GUpgrade1.getAmount() - smallest));
        GUpgrade2.setAmount((int) (GUpgrade2.getAmount() - smallest));

        ItemStack item = new ItemStack(Material.ITEM_FRAME);
        item.setAmount(4 * multiplier);
        List<String> lore = new ArrayList<>();

        ItemStack invis = null;
        try {
            //Gives Item Meta for the Invisible Item Frame
            BukkitObjectInputStream dataInput = getDataInput();

            invis = (ItemStack) dataInput.readObject();
            dataInput.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ItemMeta meta = invis.getItemMeta();

        if (YVtils.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§eInvisible Item Frame");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Invisibility I");
        } else if (YVtils.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§eUnsichtbarer Rahmen");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Unsichtbarkeit I");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), item);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GInPut);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GUpgrade1);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), GUpgrade2);
    }
}
