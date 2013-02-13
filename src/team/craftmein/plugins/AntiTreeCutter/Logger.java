package team.craftmein.plugins.AntiTreeCutter;

public class Logger {

	AntiTreeCutter plugin;
	
	protected Logger(AntiTreeCutter instance) {
		plugin = instance;
	}
	
	public void logNormal(String message){
		System.out.print("[AntiTreeCutter] " + message);
	}
	
	public boolean logVerbose(String message){
		if (plugin.getConfig().getBoolean("Options.verboselogging")) {
			System.out.print("[AntiTreeCutter] " + message);
			return true;
		}
		return false;
	}
	
	public boolean debug(String message){
		if (plugin.getConfig().getBoolean("Options.debug")) {
			System.out.print("[AntiTreeCutter DEBUG] " + message);
			return true;
		}
		return false;
	}
}
