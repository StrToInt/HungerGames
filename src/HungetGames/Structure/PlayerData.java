package HungetGames.Structure;

import org.bukkit.entity.Player;

public class PlayerData {
	public boolean aiming=false;
	public final static int aimingTimerMax=10;
	public int aimingTimer=aimingTimerMax;
	public int aimingFinalTimer=0;
	public boolean aimingFinalFlash=false;
	public Player target=null;

}
