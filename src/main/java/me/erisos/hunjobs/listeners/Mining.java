package me.erisos.hunjobs.listeners;

import me.erisos.hunjobs.HunJobs;
import me.erisos.hunjobs.user.LevelController;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mining implements Listener {

    private final HunJobs plugin;

    public Mining(HunJobs plugin) {
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

        if (block.getType().toString().contains("COAL_ORE")) {
            Random random = new Random();
            if (random.nextInt(100) < 5) {
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COAL, 3));
            }
        }

    }

    @EventHandler
    public void zümrütAydınlanması(BlockBreakEvent e) {
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

        if (!(LevelController.getLevel(player, "mining") >= 2)) {
            return;
        }

        if (block.getType().toString().contains("EMERALD_ORE")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 300, 0));
        }


    }

    @EventHandler
    public void tecrübeAkışı(BlockBreakEvent e) {
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

        if (!(LevelController.getLevel(player, "mining") >= 3)) {
            return;
        }

        var dia = block.getType().toString().contains("DIAMOND_ORE");
        var redstone = block.getType().toString().contains("REDSTONE_ORE");
        var lapis = block.getType().toString().contains("LAPIS_ORE");

        double XP_MULTIPLIER = 1.25;

        if (dia || redstone || lapis) {
            int oldXp = e.getExpToDrop();
            int newXp = (int) (oldXp * XP_MULTIPLIER);
        }
    }


    @EventHandler
    public void hemenFırına(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        var itemIn2ndHand = player.getInventory().getItemInOffHand().getType();
        var furnace = itemIn2ndHand == Material.BLAST_FURNACE;

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

        if (!(LevelController.getLevel(player, "mining") >= 3)) {
            return;
        }

        Enchantment fortune = Enchantment.LOOT_BONUS_BLOCKS;
        var level = player.getItemInHand().getEnchantmentLevel(fortune);

        var fortune1 = ThreadLocalRandom.current().nextInt(4) + 1;
        var fortune2 = ThreadLocalRandom.current().nextInt(6) + 3;
        var fortune3 = ThreadLocalRandom.current().nextInt(8) + 4;

        if (furnace) {
            switch (level) {
                case 1 -> {
                    switch (block.getType()) {
                        case DEEPSLATE_COPPER_ORE, COPPER_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COPPER_INGOT, fortune1));
                        }
                        case DEEPSLATE_IRON_ORE, IRON_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, fortune1));
                        }
                        case DEEPSLATE_GOLD_ORE, GOLD_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, fortune1));
                        }
                    }
                }
                case 2 -> {
                    switch (block.getType()) {
                        case DEEPSLATE_COPPER_ORE, COPPER_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COPPER_INGOT, fortune2));
                        }
                        case DEEPSLATE_IRON_ORE, IRON_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, fortune2));
                        }
                        case DEEPSLATE_GOLD_ORE, GOLD_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, fortune2));
                        }
                    }
                }
                case 3 -> {
                    switch (block.getType()) {
                        case DEEPSLATE_COPPER_ORE, COPPER_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COPPER_INGOT, fortune3));
                        }
                        case DEEPSLATE_IRON_ORE, IRON_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, fortune3));
                        }
                        case DEEPSLATE_GOLD_ORE, GOLD_ORE -> {
                            e.setDropItems(false);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, fortune3));
                        }
                    }
                }
            }
        }
    }

}
