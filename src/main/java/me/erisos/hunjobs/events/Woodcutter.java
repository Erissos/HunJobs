package me.erisos.hunjobs.events;

import me.erisos.hunjobs.HunJobs;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
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

        if (block.getType() == Material.OAK_LOG && !player.isSneaking()) {

            Block aboveBlock = block.getRelative(BlockFace.UP);
            Block belowBlock = block.getRelative(BlockFace.DOWN);
            if (aboveBlock.getType() != Material.OAK_LOG || belowBlock.getType() == Material.DIRT || belowBlock.getType() == Material.GRASS_BLOCK) {
                block.getMetadata("woodData").forEach(data -> System.out.println(data.value()));
                if(block.getMetadata("woodData").stream().anyMatch(MetadataValue::asBoolean)) {
                    return;
                }
                Random random = new Random();
                if (random.nextInt(100) < 1) {
                    var value = new FixedMetadataValue(plugin, true);
                    var dropWood = block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.OAK_LOG, 1));
                    block.setMetadata("woodData", value);
                    dropWood.setMetadata("woodData", value);
                }
            }
        }
    }

    @EventHandler
    public void yenilenenDünya(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

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

        if (block.getType() == Material.OAK_LOG && !player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30 ,0) );
        }
    }

    @EventHandler
    public void elmacıGüzeli(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        var itemInHand = player.getItemInHand().getType();
        var Hoe1 = itemInHand == Material.WOODEN_HOE;
        var Hoe2 = itemInHand == Material.IRON_HOE;
        var Hoe3 = itemInHand == Material.DIAMOND_HOE;
        var Hoe4 = itemInHand == Material.GOLDEN_HOE;
        var Hoe5 = itemInHand == Material.NETHERITE_HOE;
        var Shears = itemInHand == Material.SHEARS;
        var hand = itemInHand == Material.AIR;

        if (Hoe1 || Hoe2 || Hoe3 || Hoe4 || Hoe5 || Shears || hand) {
            if (List.of(Material.OAK_LEAVES, Material.BIRCH_LEAVES).contains(block.getType())) {
                Random random = new Random();
                if (random.nextInt(100) < 10) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.APPLE, 1));
                }
            }
        }


    }
}
