package me.erisos.hunjobs.user;

import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class DataCreator implements Listener {

    HunJobs plugin = JavaPlugin.getPlugin(HunJobs.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");

        if (!config.contains(uuid.toString() + ".woodcutter")) {
            config.set(uuid.toString() + ".woodcutter", 0);
        }
        if (!config.contains(uuid.toString() + ".mining")) {
            config.set(uuid.toString() + ".mining", 0);
        }

        ConfigUtils.saveConfig(plugin, config, "messages");
    }

}
