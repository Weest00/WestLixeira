package br.com.west.lixeira.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import br.com.west.lixeira.inventorys.LixeiraInventory;

public class LixeiraCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("lixo")) {
				player.openInventory(LixeiraInventory.openMenuInventory(player));

			}

		} else {
			Bukkit.getConsoleSender().sendMessage("§cApenas jogadores.");
		}

		return false;
	}

}
