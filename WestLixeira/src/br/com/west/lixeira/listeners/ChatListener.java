package br.com.west.lixeira.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import br.com.west.lixeira.Main;
import br.com.west.lixeira.apis.ActionBar;
import br.com.west.lixeira.inventorys.LixeiraInventory;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		String message = event.getMessage();

		if (Main.chat.contains(playerName)) {
			event.setCancelled(true);

			if (message.equalsIgnoreCase("cancelar")) {
				Main.chat.remove(playerName);
				ActionBar.sendActionText(player, "§cProcesso cancelado com sucesso.");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				return;

			}

			if (message.equals(playerName)) {
				ActionBar.sendActionText(player,
						"§cVocê não pode limpar seu próprio inventário por este método, limpe pelo /lixo.");
				player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
				return;
			}

			if (Bukkit.getPlayerExact(message) == null) {
				ActionBar.sendActionText(player, "§cEsse jogador não está online no momento.");
				player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
				return;

			}
			Player playerChat = Bukkit.getPlayerExact(message);
			String playerChatName = playerChat.getName();

			if (LixeiraInventory.isInventoryEmpty(playerChat)) {
				ActionBar.sendActionText(player, "§cO inventário do jogador §f" + playerChatName + " §cjá está limpo.");
				player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);

			} else {
				playerChat.getInventory().clear();
				ActionBar.sendActionText(playerChat, "§aSeu inventário foi limpo por §f" + playerName + "§a.");
				ActionBar.sendActionText(player, "§aVocê limpou o inventário de §f" + playerChatName + "§a.");
				playerChat.playSound(playerChat.getLocation(), Sound.DOOR_OPEN, 1F, 1F);
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				Main.chat.remove(playerName);
			}

		}

	}
}
