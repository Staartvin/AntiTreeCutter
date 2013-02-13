package team.craftmein.plugins.AntiTreeCutter;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExec implements CommandExecutor{

	AntiTreeCutter plugin;

	protected CommandExec(AntiTreeCutter plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandlabel, String[] args) {
		// /atc ban <player> <time> <world>
		// /atc ban Staartvin 10
		// /atc ban Staartvin asdkjahslkdjaskd\
		// /atc ban Staartvin 10 JeMoederAanDEPOEDER
		if (args.length == 3) {
			if (!sender.hasPermission("atc.ban")) {
				sender.sendMessage(ChatColor.RED + "You do not have the permission to ban a player from chopping trees!");
				return true;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You have to give a world!");
				return true;
			}
			Player player = (Player) sender;
			String bannedPlayer = args[1];
			String worldName = player.getWorld().getName();
			int time = 0;
			
			try {
				time = Integer.parseInt(args[2]);
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + args[2] + " is not a valid time format!");
				return true;
			}
			if (time <= 0) {
				sender.sendMessage(ChatColor.RED + "Time cannot be lower or equal to 0!");
				return true;
			}
			if (plugin.dCon.setBanned(bannedPlayer, worldName, time)) {
				sender.sendMessage(ChatColor.GREEN + "You have successfully banned " + bannedPlayer + " on world " + worldName + " for " + time + " minutes!");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Player " + bannedPlayer + " has already been banned on world " + worldName + "!");
			return true;
		}
		else if (args.length == 4) {
			if (!sender.hasPermission("atc.ban")) {
				sender.sendMessage(ChatColor.RED + "You do not have the permission to ban a player from chopping trees!");
				return true;
			}
			List<World> worlds = plugin.getServer().getWorlds();
			boolean isValidWorld = false;
			String worldName = args[3];
			
			for (World world: worlds) {
				String worldNames = world.getName(); 
				if (worldNames.equalsIgnoreCase(args[3])) {
					isValidWorld = true;
					worldName = worldNames;
				}
			}
			
			if (!isValidWorld) {
				sender.sendMessage(ChatColor.RED + "World " + args[3] + " is not a valid world!");
				return true;
			}
			
			String bannedPlayer = args[1];
			
			int time = 0;
			
			try {
				time = Integer.parseInt(args[2]);
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + args[2] + " is not a valid time format!");
				return true;
			}
			
			if (time <= 0) {
				sender.sendMessage(ChatColor.RED + "Time cannot be lower or equal to 0!");
				return true;
			}
			if (plugin.dCon.setBanned(bannedPlayer, worldName, time)) {
				sender.sendMessage(ChatColor.GREEN + "You have successfully banned " + bannedPlayer + " on world " + worldName + " for " + time + " minutes!");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Player " + bannedPlayer + " has already been banned on world " + worldName + "!");
			return true;
		}
		return false;
	}
}
