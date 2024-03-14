package me.erisos.hunjobs.commands;

import me.despical.commandframework.Command;
import me.despical.commandframework.CommandArguments;
import me.despical.commandframework.Cooldown;
import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import me.erisos.hunjobs.user.LevelController;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.List;
import java.util.UUID;

public class XpCommands {

    private final HunJobs plugin;

    public XpCommands (HunJobs plugin) {
        this.plugin = plugin;
        plugin.getCommandFramework().registerCommands(this);
    }
    @Command(
            name = "hunjobs.setxp",
            desc = "set job xp",
            permission = "hjobs.admin.setxp",
            max = 3
    )
    @Cooldown(
            cooldown = 3
    )
    public void setXp(CommandArguments arguments) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        arguments.getPlayer(0).ifPresentOrElse(p-> {
            UUID uuid = p.getUniqueId();

            int xp = config.getInt(uuid + "." + arguments.getArgument(1) + ".xp");

            if (!arguments.isNumeric(arguments.getArgument(2))) {
                arguments.sendMessage("Sadece tam sayıları yazabilirsin.");
                return;
            }

            if (List.of("woodcutter", "mining", "fisherman", "hunter", "craftsman", "enchanter").contains(arguments.getArgument(1))) {
                config.set(uuid + "." + arguments.getArgument(1) + ".xp", arguments.getArgumentAsInt(2));
                ConfigUtils.saveConfig(plugin, config, "data");

                LevelController.updateLevel(arguments.getSender(), arguments.getArgument(1));
                return;
            }

            arguments.sendMessage("bla bla");
        }, () -> arguments.sendMessage("Böyle bir oyuncu yok"));

    }

    @Command(
            name = "hunjobs.addxp",
            desc = "add job xp",
            permission = "hjobs.admin.addxp",
            max = 3
    )
    @Cooldown(
            cooldown = 3
    )
    public void addXp(CommandArguments arguments) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        arguments.getPlayer(0).ifPresentOrElse(p-> {
            UUID uuid = p.getUniqueId();

            if (!arguments.isNumeric(arguments.getArgument(2))) {
                arguments.sendMessage("Sadece tam sayıları yazabilirsin.");
                return;
            }

            if (List.of("woodcutter", "mining", "fisherman", "hunter", "craftsman", "enchanter").contains(arguments.getArgument(1))) {
                config.set(uuid + "." + arguments.getArgument(1) + ".xp", config.getInt(uuid + "." + arguments.getArgument(1) + ".xp") + arguments.getArgumentAsInt(2));
                ConfigUtils.saveConfig(plugin, config, "data");

                LevelController.updateLevel(arguments.getSender(), arguments.getArgument(1));
                return;
            }
            arguments.sendMessage("bla bla");


        }, () -> arguments.sendMessage("Böyle bir oyuncu yok"));

    }


    @Command(
            name = "hunjobs.removexp",
            desc = "add job xp",
            permission = "hjobs.admin.removexp",
            max = 3
    )
    @Cooldown(
            cooldown = 3
    )
    public void removeXp(CommandArguments arguments) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        arguments.getPlayer(0).ifPresentOrElse(p-> {
            UUID uuid = p.getUniqueId();

            if (!arguments.isNumeric(arguments.getArgument(2))) {
                arguments.sendMessage("Sadece tam sayıları yazabilirsin.");
                return;
            }

            if (List.of("woodcutter", "mining", "fisherman", "hunter", "craftsman", "enchanter").contains(arguments.getArgument(1))) {
                config.set(uuid + "." + arguments.getArgument(1) + ".xp", config.getInt(uuid + "." + arguments.getArgument(1) + ".xp") - arguments.getArgumentAsInt(2));
                ConfigUtils.saveConfig(plugin, config, "data");

                LevelController.updateLevel(arguments.getSender(), arguments.getArgument(1));
                return;
            }
            arguments.sendMessage("bla bla");


        }, () -> arguments.sendMessage("Böyle bir oyuncu yok"));

    }

    @Command(
            name = "hunjobs.getmeta",
            desc = "get item meta",
            permission = "hjobs.admin.getmeta",
            max = 2
    )
    public void getMeta(CommandArguments arguments) {
        Player player = arguments.getSender();

        ItemMeta meta = player.getItemInHand().getItemMeta();

        Bukkit.broadcastMessage(String.valueOf(meta));
    }

}
