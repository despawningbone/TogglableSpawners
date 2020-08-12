package me.despawningbone.togglespawner;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class TSListener implements Listener {
	
	private TSMain plugin;

	public TSListener(TSMain instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onSpawnerSpawn(SpawnerSpawnEvent event) {
		if(ConfigHandler.disabledWorlds.isEmpty() || !ConfigHandler.disabledWorlds.contains(event.getLocation().getWorld().getName())) {
			Block block = event.getSpawner().getBlock();
			if(block.getBlockPower() != 0 && (ConfigHandler.indirectPower || block.isBlockPowered())) {
				event.setCancelled(true);
				String mobname = event.getSpawner().getCreatureTypeName().toLowerCase();
				if(ConfigHandler.debug) {
					Location loc = event.getSpawner().getLocation();
					plugin.log.info((Character.toUpperCase(mobname.charAt(0)) + mobname.substring(1)) + " spawner at world "  + loc.getWorld().getName() + ":" + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() +  " is powered! Cancelling spawn event...");	
				}
			}	
		}
	}
	
}
