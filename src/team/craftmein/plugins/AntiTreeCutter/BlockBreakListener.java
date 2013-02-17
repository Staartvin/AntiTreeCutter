package team.craftmein.plugins.AntiTreeCutter;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

	AntiTreeCutter plugin;

	protected BlockBreakListener(AntiTreeCutter plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	protected void onBlockBreak(BlockBreakEvent event) {
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (block.getType() == Material.LOG) {
			// Het is een hout blokje
			if (plugin.dCon.getBannedUsers().contains(player.getName())) {
				
				List<String> worlds = plugin.dCon.getWorlds(player.getName()); 
				
				if (!worlds.contains(player.getWorld().getName()) || worlds == null) return;
				// De speler is gebanned. Hij kan dus niet hout breken.
				player.sendMessage(ChatColor.RED + "You cannot chop wood at the moment. You have been naughty!");
				player.sendMessage(ChatColor.RED + "You need to wait another " + plugin.dCon.getMinutesLeft(player.getName(), player.getWorld().getName()) + " minutes before you can chop again!");
				
				event.setCancelled(true);
			}
			
		}
	}
}
