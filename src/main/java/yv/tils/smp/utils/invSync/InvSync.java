package yv.tils.smp.utils.invSync;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.admin.invsee.EcSee;
import yv.tils.smp.mods.admin.invsee.InvSee;
import yv.tils.smp.utils.MojangAPI;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import static yv.tils.smp.utils.invSync.InvOpen.containerLocation;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class InvSync {

    public void invChange(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        Bukkit.getScheduler().runTaskLater(YVtils.getInstance(), () -> {
            copyChange(player, e.getInventory().getLocation(), e.getInventory());
            originalChange(player, e.getInventory().getLocation(), e.getInventory());
        }, 1L);
    }

    private void copyChange(Player player, Location location, Inventory inv) {
        if (location != null) return;

        String invsee_inv = LanguageFile.getMessage(LanguageMessage.MODULE_INVSEE_INVENTORY);
        String invsee_ec = LanguageFile.getMessage(LanguageMessage.MODULE_INVSEE_ENDERCHEST);
        invsee_inv = invsee_inv.replace(" PLAYER", "");
        invsee_ec = invsee_ec.replace(" PLAYER", "");

        if (player.getOpenInventory().getTitle().startsWith(invsee_inv)) {
            Player target = Bukkit.getPlayer(MojangAPI.UUID2Name(InvSee.invSee.get(player.getUniqueId())));

            playerInvEdit(inv, target);
        }

        if (player.getOpenInventory().getTitle().startsWith(invsee_ec)) {
            Player target = Bukkit.getPlayer(MojangAPI.UUID2Name(EcSee.ecSee.get(player.getUniqueId())));

            target.getEnderChest().setContents(inv.getContents());
        }

        if (!player.getOpenInventory().getTitle().equals("Container Clone")) return;

        Location _containerLocation = containerLocation.get(player.getUniqueId());
        if (_containerLocation == null) return;

        if (_containerLocation.getBlock().getType().equals(Material.CHEST)) {
            Chest container = (Chest) _containerLocation.getBlock().getState();
            container.getInventory().setContents(inv.getContents());
        } else if (_containerLocation.getBlock().getType().equals(Material.BARREL)) {
            Barrel container = (Barrel) _containerLocation.getBlock().getState();
            container.getInventory().setContents(inv.getContents());
        } else if (_containerLocation.getBlock().getType().equals(Material.SHULKER_BOX)) {
            ShulkerBox container = (ShulkerBox) _containerLocation.getBlock().getState();
            container.getInventory().setContents(inv.getContents());
        }
    }

    private void originalChange(Player player, Location location, Inventory inv) {
        if (location == null) return;

        if (inv.getType().equals(InventoryType.CRAFTING)) {
            for (var entry : InvSee.invSee.entrySet()) {
                if (entry.getValue().equals(player.getUniqueId())) {
                    Player containerSpy = Bukkit.getPlayer(MojangAPI.UUID2Name(entry.getKey()));
                    containerSpy.getOpenInventory().getTopInventory().setContents(inv.getContents());
                }
            }
        }

        if (inv.getType().equals(InventoryType.ENDER_CHEST)) {
            for (var entry : EcSee.ecSee.entrySet()) {
                if (entry.getValue().equals(player.getUniqueId())) {
                    Player containerSpy = Bukkit.getPlayer(MojangAPI.UUID2Name(entry.getKey()));
                    containerSpy.getOpenInventory().getTopInventory().setContents(inv.getContents());
                }
            }
        }

        if (player.getOpenInventory().getTitle().equals("Container Clone")) return;

        for (var entry : containerLocation.entrySet()) {
            if (entry.getValue().equals(location)) {
                Player containerSpy = Bukkit.getPlayer(MojangAPI.UUID2Name(entry.getKey()));
                containerSpy.getOpenInventory().getTopInventory().setContents(inv.getContents());
            }
        }
    }

    private void playerInvEdit(Inventory inv, Player player) {
        ItemStack[] invContent;
        ItemStack[] armour;
        ItemStack offHand;

        offHand = inv.getItem(53);
        armour = new ItemStack[]{inv.getItem(48), inv.getItem(47), inv.getItem(46), inv.getItem(45)};
        invContent = new ItemStack[]{
                inv.getItem(0), inv.getItem(1), inv.getItem(2),
                inv.getItem(3), inv.getItem(4), inv.getItem(5),
                inv.getItem(6), inv.getItem(7), inv.getItem(8),
                inv.getItem(9), inv.getItem(10), inv.getItem(11),
                inv.getItem(12), inv.getItem(13), inv.getItem(14),
                inv.getItem(15), inv.getItem(16), inv.getItem(17),
                inv.getItem(18), inv.getItem(19), inv.getItem(20),
                inv.getItem(21), inv.getItem(22), inv.getItem(23),
                inv.getItem(24), inv.getItem(25), inv.getItem(26),
                inv.getItem(27), inv.getItem(28), inv.getItem(29),
                inv.getItem(30), inv.getItem(31), inv.getItem(32),
                inv.getItem(33), inv.getItem(34), inv.getItem(35)
        };

        player.getInventory().setContents(invContent);
        player.getInventory().setItemInOffHand(offHand);
        player.getInventory().setArmorContents(armour);
        player.updateInventory();
    }
}