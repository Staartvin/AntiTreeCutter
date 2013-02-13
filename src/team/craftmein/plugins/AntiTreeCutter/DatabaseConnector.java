package team.craftmein.plugins.AntiTreeCutter;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

	AntiTreeCutter plugin;
	
	protected DatabaseConnector(AntiTreeCutter instance) {
		plugin = instance;
	}
	
	protected List<String> getBannedUsers() {
		List<Database> entries = plugin.getDatabase().find(Database.class).findList();
		
		List<String> entriesToString = new ArrayList<String>();
		for (int i=0; i<entries.size();i++) {
			String entryname = entries.get(i).getPlayerName();
			entriesToString.add(entryname);
		}
		return entriesToString;
	}
	
	protected List<String> getWorlds(String playerName) {
		List<Database> entry = plugin.getDatabase().find(Database.class).where().ieq("playerName", playerName).findList();
		
		if (entry == null) {
			return null;
		}
		List<String> entryToString = new ArrayList<String>();
		for (int i=0; i<entry.size();i++) {
			entryToString.add(entry.get(i).getWorld());
		}
		return entryToString;
	}
	
	protected boolean setBanned(String playerName, String world, Integer time) {
		Database entry = plugin.getDatabase().find(Database.class).where().ieq("playerName", playerName).ieq("world", world).findUnique();
		
		if (entry != null) {
			return false;
		}
		
		entry = new Database();
		entry.setPlayerName(playerName);
		entry.setWorld(world);
		entry.setMinutesLeft(time);
		
		plugin.getDatabase().save(entry);
		return true;
	}
}
