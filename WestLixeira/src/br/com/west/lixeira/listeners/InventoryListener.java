package br.com.west.lixeira.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import br.com.west.lixeira.Main;
import br.com.west.lixeira.apis.ActionBar;
import br.com.west.lixeira.inventorys.LixeiraInventory;

public class InventoryListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		String playerName = player.getName();
		Inventory inventory = event.getClickedInventory();

		if (inventory == null) {
			return;
		}

		if (event.getCurrentItem().getType() == Material.AIR) {
			return;
		}

		if (inventory.getTitle().equals("§7Lixeira")) {
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cSeu inventário está vázio...")) {
				event.setCancelled(true);
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1F, 1F);
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aVoltar")) {
				event.setCancelled(true);
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				player.openInventory(LixeiraInventory.openMenuInventory(player));
			}

		}

		if (inventory.getTitle().equals("§7Limpar inventário")) {
			event.setCancelled(true);

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aLimpar inventário de um jogador")) {
				if (Main.chat.contains(playerName)) {
					ActionBar.sendActionText(player, "§cVocê já está em um processo no momento.");
					player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
					player.closeInventory();

				} else {

					Main.chat.add(playerName);
					player.sendMessage(
							"§aDigite o nome do jogador no chat para limpar o inventário. \npara cancelar a ação digite §7§ncancelar§a.");
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
					player.closeInventory();
				}
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aJogar itens selecionados")) {
				player.openInventory(LixeiraInventory.openLixeiraInventory(player));
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);

			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cLimpar inventário")) {
				ActionBar.sendActionText(player, "§cSeu inventário já está vazio no momento.");
				player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
				player.closeInventory();

			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aLimpar inventário")) {
				ActionBar.sendActionText(player, "§aSeu inventário foi limpo com sucesso.");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				player.closeInventory();
				player.getInventory().clear();
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cSem permissão")) {
				ActionBar.sendActionText(player, "§cSem permissão para executar esta ação.");
				player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
				player.closeInventory();

			}

		}
	}

}
