package me.despawningbone.togglespawner;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
	
	private TSMain plugin;
	private FileConfiguration config;
	
	public static boolean debug = false, indirectPower = true;
	public static List<String> disabledWorlds = new ArrayList<String>();
	
	public ConfigHandler(TSMain instance) {
		plugin = instance;
		createFiles();
	}
	
	public void createFiles() {
		File configFile = new File(plugin.getDataFolder() + File.separator
				+ "config.yml");
		if (!configFile.exists()) {
			plugin.log.info("Cannot find config.yml, Generating now....");
			plugin.saveDefaultConfig();
			plugin.log.info("Config generated!");
		}
	}
	
	public void getConfigValues() {
		plugin.reloadConfig();
		config = plugin.getConfig();
		YamlConfiguration defcfg = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource("config.yml")));
		if(!defcfg.getKeys(true).equals(config.getKeys(true))) {
			plugin.log.warning("Config File's keys are not the same.");
			plugin.log.warning("This can mean that your configuration file is corrupted or was tempered with wrongly.");
			plugin.log.warning("Please reset or remove the config file in order for it to work properly.");
		}
		disabledWorlds = config.getStringList("Disabled-in-worlds");
		indirectPower = config.getBoolean("Allow-indirect-power");
		debug = config.getBoolean("debug");
	}
}
