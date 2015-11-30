package HungerGames;

import org.bukkit.plugin.java.JavaPlugin;

import HungerGames.Listeners.CommonListener;
import HungerGames.Listeners.InGameListener;
import HungerGames.Managers.GameManager;
import HungerGames.Managers.WeaponManager;

public class HungerMain extends JavaPlugin{
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private WeaponManager weaponManager;
	private GameManager gameManager;
	
		@Override
		public void onEnable(){
			weaponManager=new WeaponManager(this);
			gameManager=new GameManager(this);
			new CommonListener(this);
			new InGameListener(this);
			
			this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			getLogger().info("HungerGames enabled");
		}
		
		@Override
		public void onDisable(){
			getLogger().info("HungerGames disabled");
		}

		public WeaponManager getWeaponManager() {
			return weaponManager;
		}

		public GameManager getGameManager() {
			return gameManager;
		}


	}

