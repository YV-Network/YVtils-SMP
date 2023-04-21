package yv.tils.smp.modules.fun.sit;

import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import yv.tils.smp.SMPPlugin;

/**
 * @BecomingRework ?
 * @since 4.6.7
 * @version 4.6.8
 */
public class StairClickListener implements Listener {
    public static final SitManager sitManager = SMPPlugin.sitManager;
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block clickedBlock = e.getClickedBlock();
        Action action = e.getAction();

        if(e.getHand() != EquipmentSlot.HAND || action != Action.RIGHT_CLICK_BLOCK || e.getPlayer().isSneaking() || e.getPlayer().isFlying()) return;

        if (Tag.STAIRS.isTagged(clickedBlock.getType())) {
            if (sitManager.isSitting(player)) {
                sitManager.stairSitGetter(player, clickedBlock);
            }else {
                sitManager.stairSit(player, clickedBlock);
            }
        }else {
            return;
        }}}