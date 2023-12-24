package yv.tils.smp.mods.admin.vanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.mods.server.connect.PlayerJoin;
import yv.tils.smp.mods.server.connect.PlayerQuit;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static yv.tils.smp.utils.color.HexSupport.hex;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Vanish implements CommandExecutor {

    private static Vanish instance;
    public static Vanish getInstance() {return instance;}

    public static Map<UUID, Boolean> vanish = new HashMap<>(); //Default: false
    public static Map<UUID, Integer> layer = new HashMap<>(); //Default: 1
    public static Map<UUID, Boolean> itemPickup = new HashMap<>(); //Default: false
    public static Map<UUID, Boolean> invInteraction = new HashMap<>(); //Default: true
    public static Map<UUID, Boolean> mobTarget = new HashMap<>(); //Default: true

    // CMD: /v
    // CMD: /v [player]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        instance = this;

        Player player = (Player) sender;

        if (args.length > 1) {
            sendUsage(sender);
            return false;
        }

        if (args.length == 0) {
            if (!vanish.containsKey(player.getUniqueId())) vanish.put(player.getUniqueId(), false);
            if (!layer.containsKey(player.getUniqueId())) layer.put(player.getUniqueId(), 1);
            if (!itemPickup.containsKey(player.getUniqueId())) itemPickup.put(player.getUniqueId(), false);
            if (!invInteraction.containsKey(player.getUniqueId())) invInteraction.put(player.getUniqueId(), true);
            if (!mobTarget.containsKey(player.getUniqueId())) mobTarget.put(player.getUniqueId(), true);

            vanishGUI(player);
            return true;
        }
        return false;
    }

    private void vanishGUI(Player player) {

        //36 Slots
        //1. Row = Empty
        //2. Row = Slot 1, 3, 5, 9 EMPTY; Slot 2 = Vanish, Slot 4 = Layer, Slot 6 = ItemPickup, Slot 7 = InvInteraction, Slot 8 = MobTarget
        //3. Row = Slot 1, 3, 5, 9 EMPTY; Slot 2, 4, 6, 7. 8 = Toggle Item
        //4. Row = Empty

        Inventory inv = Bukkit.createInventory(player, 36, hex("#6D8896" + "Vanish"));

        //Vanish
        ItemStack vanish = new ItemStack(Material.POTION);
        PotionMeta meta_vanish = (PotionMeta) vanish.getItemMeta();
        meta_vanish.setDisplayName(hex("#96C8FF" + "Vanish"));
        meta_vanish.setColor(Color.fromRGB(246, 246, 246));
        meta_vanish.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        vanish.setItemMeta(meta_vanish);

        inv.setItem(10, vanish);

        //Layer
        ItemStack layer = new ItemStack(Material.FILLED_MAP);
        ItemMeta meta_layer = layer.getItemMeta();
        meta_layer.setDisplayName(hex("#96C8FF" + "Layer"));
        layer.setItemMeta(meta_layer);

        inv.setItem(12, layer);

        //ItemPickup
        ItemStack itemPickup = new ItemStack(Material.HOPPER);
        ItemMeta meta_itemPickup = itemPickup.getItemMeta();
        meta_itemPickup.setDisplayName(hex("#96C8FF" + "Item Pickup"));
        itemPickup.setItemMeta(meta_itemPickup);

        inv.setItem(14, itemPickup);

        //InvInteraction
        ItemStack invInteraction = new ItemStack(Material.LIGHT_GRAY_SHULKER_BOX);
        ItemMeta meta_invInteraction = invInteraction.getItemMeta();
        meta_invInteraction.setDisplayName(hex("#96C8FF" + "Inventory Interaction"));
        invInteraction.setItemMeta(meta_invInteraction);

        inv.setItem(15, invInteraction);

        //MobTarget
        ItemStack mobTarget = new ItemStack(Material.SPAWNER);
        ItemMeta meta_mobTarget = mobTarget.getItemMeta();
        meta_mobTarget.setDisplayName(hex("#96C8FF" + "Mob Target"));
        meta_mobTarget.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        mobTarget.setItemMeta(meta_mobTarget);

        inv.setItem(16, mobTarget);

        guiToggles(inv, player);

        //Filler
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta_pane = pane.getItemMeta();
        meta_pane.setDisplayName(" ");
        pane.setItemMeta(meta_pane);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, pane);
            }
        }

        player.openInventory(inv);
    }

    private void guiToggles(Inventory inv, Player player) {

        //Vanish Toggle
        if (!vanish.containsKey(player.getUniqueId())) {
            ItemStack vanishToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_vanishToggle = vanishToggle.getItemMeta();
            meta_vanishToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            vanishToggle.setItemMeta(meta_vanishToggle);

            inv.setItem(19, vanishToggle);
        }else if (vanish.get(player.getUniqueId())) {
            ItemStack vanishToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_vanishToggle = vanishToggle.getItemMeta();
            meta_vanishToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            vanishToggle.setItemMeta(meta_vanishToggle);

            inv.setItem(19, vanishToggle);
        } else {
            ItemStack vanishToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_vanishToggle = vanishToggle.getItemMeta();
            meta_vanishToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            vanishToggle.setItemMeta(meta_vanishToggle);

            inv.setItem(19, vanishToggle);
        }

        //Layer Toggle
        if (!layer.containsKey(player.getUniqueId())) {
            ItemStack layerToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_layerToggle = layerToggle.getItemMeta();
            meta_layerToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            layerToggle.setItemMeta(meta_layerToggle);

            inv.setItem(21, layerToggle);
        }else if (layer.get(player.getUniqueId()) == 1) {
            ItemStack layerToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_layerToggle = layerToggle.getItemMeta();
            meta_layerToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            layerToggle.setItemMeta(meta_layerToggle);

            inv.setItem(21, layerToggle);
            inv.getItem(12).setAmount(1);
        } else if (layer.get(player.getUniqueId()) == 2) {
            ItemStack layerToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_layerToggle = layerToggle.getItemMeta();
            meta_layerToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            layerToggle.setItemMeta(meta_layerToggle);

            inv.setItem(21, layerToggle);
            inv.getItem(12).setAmount(2);
        } else if (layer.get(player.getUniqueId()) == 3) {
            ItemStack layerToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_layerToggle = layerToggle.getItemMeta();
            meta_layerToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            layerToggle.setItemMeta(meta_layerToggle);

            inv.setItem(21, layerToggle);
            inv.getItem(12).setAmount(3);
        } else {
            ItemStack layerToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_layerToggle = layerToggle.getItemMeta();
            meta_layerToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            layerToggle.setItemMeta(meta_layerToggle);

            inv.setItem(21, layerToggle);
        }

        //ItemPickup Toggle
        if (!itemPickup.containsKey(player.getUniqueId())) {
            ItemStack itemPickupToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_itemPickupToggle = itemPickupToggle.getItemMeta();
            meta_itemPickupToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            itemPickupToggle.setItemMeta(meta_itemPickupToggle);

            inv.setItem(23, itemPickupToggle);
        }else if (itemPickup.get(player.getUniqueId())) {
            ItemStack itemPickupToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_itemPickupToggle = itemPickupToggle.getItemMeta();
            meta_itemPickupToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            itemPickupToggle.setItemMeta(meta_itemPickupToggle);

            inv.setItem(23, itemPickupToggle);
        } else {
            ItemStack itemPickupToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_itemPickupToggle = itemPickupToggle.getItemMeta();
            meta_itemPickupToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            itemPickupToggle.setItemMeta(meta_itemPickupToggle);

            inv.setItem(23, itemPickupToggle);
        }

        //InvInteraction Toggle
        if (!invInteraction.containsKey(player.getUniqueId())) {
            ItemStack invInteractionToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_invInteractionToggle = invInteractionToggle.getItemMeta();
            meta_invInteractionToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            invInteractionToggle.setItemMeta(meta_invInteractionToggle);

            inv.setItem(24, invInteractionToggle);
        }else if (invInteraction.get(player.getUniqueId())) {
            ItemStack invInteractionToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_invInteractionToggle = invInteractionToggle.getItemMeta();
            meta_invInteractionToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            invInteractionToggle.setItemMeta(meta_invInteractionToggle);

            inv.setItem(24, invInteractionToggle);
        } else {
            ItemStack invInteractionToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_invInteractionToggle = invInteractionToggle.getItemMeta();
            meta_invInteractionToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            invInteractionToggle.setItemMeta(meta_invInteractionToggle);

            inv.setItem(24, invInteractionToggle);
        }

        //MobTarget Toggle
        if (!mobTarget.containsKey(player.getUniqueId())) {
            ItemStack mobTargetToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_mobTargetToggle = mobTargetToggle.getItemMeta();
            meta_mobTargetToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            mobTargetToggle.setItemMeta(meta_mobTargetToggle);

            inv.setItem(25, mobTargetToggle);
        }else if (mobTarget.get(player.getUniqueId())) {
            ItemStack mobTargetToggle = new ItemStack(Material.LIME_DYE);
            ItemMeta meta_mobTargetToggle = mobTargetToggle.getItemMeta();
            meta_mobTargetToggle.setDisplayName(hex("#96C8FF" + "[ENABLED]"));
            mobTargetToggle.setItemMeta(meta_mobTargetToggle);

            inv.setItem(25, mobTargetToggle);
        } else {
            ItemStack mobTargetToggle = new ItemStack(Material.RED_DYE);
            ItemMeta meta_mobTargetToggle = mobTargetToggle.getItemMeta();
            meta_mobTargetToggle.setDisplayName(hex("#96C8FF" + "[DISABLED]"));
            mobTargetToggle.setItemMeta(meta_mobTargetToggle);

            inv.setItem(25, mobTargetToggle);
        }
    }

    public void invInteraction(InventoryClickEvent e) {
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        if (player.getOpenInventory().getTitle().equals("§x§6§d§8§8§9§6Vanish") && e.getInventory().getSize() == 36) {
            switch (e.getSlot()) {
                case 19 -> {
                    if (vanish.containsKey(player.getUniqueId())) {
                        vanishRegister(player, !vanish.get(player.getUniqueId()));
                    } else {
                        vanishRegister(player, true);
                    }
                }

                case 12 -> {
                    if (layer.containsKey(player.getUniqueId())) {
                        if (e.getClick().isLeftClick()) {
                            if (layer.get(player.getUniqueId()) == 1) {
                                vanishLayer(player, 2);
                            } else if (layer.get(player.getUniqueId()) == 2) {
                                vanishLayer(player, 3);
                            } else if (layer.get(player.getUniqueId()) == 3) {
                                vanishLayer(player, 1);
                            }
                        } else if (e.getClick().isRightClick()) {
                            if (layer.get(player.getUniqueId()) == 1) {
                                vanishLayer(player, 3);
                            } else if (layer.get(player.getUniqueId()) == 2) {
                                vanishLayer(player, 1);
                            } else if (layer.get(player.getUniqueId()) == 3) {
                                vanishLayer(player, 2);
                            }
                        }
                    }else {
                        vanishLayer(player, 1);
                    }
                }

                case 21 -> {
                    if (layer.containsKey(player.getUniqueId())) {
                        if (layer.get(player.getUniqueId()) == 1) {
                            vanishLayer(player, 4);
                        } else if (layer.get(player.getUniqueId()) == 2) {
                            vanishLayer(player, 4);
                        } else if (layer.get(player.getUniqueId()) == 3) {
                            vanishLayer(player, 4);
                        } else if (layer.get(player.getUniqueId()) == 4) {
                            vanishLayer(player, 1);
                        }
                    } else {
                        vanishLayer(player, 1);
                    }
                }

                case 23 -> {
                    if (itemPickup.containsKey(player.getUniqueId())) {
                        vanishItemPickup(player, !itemPickup.get(player.getUniqueId()));
                    } else {
                        vanishItemPickup(player, true);
                    }
                }

                case 24 -> {
                    if (invInteraction.containsKey(player.getUniqueId())) {
                        vanishInvInteraction(player, !invInteraction.get(player.getUniqueId()));
                    } else {
                        vanishInvInteraction(player, true);
                    }
                }

                case 25 -> {
                    if (mobTarget.containsKey(player.getUniqueId())) {
                        vanishMobTarget(player, !mobTarget.get(player.getUniqueId()));
                    } else {
                        vanishMobTarget(player, true);
                    }
                }
            }
            vanishGUI(player);
        }
    }

    private void vanishRegister(Player player, boolean vanish_bool) {
        UUID uuid = player.getUniqueId();
        vanish.put(uuid, vanish_bool);
    }

    private void vanishLayer(Player player, int layer_int) {
        UUID uuid = player.getUniqueId();
        layer.put(uuid, layer_int);
    }

    private void vanishItemPickup(Player player, boolean itemPickup_bool) {
        UUID uuid = player.getUniqueId();
        itemPickup.put(uuid, itemPickup_bool);
    }

    private void vanishInvInteraction(Player player, boolean invInteraction_bool) {
        UUID uuid = player.getUniqueId();
        invInteraction.put(uuid, invInteraction_bool);
    }

    private void vanishMobTarget(Player player, boolean mobTarget_bool) {
        UUID uuid = player.getUniqueId();
        mobTarget.put(uuid, mobTarget_bool);
    }

    public void guiClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (e.getView().getTitle().equals("§x§6§d§8§8§9§6Vanish") && e.getInventory().getSize() == 36) {
            if (vanish.get(player.getUniqueId())) {
                //Enable Vanish + Additions
                enableVanish(player);
            }else {
                //Disable Vanish + Additions
                disableVanish(player);
            }
        }
    }

    private void enableVanish(Player player) {
        String quitMessage= new PlayerQuit().generateQuitMessage(player);

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.hidePlayer(YVtils.getInstance(), player);
        }


        if (!layer.get(player.getUniqueId()).equals(4)) {
            for (var entry : layer.entrySet()) {
                if (entry.getValue() >= layer.get(player.getUniqueId())) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(YVtils.getInstance(), player);
                    }
                }
            }
        }

        if (!itemPickup.get(player.getUniqueId())) {
            player.setCanPickupItems(false);
        }

        if (!invInteraction.get(player.getUniqueId())) {
            // Silent Inventory Interaction
            // https://pastebin.com/LF7u1HvH <- Use as reference
        }

        player.setSleepingIgnored(true);

        Bukkit.broadcastMessage(quitMessage);
        player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ACTIVATE), List.of("PREFIX"), List.of(Prefix.PREFIX)));
    }

    private void disableVanish(Player player) {
        String joinMessage = new PlayerJoin().generateJoinMessage(player);

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.showPlayer(YVtils.getInstance(), player);
        }

        player.setSleepingIgnored(false);

        Bukkit.broadcastMessage(joinMessage);
        player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_DEACTIVATE), List.of("PREFIX"), List.of(Prefix.PREFIX)));
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE + "/v [player]");
    }
}