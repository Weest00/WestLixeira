package br.com.west.lixeira.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.west.lixeira.apis.ItemBuilder;

public class LixeiraInventory {

	public static Inventory openLixeiraInventory(Player player) {

		Inventory inventory = Bukkit.createInventory(null, 45, "§7Lixeira");

		inventory.setItem(40, ItemBuilder.addSkull(
				"http://textures.minecraft.net/texture/c4e490e1658bfde4d4ef1ea7cd646c5353377905a1369b86ee966746ae25ca7",
				"§aVoltar", "§7Clique para voltar a página."));

		if (isInventoryEmpty(player)) {
			inventory.setItem(22, ItemBuilder.add(Material.WEB, "§cSeu inventário está vázio..."));
		}

		return inventory;
	}

	public static Inventory openMenuInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27, "§7Limpar inventário");

		if (player.hasPermission("west.lixeira.admin")) {
			inventory.setItem(11, ItemBuilder.addSkull(
					"http://textures.minecraft.net/texture/ffec3d25ae0d147c342c45370e0e43300a4e48a5b43f9bb858babff756144dac",
					"§aLimpar inventário de um jogador", "§7Limpe o inventário de um jogador especifico.", "",
					"§aClique para selecionar jogador."));

		} else {
			inventory.setItem(11, ItemBuilder.addSkull(
					"http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025",
					"§cSem permissão", "§7Você precisa ser um moderador para executar está ação."));
		}

		if (player.hasPermission("west.lixeira")) {

			inventory.setItem(12, ItemBuilder.addSkull(
					"http://textures.minecraft.net/texture/6c183f3601f67293a51e2577dbea05a4602efa433cc354e63fcf7fbe026486bc",
					"§aJogar itens selecionados", "§7Escolha certos itens para jogar na lixeira.", "",
					"§aClique para abrir uma lixeira."));

			if (isInventoryEmpty(player)) {
				inventory.setItem(14, ItemBuilder.add(Material.WEB, "§cLimpar inventário",
						"§7Cheio de itens? limpe seu inventário.", "", "§cSeu inventário já está vazio."));

			} else {
				inventory.setItem(14,
						ItemBuilder.add(Material.EMERALD, "§aLimpar inventário", "",
								"§7Atenção: §cCuidado! isso limpará todo seu inventário!", "",
								"§aClique para limpar seu inventário."));
			}
		} else {
			inventory.setItem(12, ItemBuilder.addSkull(
					"http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025",
					"§cSem permissão", "§7Você não possui permissão para limpar seu inventário."));
			inventory.setItem(14, ItemBuilder.addSkull(
					"http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025",
					"§cSem permissão", "§7Você não possui permissão para limpar seu inventário."));

		}

		return inventory;
	}

	public static boolean isInventoryEmpty(Player player) {
		for (ItemStack item : player.getInventory().getContents()) {

			{
				if (item != null)
					return false;
			}
		}
		return true;

	}
}
