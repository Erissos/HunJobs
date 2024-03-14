package me.erisos.hunjobs.listeners;

import me.erisos.hunjobs.HunJobs;
import me.erisos.hunjobs.user.LevelController;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Woodcutter implements Listener {

    private final HunJobs plugin;

    public Woodcutter (HunJobs plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void çifteŞans(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        Block aboveBlock = block.getRelative(BlockFace.UP);
        Block belowBlock = block.getRelative(BlockFace.DOWN);

        var itemInHand = player.getItemInHand().getType();
        var Axe1 = itemInHand == Material.WOODEN_AXE;
        var Axe2 = itemInHand == Material.STONE_AXE;
        var Axe3 = itemInHand == Material.IRON_AXE;
        var Axe4 = itemInHand == Material.GOLDEN_AXE;
        var Axe5 = itemInHand == Material.DIAMOND_AXE;
        var Axe6 = itemInHand == Material.NETHERITE_AXE;

        if (!(Axe1 || Axe2 || Axe3 || Axe4 || Axe5 || Axe6)) {
            return;
        }

        if (!(LevelController.getLevel(player, "woodcutter") >= 1)) {
            return;
        }

        if (block.getType().toString().contains("_LOG") && !player.isSneaking()) {

            if (aboveBlock.getType() == Material.OAK_LOG || aboveBlock.getType() == Material.OAK_LEAVES || belowBlock.getType() == Material.DIRT || belowBlock.getType() == Material.OAK_LOG) {

                    Random random = new Random();
                    if (random.nextInt(100) < 69) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(block.getType()));
                    }
           }
        }
    }

    @EventHandler
    public void yenilenenDünya(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        var itemInHand = player.getItemInHand().getType();
        var Axe1 = itemInHand == Material.WOODEN_AXE;
        var Axe2 = itemInHand == Material.STONE_AXE;
        var Axe3 = itemInHand == Material.IRON_AXE;
        var Axe4 = itemInHand == Material.GOLDEN_AXE;
        var Axe5 = itemInHand == Material.DIAMOND_AXE;
        var Axe6 = itemInHand == Material.NETHERITE_AXE;

        if (!(Axe1 || Axe2 || Axe3 || Axe4 || Axe5 || Axe6)) {
            return;
        }

        if (!(LevelController.getLevel(player, "woodcutter") >= 2)) {
            return;
        }

        if (block.getType() == Material.OAK_LOG && !player.isSneaking()) {

            Block aboveBlock = block.getRelative(BlockFace.UP);
            Block belowBlock = block.getRelative(BlockFace.DOWN);
            if (aboveBlock.getType() == Material.OAK_LOG && belowBlock.getType() == Material.DIRT) {

                Random random = new Random();
                if (random.nextInt(100) < 15) {
                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> block.setType(Material.OAK_SAPLING), 10);
                }
            }
        }
    }

    @EventHandler
    public void güçlüBilekler(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        var itemInHand = player.getItemInHand().getType();
        var Axe1 = itemInHand == Material.WOODEN_AXE;
        var Axe2 = itemInHand == Material.STONE_AXE;
        var Axe3 = itemInHand == Material.IRON_AXE;
        var Axe4 = itemInHand == Material.GOLDEN_AXE;
        var Axe5 = itemInHand == Material.DIAMOND_AXE;
        var Axe6 = itemInHand == Material.NETHERITE_AXE;

        if (!(Axe1 || Axe2 || Axe3 || Axe4 || Axe5 || Axe6)) {
            return;
        }

        if (!(LevelController.getLevel(player, "woodcutter") >= 3)) {
            return;
        }

        if (block.getType() == Material.OAK_LOG && !player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30 ,0) );
        }
    }

    @EventHandler
    public void elmacıGüzeli(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (!(LevelController.getLevel(player, "woodcutter") >= 4)) {
            return;
        }

        var itemInHand = player.getItemInHand().getType();
        var Hoe1 = itemInHand == Material.WOODEN_HOE;
        var Hoe2 = itemInHand == Material.IRON_HOE;
        var Hoe3 = itemInHand == Material.GOLDEN_HOE;
        var Hoe4 = itemInHand == Material.DIAMOND_HOE;
        var Hoe5 = itemInHand == Material.NETHERITE_HOE;
        var HoeS = itemInHand == Material.STONE_HOE;
        var Shears = itemInHand == Material.SHEARS;
        var hand = itemInHand == Material.AIR;

        if (Hoe1 || Hoe2 || Hoe3 || Hoe4 || Hoe5 || HoeS || Shears || hand) {
            if (block.getType().toString().contains("_LEAVES")) {
                Random random = new Random();
                if (random.nextInt(100) < 10) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.APPLE, 1));
                }
            }
        }

    }

    @EventHandler
    public void köktenMüdahale(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (!(LevelController.getLevel(player, "woodcutter") >= 5)) {
            return;
        }

    }
}
