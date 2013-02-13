package team.craftmein.plugins.AntiTreeCutter;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class AntiTreeCutter extends JavaPlugin {

	Logger log = new Logger(this);
	DatabaseConnector dCon = new DatabaseConnector(this);
	
	public void onEnable() {
		
		setupDatabase();
		getCommand("atc").setExecutor(new CommandExec(this));
		getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
		
		log.logNormal("AntiTreeCutter v" + getDescription().getVersion() + " has been enabled!");
	}
	
	public void onDisable() {
		
		log.logNormal("AntiTreeCutter v" + getDescription().getVersion() + " has been disabled!");
	}
	
	private void setupDatabase() {
		try {
			getDatabase().find(Database.class).findRowCount();
		} catch (Exception e) {
			log.logNormal("Installing Database for the first time!");
			installDDL();
		}
	}
	
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Database.class);
        return list;
    }
}
