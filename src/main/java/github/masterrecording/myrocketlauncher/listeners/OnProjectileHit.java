package github.masterrecording.myrocketlauncher.listeners;

import github.masterrecording.myrocketlauncher.MyRocketLauncher;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;

public class OnProjectileHit implements Listener {

    private final MyRocketLauncher plugin;

    public OnProjectileHit(MyRocketLauncher plugin) {
        this.plugin = plugin;
    }

    @EventHandler
public void onProjectileHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        if (Objects.equals(entity.getCustomName(), "rocket")) {
            entity.getWorld().createExplosion(entity.getLocation(), plugin.getConfig().getInt("rocket.explosion_power"), plugin.getConfig().getBoolean("rocket.spawn_fire"));
            entity.remove();
        }
    }
    }