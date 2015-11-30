package HungetGames.Structure;

import org.bukkit.scheduler.BukkitRunnable;

import HungerGames.HungerMain;

public class Timer_1s extends BukkitRunnable{
	
	private HungerMain self;
	
	public Timer_1s(HungerMain plugin){
		self=plugin;
		this.runTaskTimer(self, 1, 20);
	}
	
	@Override
	public void run() {
	
	}
	

}
