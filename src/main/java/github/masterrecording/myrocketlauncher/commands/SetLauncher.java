package github.masterrecording.myrocketlauncher.commands;

import github.masterrecording.myrocketlauncher.MyRocketLauncher;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SetLauncher implements CommandExecutor {

    private final MyRocketLauncher plugin;

    public SetLauncher(MyRocketLauncher plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.isOp() || player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("permissions.set_launcher_permission")))) {
                ItemStack launcherItem = player.getInventory().getItemInMainHand();
                plugin.getConfig().set("rocket.launcher", launcherItem);
                plugin.saveConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.launcher_set"))));
                return true;
            }
        }else {
                ConsoleCommandSender console = plugin.getServer().getConsoleSender();
                console.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.not_player"))));
            }
        return false;
    }
}
