package github.masterrecording.myrocketlauncher.listeners;

import github.masterrecording.myrocketlauncher.MyRocketLauncher;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class OnProjectileShoot implements Listener {

    private final MyRocketLauncher plugin;

    public OnProjectileShoot(MyRocketLauncher plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileShoot(ProjectileLaunchEvent event) {
        try {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                ItemStack launcherItem = plugin.getConfig().getItemStack("rocket.launcher");
                if (plugin.getConfig().getBoolean("rocket.permission_required")) {
                    if (player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("permissions.use_launcher_permission")))) {
                        createRocket(event, player, launcherItem);
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.no_permission"))));
                    }
                } else {
                    createRocket(event, player, launcherItem);
                }
            }
        }catch (NullPointerException e) {
            ConsoleCommandSender console = plugin.getServer().getConsoleSender();
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere is an error with the config.yml (If don't know how to fix it, delete the file and restart the server)"));
        }
    }

    private void createRocket(ProjectileLaunchEvent event, Player player, ItemStack launcherItem) {
        ItemMeta playerItem = player.getInventory().getItemInMainHand().getItemMeta();
        ItemMeta rocketItem = launcherItem.getItemMeta();
        if (playerItem.getItemName().equals(rocketItem.getItemName()) && Objects.equals(playerItem.getLore(), rocketItem.getLore())) {
            event.getEntity().setCustomName("rocket");
            event.getEntity().setCustomNameVisible(false);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.rocket_launched"))));
        }
    }
}
