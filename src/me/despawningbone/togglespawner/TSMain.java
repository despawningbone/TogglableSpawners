package me.despawningbone.togglespawner;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class TSMain extends JavaPlugin {
	
	public Logger log;
	private TSListener listener = new TSListener(this);
	private ConfigHandler configHandler;
	
	String[] cmdAliases = {"ts", "togglespawner", "tspawner"};
	
	@Override
	public void onEnable() {
		log = getLogger();
		configHandler = new ConfigHandler(this);
		configHandler.getConfigValues();
		getServer().getPluginManager().registerEvents(listener, this);
		getCommand("togglablespawner").setExecutor(new TSCommand(this));
		getCommand("togglablespawner").setAliases(Arrays.asList(cmdAliases));
		log.info("Togglable spawners v" + getDescription().getVersion() + " by despawningbone has been enabled!");
	}
	
	@Override
	public void onDisable() {
		log.info("Disabled Togglable spawners v" + getDescription().getVersion() + ".");
	}
}
