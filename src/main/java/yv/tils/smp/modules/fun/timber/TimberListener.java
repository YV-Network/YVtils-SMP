package yv.tils.smp.modules.fun.timber;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import yv.tils.smp.logger.ConsoleLog;

import static org.bukkit.Bukkit.getServer;

/**
 * Copyright 2020 © Cheep-YT All rights reserved.
 *
 * Cheep-YT.com
 *
 * Project Name: Timber
 *
 * Referenced Library: spigot-1.8.8-R0.1-SNAPSHOT-latest.jar
 *
 * Class: com.cheep_yt.Coding.Timber
 *
 * @inWork
 *  @since 4.6.7
 *  @version 4.6.7
 *
 */
public class TimberListener implements Listener {

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
        if (!e.isDropItems()) return;
        if (e.getPlayer().hasPermission("yvtils.smp.module.fun.timber")) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.WOODEN_AXE) || e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STONE_AXE) || e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.IRON_AXE) || e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_AXE) || e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_AXE) || e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_AXE)) {
                    if (!e.getPlayer().isSneaking()) {
                        breakBlock(e.getBlock());
            }}}}}

    static void breakBlock(Block b) {

        String a = getServer().getClass().getPackage().getName();
        String version = a.substring(a.lastIndexOf('.') + 1);

        new ConsoleLog("Version: " + version);

        // Check
        if(version.equalsIgnoreCase("v1_19_R1")){

            if(b.getType() != Material.BIRCH_LOG && b.getType() != Material.ACACIA_LOG && b.getType() != Material.DARK_OAK_LOG && b.getType() != Material.JUNGLE_LOG && b.getType() != Material.OAK_LOG && b.getType() != Material.SPRUCE_LOG && b.getType() != Material.WARPED_STEM && b.getType() != Material.CRIMSON_STEM && b.getType() != Material.MANGROVE_LOG) {
                return;
            }
        }else {
            if(b.getType() != Material.BIRCH_LOG && b.getType() != Material.ACACIA_LOG && b.getType() != Material.DARK_OAK_LOG && b.getType() != Material.JUNGLE_LOG && b.getType() != Material.OAK_LOG && b.getType() != Material.SPRUCE_LOG && b.getType() != Material.WARPED_STEM && b.getType() != Material.CRIMSON_STEM) {
                return;
            }}

        b.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_FALL, 20, 1);

        b.breakNaturally();

        breakBlock(b.getLocation().add(0, 1, 0).getBlock());
        breakBlock(b.getLocation().add(1, 0, 0).getBlock());
        breakBlock(b.getLocation().add(0, 0, 1).getBlock());

        breakBlock(b.getLocation().subtract(0, 1, 0).getBlock());
        breakBlock(b.getLocation().subtract(1, 0, 0).getBlock());
        breakBlock(b.getLocation().subtract(0, 0, 1).getBlock());
    }
}