package HungerGames.Listeners;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import HungerGames.HungerMain;
import HungetGames.Structure.Permissions;

public class CommonListener implements Listener{

	private HungerMain plugin;
	
	public CommonListener(HungerMain _plugin) {
		plugin = _plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	// never hungry
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHungry(FoodLevelChangeEvent e) {
			e.setCancelled(true);
	}	
	// 

	
	//remove from map on quit
	@EventHandler
	public void onPlayerQuitServer(PlayerQuitEvent e){
		plugin.getGameManager().onPlayerQuitServer(e.getPlayer());
		e.setQuitMessage(null);
	}
	
	//cancell rain
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onWeatherChange(WeatherChangeEvent e){
		new BukkitRunnable() {
			@Override
			public void run() {
				World w=e.getWorld();
				if(w!=null){
					e.getWorld().setStorm(false);
					e.getWorld().setThundering(false);
				}
			this.cancel();
			}
		}.runTaskLater(plugin,10);
	}
	
	//cancell dropping item
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDropItem(PlayerDropItemEvent e){
		if (e.getPlayer().hasPermission(Permissions.admin.toString())) 
			if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		e.setCancelled(true);
	}
	
	//cancell dropping item
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSignChange(SignChangeEvent e){
		if(e.getPlayer().getGameMode()==GameMode.CREATIVE){
			e.setLine(0, e.getLine(0).replaceAll("&", "§"));
			e.setLine(1, e.getLine(1).replaceAll("&", "§"));
			e.setLine(2, e.getLine(2).replaceAll("&", "§"));
			e.setLine(3, e.getLine(3).replaceAll("&", "§"));
			return;
		}
	}
	
	// inventory
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvetoryEvent(InventoryClickEvent e){
		if (e.getWhoClicked().hasPermission(Permissions.admin.toString()))
			if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
				return;
		e.setCancelled(true);
	}
	
	//cancell dropping item
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPickupItem(PlayerPickupItemEvent e){
		if (e.getPlayer().hasPermission(Permissions.admin.toString()))
			if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		e.setCancelled(true);
	}
	

	//cancell block destroy by burning
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockDestroyByBurning(BlockBurnEvent e){
		e.setCancelled(true);
	}
	

	//cancell block igniting
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockIgnite(BlockIgniteEvent e){
		if(e.getCause()==IgniteCause.FLINT_AND_STEEL)
			if(e.getIgnitingEntity().getType()==EntityType.PLAYER){
				if (e.getPlayer().hasPermission(Permissions.admin.toString()))return;else
				e.setCancelled(true);
			}
	}
	

	//hide achievement message
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAchievement(PlayerAchievementAwardedEvent e){
		e.setCancelled(true);
	}
	
	// entities are immortals (ARMORSTAND)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamageByOther(EntityDamageByEntityEvent e) {
		if(e.getDamager().getType()==EntityType.PLAYER){
			if (e.getDamager().hasPermission(Permissions.admin.toString())) 
					if(((Player)e.getDamager()).getGameMode()==GameMode.CREATIVE)
						return;
		}
		e.setCancelled(true);
	}
	
	// can't equip (ARMORSTAND)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onArmorStandInteract(PlayerInteractAtEntityEvent   e){
		if (e.getPlayer().hasPermission(Permissions.admin.toString())) 
				if(e.getPlayer().getGameMode()==GameMode.CREATIVE)
					return;
		e.setCancelled(true);
	}
	
	//cancell interact with entity (ITEMFRAME)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInteractWithEntity(PlayerInteractEntityEvent e){
		if (e.getPlayer().hasPermission(Permissions.admin.toString())) 
				if(e.getPlayer().getGameMode()==GameMode.CREATIVE)
					return;
		e.setCancelled(true);
	}
	
	// entities are immortals (ITEMFRAME)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHangingBreak(HangingBreakByEntityEvent   e) {
		if(e.getRemover().getType()==EntityType.PLAYER){
			if (e.getRemover().hasPermission(Permissions.admin.toString())) 
					if(((Player)e.getRemover()).getGameMode()==GameMode.CREATIVE)
						return;
		}
		e.setCancelled(true);
	}
	
	// everyone immortal
	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntityType()==EntityType.PLAYER){
			if(e.getCause().equals(DamageCause.VOID))
					e.getEntity().teleport(plugin.getGameManager().getGameWorld().getSpawnLocation());
			e.setCancelled(true);
		}
	}
	
	// cancell block break for gm -0
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getPlayer().hasPermission(Permissions.admin.toString())) 
				if(e.getPlayer().getGameMode()==GameMode.CREATIVE)
					return;
		e.setCancelled(true);
	}
	
	// cancell block break for gm -0
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getPlayer().hasPermission(Permissions.admin.toString())) 
				if(e.getPlayer().getGameMode()==GameMode.CREATIVE)
					return;
		e.setCancelled(true);
	}
	
	//join event - add to common players map
	@EventHandler
	public void onPlayerJoinServer(PlayerJoinEvent e){
		plugin.getGameManager().onPlayerJoinServer(e.getPlayer());
		e.setJoinMessage(null);
	}
	
}
