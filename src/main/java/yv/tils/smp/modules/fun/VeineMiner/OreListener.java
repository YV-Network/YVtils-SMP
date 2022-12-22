package yv.tils.smp.modules.fun.VeineMiner;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class OreListener implements Listener {

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().hasPermission("yvtils.smp.module.fun.veinemine")) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                    if (!e.getPlayer().isSneaking()) {
                        if (e.isDropItems()) {

                            //
                            breakBlock(e.getBlock(), e.getPlayer());
                            return;
                        }}}}}

    static void breakBlock(Block b, Player p) {
        if (b.getType() != Material.COAL_ORE && b.getType() != Material.DEEPSLATE_COAL_ORE && b.getType() != Material.NETHER_QUARTZ_ORE && b.getType() != Material.NETHER_GOLD_ORE && b.getType() != Material.IRON_ORE && b.getType() != Material.DEEPSLATE_IRON_ORE && b.getType() != Material.LAPIS_ORE && b.getType() != Material.DEEPSLATE_LAPIS_ORE && b.getType() != Material.EMERALD_ORE && b.getType() != Material.DEEPSLATE_EMERALD_ORE && b.getType() != Material.COPPER_ORE && b.getType() != Material.DEEPSLATE_COPPER_ORE && b.getType() != Material.DIAMOND_ORE && b.getType() != Material.DEEPSLATE_DIAMOND_ORE && b.getType() != Material.REDSTONE_ORE && b.getType() != Material.DEEPSLATE_REDSTONE_ORE && b.getType() != Material.ANCIENT_DEBRIS) {
            return;
        }

        ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
        Damageable damage_nounbreaking = (Damageable) meta;
        damage_nounbreaking.setDamage(damage_nounbreaking.getDamage() + 1);

        Damageable damage_unbreaking1 = (Damageable) meta;
        //damage_nounbreaking.setDamage(damage_unbreaking1.getDamage() + 0.6);

        Damageable damage_unbreaking2 = (Damageable) meta;
        //damage_nounbreaking.setDamage(damage_unbreaking2.getDamage() + 0.2);

        Damageable damage_unbreaking3 = (Damageable) meta;
        //damage_nounbreaking.setDamage(damage_unbreaking3.getDamage() + 0.1);


        int l = p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY);

        System.out.println(p.getInventory().getItemInMainHand().getDurability());
        //p.getInventory().getItemInMainHand().setDurability((short) (p.getInventory().getItemInMainHand().getDurability() + 10));

        if (l == 0) {
            p.getInventory().getItemInMainHand().setItemMeta(damage_nounbreaking);
        }else if (l == 1) {
            p.getInventory().getItemInMainHand().setItemMeta(damage_unbreaking1); //0.9
        } else if (l == 2) {
            p.getInventory().getItemInMainHand().setItemMeta(damage_unbreaking2); //0.6
        } else if (l == 3) {
            p.getInventory().getItemInMainHand().setItemMeta(damage_unbreaking3); //0.3
        }

        b.getWorld().playSound(b.getLocation(), Sound.BLOCK_STONE_BREAK, 20, 1);

        b.breakNaturally();


        breakBlock(b.getLocation().add(0, 1, 0).getBlock(), p);
        breakBlock(b.getLocation().add(1, 0, 0).getBlock(), p);
        breakBlock(b.getLocation().add(0, 0, 1).getBlock(), p);

        breakBlock(b.getLocation().subtract(0, 1, 0).getBlock(), p);
        breakBlock(b.getLocation().subtract(1, 0, 0).getBlock(), p);
        breakBlock(b.getLocation().subtract(0, 0, 1).getBlock(), p);
    }}