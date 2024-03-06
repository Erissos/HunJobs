package me.erisos.hunjobs;

import me.despical.commandframework.CommandFramework;
import me.erisos.hunjobs.commands.XpCommands;
import me.erisos.hunjobs.events.Woodcutter;
import me.erisos.hunjobs.user.DataCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.stream.Stream;

public final class HunJobs extends JavaPlugin {

    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        setupConfigurationFiles();
        commandFramework = new CommandFramework(this);

        getServer().getPluginManager().registerEvents(new DataCreator(),this);
        new XpCommands(this);
        new Woodcutter(this);
    }

    private void setupConfigurationFiles() {
        Stream.of("config", "messages", "data").filter(fileName -> !new File(getDataFolder(),fileName + ".yml").exists()).forEach(fileName -> this.saveResource(fileName + ".yml", false));
    }

    public CommandFramework getCommandFramework() {
        return commandFramework;
    }
}
