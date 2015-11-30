package HungerGames.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import HungerGames.HungerMain;

public class InGameListener implements Listener{

	private HungerMain plugin;
	
	
	public InGameListener(HungerMain _plugin) {
		plugin = _plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
		protocolManager.addPacketListener(new PacketAdapter(plugin,
		        ListenerPriority.NORMAL, 
		        PacketType.Play.Client.BLOCK_DIG) {
		    @Override
		    public void onPacketReceiving(PacketEvent event) {
		        if (event.getPacketType() == PacketType.Play.Client.BLOCK_DIG) {
		        	new BukkitRunnable(){

						@Override
						public void run() {
							_plugin.getWeaponManager().onPlayerReleaseMouse(event.getPlayer());
							this.cancel();
						}
		        		
		        	}.runTask(_plugin);
		        }
		    }
		});
	}

	//never hungry
	@EventHandler(priority = EventPriority.MONITOR,ignoreCancelled=false)
	public void onChangeItem(PlayerItemHeldEvent e) {
		plugin.getWeaponManager().onPlayerChangeItem(e);
	}	

	//never hungry
	@EventHandler(priority = EventPriority.MONITOR,ignoreCancelled=false)
	public void onInteract(PlayerInteractEvent e) {
		plugin.getWeaponManager().onPlayerInteract(e);
	}
	
}
