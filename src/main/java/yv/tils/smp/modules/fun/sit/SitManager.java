package yv.tils.smp.modules.fun.sit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SitManager {
    private static final Set<UUID> sittingPlayers = new HashSet<>();
    public void sit(Player player) {
        if (isSitting(player)) {
            return;
        }
        sittingPlayers.add(player.getUniqueId());
        sitDown(player.getLocation(), 0, 1.7, 0).addPassenger(player);
    }

    public void sitGetter(Player player) {
        if (player.getVehicle() instanceof final ArmorStand sit) {
            standUp(player, sit, 0, 1.7, 0);
        }
    }

    public void stairSit(Player player, Block block) {
        if (isSitting(player)) {
            return;
        }
        sittingPlayers.add(player.getUniqueId());
        sitDown(block.getLocation(), 0, 1.2, 0).addPassenger(player);
    }

    public void stairSitGetter(Player player, Block block) {
        if (player.getVehicle() instanceof final ArmorStand sit) {
            standUp(player, sit, 0, 1.2, 0);
        }
    }

    public void standUp(Player player, ArmorStand sit, double x, double y, double z) {
        if (!isSitting(player)) {
            return;
        }

        player.teleport(player.getLocation().add(x, y, z));
        sit.remove();
        sittingPlayers.remove(player.getUniqueId());
    }

    private ArmorStand sitDown(Location location, double x, double y, double z) {
        final World world = location.getWorld();
        final ArmorStand sit = (ArmorStand) world.spawnEntity(location.subtract(x, y, z), EntityType.ARMOR_STAND);

        sit.setInvulnerable(true);
        sit.setInvisible(true);
        sit.setGravity(false);

        return sit;
    }

    public boolean isSitting(Player player) {
        return sittingPlayers.contains(player.getUniqueId());
    }
}
