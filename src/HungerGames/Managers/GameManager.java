package HungerGames.Managers;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import HungerGames.HungerMain;
import HungetGames.Structure.PlayerData;

public class GameManager {
		
	private HungerMain plugin;
	private World GameWorld;
	private HashMap<Player,PlayerData> playersData=new HashMap<Player,PlayerData>();
	
	public GameManager(HungerMain _plugin){
		plugin=_plugin;
		GameWorld=plugin.getServer().getWorlds().get(0);
		for(Player pent:plugin.getServer().getOnlinePlayers())
			onPlayerJoinServer(pent);
	}
	
	public World getGameWorld(){
		return GameWorld;
	}

	public void onPlayerJoinServer(Player player){
		getPlayersData().put(player, new PlayerData());
		
	}
	
	public void onPlayerQuitServer(Player player){
		if(getPlayersData().containsKey(player))
			getPlayersData().remove(player);
	}

	public HashMap<Player,PlayerData> getPlayersData() {
		return playersData;
	}
	
}
