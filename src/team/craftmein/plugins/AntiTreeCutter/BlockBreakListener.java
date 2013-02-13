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
		System.out.print("Ik kom hier!");
		if (block.getType() == Material.WOOD) {
			// Het is een hout blokje
			System.out.print("Ik kom hier 2!");
			if (plugin.dCon.getBannedUsers().contains(player.getName())) {
				
				System.out.print("Ik kom hier 3!");
				List<String> worlds = plugin.dCon.getWorlds(player.getName()); 
				if (!worlds.contains(player.getWorld().getName()) || worlds == null) return;
				System.out.print("Ik kom hier 5!");
				// De speler is gebanned. Hij kan dus niet hout breken.
				player.sendMessage(ChatColor.RED + "You cannot chop wood at the moment. You have been naughty!");
				
				event.setCancelled(true);
			}
			
		}
	}
}
