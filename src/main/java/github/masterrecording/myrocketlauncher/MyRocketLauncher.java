package github.masterrecording.myrocketlauncher;

import github.masterrecording.myrocketlauncher.commands.ReloadConfig;
import github.masterrecording.myrocketlauncher.commands.SetLauncher;
import github.masterrecording.myrocketlauncher.listeners.OnProjectileHit;
import github.masterrecording.myrocketlauncher.listeners.OnProjectileShoot;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MyRocketLauncher extends JavaPlugin {

    @Override
    public void onEnable(){

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString("messages.plugin_enabled"))));

        getServer().getPluginManager().registerEvents(new OnProjectileShoot(this), this);
        getServer().getPluginManager().registerEvents(new OnProjectileHit(this), this);

        Objects.requireNonNull(getCommand("set_launcher")).setExecutor(new SetLauncher(this));
        Objects.requireNonNull(getCommand("reload")).setExecutor(new ReloadConfig(this));
        Objects.requireNonNull(getCommand("give_rocket")).setExecutor(new ReloadConfig(this));
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString("messages.plugin_disabled"))));
    }
}
