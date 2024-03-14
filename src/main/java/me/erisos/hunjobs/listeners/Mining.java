package me.erisos.hunjobs.listeners;

import me.erisos.hunjobs.HunJobs;
import me.erisos.hunjobs.user.LevelController;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

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

        var itemInHand = player.getItemInHand().getType();
        var PAxe1 = itemInHand == Material.WOODEN_PICKAXE;
        var PAxe2 = itemInHand == Material.STONE_PICKAXE;
        var PAxe3 = itemInHand == Material.IRON_PICKAXE;
        var PAxe4 = itemInHand == Material.GOLDEN_PICKAXE;
        var PAxe5 = itemInHand == Material.DIAMOND_PICKAXE;
        var PAxe6 = itemInHand == Material.NETHERITE_PICKAXE;

        if (!(PAxe1 || PAxe2 || PAxe3 || PAxe4 || PAxe5 || PAxe6)) {
            return;
        }

        if (!(LevelController.getLevel(player, "mining") >= 1)) {
            return;
        }

        if(block.getType().toString().contains("COAL_ORE")) {
            Random random = new Random();
            if (random.nextInt(100) < 5) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COAL, 3));
            }
        }

    }
}
