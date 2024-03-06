package me.erisos.hunjobs.user;

import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelController {

    private static final HunJobs plugin = JavaPlugin.getPlugin(HunJobs.class);

    public static void updateLevel(Player player, String job) {
        var config = ConfigUtils.getConfig(plugin, "data");

        config.set("", getLevel(player, job));
        ConfigUtils.saveConfig(plugin, config, "data");
    }

    public static int getLevel(Player player, String job) {
        var config = plugin.getConfig();
        var xp = config.getInt(player.getUniqueId() + "." + job + ".xp");

        int level;

        if (xp >= 0 && xp <= 99) {
            level = 1;
        } else if (xp <= 199) {
            level = 2;
        } else if (xp <= 399) {
            level = 3;
        }
        else {
            level = 0;
        }

        return level;
    }
}