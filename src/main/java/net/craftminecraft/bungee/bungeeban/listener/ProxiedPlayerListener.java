package net.craftminecraft.bungee.bungeeban.listener;

import com.google.common.eventbus.Subscribe;

import net.craftminecraft.bungee.bungeeban.BanManager;
import net.craftminecraft.bungee.bungeeban.BungeeBan;
import net.craftminecraft.bungee.bungeeban.banstore.BanEntry;
import net.craftminecraft.bungee.bungeeban.util.Utils;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;

public class ProxiedPlayerListener implements Listener {
	BungeeBan plugin;
	public ProxiedPlayerListener(BungeeBan plugin) {
		this.plugin = plugin;
	}
	
	@Subscribe
	public void onPlayerJoin(PostLoginEvent e) {
		BanEntry ban = BanManager.getBan(e.getPlayer().getName(), "(GLOBAL)");
		if (ban != null) {
			e.getPlayer().disconnect(Utils.formatMessage(ban.getReason(), ban));
			return;
		}
		ban = BanManager.getBan(e.getPlayer().getAddress().getAddress().getHostAddress(), "(GLOBAL)");
		if (ban != null) {
            e.getPlayer().disconnect(Utils.formatMessage(ban.getReason(), ban));
            return;
		}
	}
	
	@Subscribe
	public void onServerConnect(ServerConnectEvent e) {
		BanEntry ban = BanManager.getBan(e.getPlayer().getName(), e.getTarget().getName());
		if (ban != null) {
			// Ugly workaround the player joined... player left messages
			Server srv = e.getPlayer().getServer();
			if (srv != null)
				e.setTarget(srv.getInfo());
			e.getPlayer().disconnect(Utils.formatMessage(ban.getReason(), ban));
			return;
		} 
		ban = BanManager.getBan(e.getPlayer().getAddress().getAddress().getHostAddress(), e.getTarget().getName());
		if (ban != null) {
			// Ugly workaround the player joined... player left messages
			Server srv = e.getPlayer().getServer();
			if (srv != null)
				e.setTarget(srv.getInfo());
			e.getPlayer().disconnect(Utils.formatMessage(ban.getReason(), ban));
		}
		return;
	}
}
