package fr.lenchanteur.fk.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.lenchanteur.fk.Main;

public class commandKickEquipe implements CommandExecutor, Listener{

	private Main main;
	public commandKickEquipe(Main main) { this.main = main; }
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			sender.sendMessage("§cSeul un joueur peut executer cette commande !");
			return false;
		}
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("kickequipe")) {
			
			if(!p.isOp()) {
				p.sendMessage("§cVous n'avez pas accès à cette commande !");
			}
			
			openInvKickEquipe(p);
			
			
		}
		
		return true;
	}


	private void openInvKickEquipe(Player p) {
		
		Inventory inv = Bukkit.createInventory(null,9 * 3, "§6§k!!§r  §bElimination  §6§k!!");
		
		ItemStack orange = new ItemStack(Material.WOOL, 1, (byte) 1);
		ItemMeta orangeM = orange.getItemMeta();
		orangeM.setDisplayName("§4Equipe Orange !");
		orangeM.setLore(Arrays.asList("§dCliquez ici pour","§dEliminer cette équipe !"));
		orange.setItemMeta(orangeM);
		inv.setItem(11, orange);
		
		ItemStack violet = new ItemStack(Material.WOOL, 1, (byte) 10);
		ItemMeta violetM = violet.getItemMeta();
		violetM.setDisplayName("§4Equipe Violette !");
		violetM.setLore(Arrays.asList("§dCliquez ici pour","§dEliminer cette équipe !"));
		violet.setItemMeta(violetM);
		inv.setItem(13, violet);
		
		ItemStack cyan = new ItemStack(Material.WOOL, 1, (byte) 9);
		ItemMeta cyanM = cyan.getItemMeta();
		cyanM.setDisplayName("§4Equipe Cyan !");
		cyanM.setLore(Arrays.asList("§dCliquez ici pour","§dEliminer cette équipe !"));
		cyan.setItemMeta(cyanM);
		inv.setItem(15, cyan);
		
		p.openInventory(inv);
		
	}
	
	
	@EventHandler
	private void onInteract(InventoryClickEvent e) {
		e.setCancelled(true);
		if(e.getInventory().getName().equalsIgnoreCase("§6§k!!§r  §bElimination  §6§k!!")) {
			ItemStack it = e.getCurrentItem();
			if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§4Equipe Orange !")) {
				if(main.orange.size() == 0) {
					e.getWhoClicked().closeInventory();
					e.getWhoClicked().sendMessage("§b§l[§6FKI§b§l] §6L'équipe est vide !");
					return;
				}
				for(String str : main.orange) {
					Player target = Bukkit.getPlayer(str);
					main.setSpec(target);
					target.sendMessage("§cVotre équipe est éliminée !");
				}
				Bukkit.broadcastMessage("§b§l[§6FKI§b§l] §6L'équipe "+ main.getConfig().getString("teams.orange.color").replace("&", "§") + main.getConfig().getString("teams.orange.name") + "§6 vient d'être éliminée !");
			} else if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§4Equipe Violette !")) {
				if(main.violet.size() == 0) {
					e.getWhoClicked().closeInventory();
					e.getWhoClicked().sendMessage("§b§l[§6FKI§b§l] §6L'équipe est vide !");
					return;
				}
				for(String str : main.violet) {
					Player target = Bukkit.getPlayer(str);
					main.setSpec(target);
					target.sendMessage("§cVotre équipe est éliminée !");
				}
				Bukkit.broadcastMessage("§b§l[§6FKI§b§l] §6L'équipe "+ main.getConfig().getString("teams.violet.color").replace("&", "§") + main.getConfig().getString("teams.violet.name") + "§6 vient d'être éliminée !");
			} else if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§4Equipe Cyan !")) {
				if(main.cyan.size() == 0) {
					e.getWhoClicked().closeInventory();
					e.getWhoClicked().sendMessage("§b§l[§6FKI§b§l] §6L'équipe est vide !");
					return;
				}
				for(String str : main.cyan) {
					Player target = Bukkit.getPlayer(str);
					main.setSpec(target);
					target.sendMessage("§cVotre équipe est éliminée !");
				}
				Bukkit.broadcastMessage("§b§l[§6FKI§b§l] §6L'équipe "+ main.getConfig().getString("teams.cyan.color").replace("&", "§") +main.getConfig().getString("teams.cyan.name") + "§6 vient d'être éliminée !");
			} 
		}
		e.getWhoClicked().closeInventory();
		
	}

}
