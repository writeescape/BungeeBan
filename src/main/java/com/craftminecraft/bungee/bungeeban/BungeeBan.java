package com.craftminecraft.bungee.bungeeban;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import com.craftminecraft.bungee.bungeeban.banstore.*;
import com.craftminecraft.bungee.bungeeban.command.*;
import com.craftminecraft.bungee.bungeeban.listener.ProxiedPlayerListener;
import com.google.common.eventbus.Subscribe;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;


public class BungeeBan extends Plugin implements Listener {
	//private Map<?,?> config;
	public static File configdir = new File("plugins/BungeeBan");
	
	public void onEnable() {
		if (!configdir.exists())
			configdir.mkdirs();
		// 		Will see later for a config. For now, ain't much use. 
/*		File configfile = new File(configdir, "config.yml");
		if (!configfile.exists() || !configfile.isFile()) {
			try {
				configfile.createNewFile();
			} catch (IOException e1) {
				// Can this happen ?
				e1.printStackTrace();
			}
		}
		
		Yaml yaml = new Yaml();
		try {
			config = (Map<?,?>) yaml.load(new FileReader(configfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (YAMLException e) {
			ProxyServer.getInstance().getLogger().severe("[BungeeBan] Yaml Exception Occured : " + e.getMessage());
		} catch (ClassCastException e) {
			ProxyServer.getInstance().getLogger().severe("[BungeeBan] Top-level object in config.yml is not a Map");
		}
		if (((String) config.get("store")).equalsIgnoreCase("mcfile"))*/
			BanManager.setBanStore(new FileBanStore());
		ProxyServer.getInstance().getPluginManager().registerListener(new ProxiedPlayerListener());
		ProxyServer.getInstance().getPluginManager().registerCommand(new BanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new TempBanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GBanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GTempBanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new UnbanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new BanIpCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new TempBanIpCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GBanIpCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GTempBanIpCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new UnbanIpCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GUnbanCommand());
		ProxyServer.getInstance().getPluginManager().registerCommand(new GUnbanIpCommand());
		ProxyServer.getInstance().getLogger().log(Level.INFO,"[BungeeBan] Now loaded.");
	}
	
	public void onDisable() {
		
	}
	
/*	@Subscribe
	public void onPluginmessage(PluginMessageEvent e) {
		// Checks if the one sending the message is the server.
		if (e.getTag() != "BungeeBan" || !(e.getSender() instanceof Server))
			return;
		String servername = ((Server)e.getSender()).getInfo().getName();
		ByteArrayInputStream bytestream = new ByteArrayInputStream(e.getData());
		DataInputStream datastream = new DataInputStream(bytestream);
		try {
			String method = readString(datastream);
			if (method.equalsIgnoreCase("ban")) {
				String bannedPlayer = readString(datastream);
				if (datastream.available() == 0) {
					banstore.ban(bannedPlayer, servername, "", "");
					return;
				}
				String banner = readString(datastream);
				if (datastream.available() == 0) {
					banstore.ban(bannedPlayer,servername,banner,"");
					return;
				}
				String reason = readString(datastream);
				banstore.ban(bannedPlayer,servername,banner,reason);
				
			} else if (method.equalsIgnoreCase("banip")) {
				String bannedIP = readString(datastream);
				if (datastream.available() == 0) {
					banstore.banIP(bannedIP,servername,"","");
					return;
				}
				String banner = readString(datastream);
				if (datastream.available() == 0) {
					banstore.banIP(bannedIP,servername,banner,"");
					return;
				}
				String reason = readString(datastream);
				banstore.banIP(bannedIP,servername,banner,reason);
				
			} else if (method.equalsIgnoreCase("unban")) {
				String unbannedPlayer = readString(datastream);
				banstore.unban(unbannedPlayer);
				
			} else if (method.equalsIgnoreCase("unbanip")) {
				String unbannedIP = readString(datastream);
				banstore.unbanIP(unbannedIP);
				
			} else {
				ProxyServer.getInstance().getLogger().warning("[BungeeBan] PluginMessage error");
			}
		} catch(IOException ex) {
			
		}
		return;
	}*/
	
/*	private String readString(DataInputStream stream) throws IOException {
		short len = stream.readShort();
		StringBuilder str = new StringBuilder();
		for (int i=0;i<len;i++)
			str.append(stream.readChar());
		return str.toString();
	}*/
}