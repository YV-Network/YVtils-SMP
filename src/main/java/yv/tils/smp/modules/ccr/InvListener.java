package yv.tils.smp.modules.ccr;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.ccr.recipes.InvFrameRecipe;
import yv.tils.smp.modules.ccr.recipes.LightBlockRecipe;
import yv.tils.smp.modules.ccr.recipes.TestRecipe;
import yv.tils.smp.modules.ccr.recipes.GlowingNetheriteElytra;
import yv.tils.smp.utils.configs.ConfigModeration;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class InvListener implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals("§9CCR - Custom Crafting Recipes")) {
            if (!SMPPlugin.getInstance().InvClose.contains(e.getPlayer().getUniqueId())) {
                if (e.getInventory().getItem(20) != null) e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation().add(0, 1, -0.25), e.getInventory().getItem(20));
                if (e.getInventory().getItem(13) != null) e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation().add(0, 1, -0.25), e.getInventory().getItem(13));
                if (e.getInventory().getItem(31) != null) e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation().add(0, 1, -0.25), e.getInventory().getItem(31));
            }
            SMPPlugin.getInstance().InvClose.remove(e.getPlayer().getUniqueId());
        }}

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§9CCR - Custom Crafting Recipes")) {
            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()) {
                case 36,37,38,39,40,41,42,43,44 -> {
                    ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta meta_pane = pane.getItemMeta();
                    meta_pane.setDisplayName(" ");
                    pane.setItemMeta(meta_pane);

                    if (e.getInventory().getItem(24) != null && e.getInventory().getItem(24).getType() != pane.getType()) {
                        if (new LightBlockRecipe().on_Input_NL(e.getInventory()) || new LightBlockRecipe().on_Input_SL(e.getInventory())) {
                            new LightBlockRecipe().outputDrop(e.getInventory(), player);
                        }else if (new InvFrameRecipe().on_Input_NF(e.getInventory()) || new InvFrameRecipe().on_Input_GF(e.getInventory())) {
                            new InvFrameRecipe().outputDrop(e.getInventory(), player);
                        }else if (new GlowingNetheriteElytra().on_Input(e.getInventory())) {
                            new GlowingNetheriteElytra().outputDrop(e.getInventory(), player);
                        }else if (new TestRecipe().on_Input(e.getInventory())) {
                            new TestRecipe().outputDrop(e.getInventory(), player);

                        }
                        e.setCancelled(true);
                        SMPPlugin.getInstance().InvClose.add(e.getWhoClicked().getUniqueId());
                        e.getWhoClicked().closeInventory();
                    }else {
                        e.setCancelled(true);
                    }}
                case 20,13,31 -> Bukkit.getScheduler().runTaskLater(SMPPlugin.getInstance(), () -> {
                    if (new LightBlockRecipe().on_Input_NL(e.getInventory()) || new LightBlockRecipe().on_Input_SL(e.getInventory()) && new ConfigModeration().ConfigRequest("FunModule").getBoolean("CCR.LightBlock")) {
                        e.getInventory().setItem(24, new LightBlockRecipe().on_Output_create(e.getInventory()));
                    }else if (new InvFrameRecipe().on_Input_NF(e.getInventory()) || new InvFrameRecipe().on_Input_GF(e.getInventory()) && new ConfigModeration().ConfigRequest("FunModule").getBoolean("CCR.InvisibleItemFrame")) {
                        e.getInventory().setItem(24, new InvFrameRecipe().on_Output_create(e.getInventory()));
                    }else if (new GlowingNetheriteElytra().on_Input(e.getInventory()) && new ConfigModeration().ConfigRequest("FunModule").getBoolean("CCR.GlowingNetheriteElytra")) {
                        e.getInventory().setItem(24, new GlowingNetheriteElytra().on_Output_create(e.getInventory()));



                    }else if (new TestRecipe().on_Input(e.getInventory())) {
                        e.getInventory().setItem(24, new TestRecipe().on_Output_create(e.getInventory()));




                    }else {
                        ItemStack recipe_check = new ItemStack(Material.RED_WOOL);
                        ItemMeta meta_recipe_check = recipe_check.getItemMeta();
                        meta_recipe_check.setDisplayName(" ");
                        recipe_check.setItemMeta(meta_recipe_check);

                        for (int i : new int[]{10,19,28,16,25,34}) {
                            e.getInventory().setItem(i, recipe_check);
                        }

                        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        ItemMeta meta_pane = pane.getItemMeta();
                        meta_pane.setDisplayName(" ");
                        pane.setItemMeta(meta_pane);

                        e.getInventory().setItem(24, pane);
                    }
                }, 1L);
                case 45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80 -> {
                }
                default -> e.setCancelled(true);
            }}}}