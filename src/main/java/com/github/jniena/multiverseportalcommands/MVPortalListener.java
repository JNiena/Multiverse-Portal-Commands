package com.github.jniena.multiverseportalcommands;

import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MVPortalListener implements Listener {

	private JavaPlugin plugin;

	public MVPortalListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onMVPortal(MVPortalEvent event) {
		executeCommands(event.getTeleportee(), getCommands(event.getSendingPortal().getName()));
		if (cancelTeleport(event.getSendingPortal().getName())) {
			event.setCancelled(true);
		}
	}

	private List<String> getCommands(String portalName) {
		return plugin.getConfig().getStringList("portals." + portalName + ".commands");
	}

	private void executeCommands(Player player, List<String> commands) {
		for (String command : commands) {
			player.getServer().dispatchCommand(player.getServer().getConsoleSender(), PlaceholderAPI.setPlaceholders(player, command));
		}
	}

	private boolean cancelTeleport(String portalName) {
		return !plugin.getConfig().getBoolean("portals." + portalName + ".teleport");
	}

}