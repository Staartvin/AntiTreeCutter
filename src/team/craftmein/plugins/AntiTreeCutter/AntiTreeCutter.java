package team.craftmein.plugins.AntiTreeCutter;


import org.bukkit.plugin.java.JavaPlugin;

public class AntiTreeCutter extends JavaPlugin {

	Logger log = new Logger(this);
	
	public void onEnable() {
		
		log.logNormal("AntiTreeCutter v" + getDescription().getVersion() + " has been enabled!");
	}
	
	public void onDisable() {
		// TEst
		log.logNormal("AntiTreeCutter v" + getDescription().getVersion() + " has been disabled!");
	}
}
