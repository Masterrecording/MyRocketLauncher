package github.masterrecording.myrocketlauncher.commands;

import github.masterrecording.myrocketlauncher.MyRocketLauncher;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {

    private final MyRocketLauncher plugin;

    public ReloadConfig(MyRocketLauncher plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.reloadConfig();
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.config_reloaded")));
        return true;
    }
}
