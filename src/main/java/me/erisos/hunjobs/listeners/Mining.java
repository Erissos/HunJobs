package me.erisos.hunjobs.listeners;

import me.erisos.hunjobs.HunJobs;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Mining implements Listener {

    private final HunJobs plugin;

    public Mining (HunJobs plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void kömürKarası(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();


    }
}
