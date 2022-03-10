package com.github.jniena.multiverseportalcommands;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiversePortalCommands extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new MVPortalListener(this), this);
		PluginCommand command = getCommand("mvpcommands");
		if (command != null) {
			ReloadCommand reloadCommand = new ReloadCommand(this);
			command.setExecutor(reloadCommand);
			command.setTabCompleter(reloadCommand);
		}
	}

}