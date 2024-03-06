package me.erisos.hunjobs.user;

import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class DataCreator implements Listener {

    HunJobs plugin = JavaPlugin.getPlugin(HunJobs.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        List.of("woodcutter", "mining", "hunter", "fisherman", "enchanter").forEach(job -> {
            config.set(uuid + ".%s.level".formatted(job), 0);
            config.set(uuid + ".%s.xp".formatted(job), 0);
        });

        ConfigUtils.saveConfig(plugin, config, "data");
    }

}
// craftsman,enchanter,woodcutter,mining,fisherman,hunter
