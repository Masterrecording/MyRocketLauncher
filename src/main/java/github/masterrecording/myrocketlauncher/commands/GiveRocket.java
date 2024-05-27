package github.masterrecording.myrocketlauncher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import github.masterrecording.myrocketlauncher.MyRocketLauncher;
import net.md_5.bungee.api.ChatColor;

public class GiveRocket implements CommandExecutor {

    private final MyRocketLauncher plugin; 

    public GiveRocket(MyRocketLauncher myRocketLauncher) {
        this.plugin = myRocketLauncher;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player)sender;
            if (player.isOp() || player.hasPermission(plugin.getConfig().getString("permissions.give_rocket_permission"))) {
                ItemStack rocketLauncher = plugin.getConfig().getItemStack("rocket.launcher");
                player.getInventory().addItem(rocketLauncher);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.rocket_gived")));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no_permission")));
            }
        }

        return true;
    }

}
