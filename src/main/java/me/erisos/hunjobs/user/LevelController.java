package me.erisos.hunjobs.user;

import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelController {

    private static final HunJobs plugin = JavaPlugin.getPlugin(HunJobs.class);

    public static void updateLevel(Player player, String job) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");

        config.set(player.getUniqueId() + "." + job + ".level", getLevel(player, job));
        ConfigUtils.saveConfig(plugin, config, "data");
    }

    public static int getLevel(Player player, String job) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        var xp = config.getInt(player.getUniqueId() + "." + job + ".xp");

        int level;

        if (xp >= 0 && xp <= 349) {
            level = 0;
        } else if (xp >= 350 && xp <= 599) {
            level = 1;
        } else if (xp >= 600 && xp <= 1199) {
            level = 2;
        } else if (xp >= 1200 && xp <= 1599) {
            level = 3;
        } else if (xp >= 1600 && xp <= 1999) {
            level = 4;
        } else if (xp >= 2000) {
            level = 5;
        } else {
            level = 6;
        }

        return level;
    }
}