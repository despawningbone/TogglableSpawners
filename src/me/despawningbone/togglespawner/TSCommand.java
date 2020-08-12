package me.despawningbone.togglespawner;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TSCommand implements CommandExecutor {
	
	private TSMain plugin;
	
	public TSCommand(TSMain instance) {
		plugin = instance;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length > 0 && args[0].equalsIgnoreCase("reload")) {
			if(sender.hasPermission("togglespawner.reload") || sender.isOp()) {
				ConfigHandler configHandler = new ConfigHandler(plugin);
				configHandler.getConfigValues();
				sender.sendMessage(ChatColor.BLUE + "Togglable Spawners has been reloaded.");	
			} else { 
				sender.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		} else {
			sender.sendMessage("Unknown arguments.");
		}
		return true;
	}
}