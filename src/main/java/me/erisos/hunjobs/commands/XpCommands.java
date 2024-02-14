package me.erisos.hunjobs.commands;

import me.despical.commandframework.Command;
import me.despical.commandframework.CommandArguments;
import me.despical.commandframework.Cooldown;
import me.despical.commons.configuration.ConfigUtils;
import me.erisos.hunjobs.HunJobs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class XpCommands {

    HunJobs plugin = JavaPlugin.getPlugin(HunJobs.class);

    @Command(
            name = "hunjobs.setxp",
            desc = "set job xp",
            permission = "hjobs.admin.setxp",
            max = 3,
            senderType = Command.SenderType.PLAYER
    )
    @Cooldown(
            cooldown = 3
    )
    public void setXp(CommandArguments arguments) {
        FileConfiguration config = ConfigUtils.getConfig(plugin, "data");
        arguments.getPlayer(0).ifPresentOrElse(p-> {
            UUID uuid = p.getUniqueId();

            if (arguments.getArgument(2).matches("-?\\d+(\\.\\d+)?")) {
                arguments.sendMessage("Sadece tam sayıları yazabilirsin.");
                return;
            }

            switch (arguments.getArgument(1)) {
                case "woodcutter":
                    if (config.contains(uuid.toString() + ".woodcutter")) {
                        config.set(uuid.toString() + ".woodcutter", arguments.getArgumentAsInt(2));
                    }
                    break;
                case "mining":
                    if (config.contains(uuid.toString() + ".mining")) {
                        config.set(uuid.toString() + ".mining", arguments.getArgumentAsInt(2));
                    }
                    break;
            }


        }, () -> arguments.sendMessage("Böyle bir oyuncu yok"));

    }
}
