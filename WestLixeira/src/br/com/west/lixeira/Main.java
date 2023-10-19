package br.com.west.lixeira;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import br.com.west.lixeira.commands.LixeiraCommand;
import br.com.west.lixeira.listeners.ChatListener;
import br.com.west.lixeira.listeners.InventoryListener;

public class Main extends JavaPlugin {

	public static ArrayList<String> chat = new ArrayList<String>();

	public void onEnable() {
		registerCommands();
		registerEvents();
		getLogger().info("Plugin feito por west, obrigado por usar.");

	}

	public void onDisable() {

	}

	public void registerCommands() {
		getCommand("lixo").setExecutor(new LixeiraCommand());
		Bukkit.getLogger().info("Comandos registrados com sucesso.");

	}

	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getLogger().info("Eventos registrados com sucesso.");

	}


}
