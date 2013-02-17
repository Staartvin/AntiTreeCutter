package team.craftmein.plugins.AntiTreeCutter;

import java.util.List;

public class TimeTask {

	AntiTreeCutter plugin;

	protected TimeTask(final AntiTreeCutter plugin) {
		this.plugin = plugin;
	}

	protected void runCheck() {
		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
		    @Override  
		    public void run() {
		        List<Database> databaseList = plugin.dCon.getFullDatabase();
		        
		        for (Database database: databaseList) {
		        	if (database.getMinutesLeft() - 1 == 0) {
		        		plugin.dCon.removeBanned(database.getPlayerName(), database.getWorld());
		        		continue;
		        	}
		        	else {
		        		database.setMinutesLeft(database.getMinutesLeft() - 1);
		        		plugin.getDatabase().save(database);
		        		continue;
		        	}
		        }
		    }
		}, 0L, 1200L);
	}
}
