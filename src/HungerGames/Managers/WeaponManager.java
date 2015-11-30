package HungerGames.Managers;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import HungerGames.HungerMain;
import HungerGames.actBarMsg;
import HungetGames.Structure.PlayerData;

public class WeaponManager {
	
	private HungerMain plugin;
	
	private class Rocket extends BukkitRunnable{

		private Location position;
		//private Player launcher;
		private Player target;
		private long lifeTime;
		private ArmorStand missile;
		private int stableFlyCount=100;
		
		public Rocket(Player _launcher, Player _target){
			position=_launcher.getLocation().clone();
			position.add(position.getDirection().multiply(0.9f));
			//launcher=_launcher;
			target=_target;
			lifeTime=System.currentTimeMillis()+10000;//время жизни рокеты 5сек
			missile=(ArmorStand) position.getWorld().spawnEntity(position, EntityType.ARMOR_STAND);
			ItemStack s = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) s.getItemMeta();
			sm.setOwner("MHF_TNT");
			s.setItemMeta(sm);
			missile.getEquipment().setHelmet(s);
			missile.setVisible(false);
			missile.setGravity(false);
			this.runTaskTimer(plugin, 0, 2);
		}
		
		@Override
		public void run() {
			if(System.currentTimeMillis()>lifeTime){
				missile.remove();
				this.cancel();
			}else{
				position.add(position.getDirection().multiply(0.9f));
				missile.teleport(position);
				if(target!=null){
					if(stableFlyCount>0)
						stableFlyCount--;
					if(stableFlyCount%10==0){
						position.getWorld().spigot().playEffect(position, Effect.CLOUD, 0, 0, 0.0f, 0.0f, 0.0f, 0.0f, 10, 40);
						Vector targVec=target.getLocation().subtract(position).toVector();
						Vector resVec=position.getDirection().add(targVec);
						position.setDirection(resVec);
					}
				}
			}
			
		}
	}
	
	private class AimingTimer100ms extends BukkitRunnable{
		
		public AimingTimer100ms(){
			this.runTaskTimer(plugin, 1, 2);//каждые 100мс
		}
		
		private String getAimingProgress(int max, int current,boolean flash){
			String result=ChatColor.YELLOW+""+ChatColor.BOLD+"НАЦЕЛИВАНИЕ [";
			if(flash)
				result+=ChatColor.RED;else
				result+=ChatColor.DARK_RED;
				
			for(int j=0;j<=7;j++)
				if((double)current*8/(double)max>=j)
					result+=StringEscapeUtils.unescapeJava("\\u"+(2581+j));else
					result+='\u2581';
			return result+ChatColor.YELLOW+ChatColor.BOLD+"]";	
		}

		@Override
		public void run() {
			for(Player pent:plugin.getGameManager().getGameWorld().getPlayers()){
				PlayerData pd=plugin.getGameManager().getPlayersData().get(pent);
				if(pd.aiming){
					if(pd.target!=null){
						if(pd.aimingTimer>0){
							pd.aimingTimer--;
							if(pd.aimingTimer % 2==0)
								pent.playSound(pent.getLocation(), Sound.NOTE_PIANO, 0.4f, 1.7f);
						}else{
							pent.playSound(pent.getLocation(), Sound.NOTE_PIANO, 0.2f, 2.0f);
							pd.aimingFinalFlash=!pd.aimingFinalFlash;
						}
						actBarMsg.actionBarMsg(pent,getAimingProgress(PlayerData.aimingTimerMax,
								PlayerData.aimingTimerMax-pd.aimingTimer,pd.aimingFinalFlash));
					}else pd.aimingTimer=PlayerData.aimingTimerMax;
					if(pd.aimingTimer>0)
						pd.target=getPlayerAim(pent, 100);
				}
			}
			
		}
		
	}
	
	public WeaponManager(HungerMain _plugin){
		plugin=_plugin;
		new AimingTimer100ms();
	}
	
	public Player getPlayerAim(Player player,int distance){
		Location start=player.getEyeLocation().clone();
		Block bl=null;
		for(int j=0;j<=distance;j++){
			start.add(start.getDirection().multiply(0.9f));
			bl=start.getWorld().getBlockAt(start);
			if(bl!=null){
				if(bl.getType()!=Material.AIR)return null;
			}else return null;
			for(Player pent:plugin.getGameManager().getGameWorld().getPlayers()){
				if(!pent.equals(player))
					if(pent.getLocation().distanceSquared(start)<2)return pent;
			}
			
		}
		return null;
	}
	
	public void tryPlayerAiming(Player player){
		PlayerData pd=plugin.getGameManager().getPlayersData().get(player);
		pd.aimingTimer=PlayerData.aimingTimerMax;
		pd.aiming=true;	
		Player aim=getPlayerAim(player,100);
		if(aim!=null)pd.target=aim;
	}

	public void onPlayerInteract(PlayerInteractEvent e){
		if(	(e.getAction().equals(Action.RIGHT_CLICK_AIR))||
			(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
			ItemStack is=e.getPlayer().getItemInHand();
			if(is!=null){
				if(is.getType().equals(Material.GOLD_SWORD)){
					tryPlayerAiming(e.getPlayer());
				}
				if(is.getType().equals(Material.GOLD_NUGGET)){
					Location loc = e.getPlayer().getEyeLocation();
					 
					for(int pitch = 0; pitch >= -90; pitch -= 15){
					  for(int yaw = 180; yaw >= -180; yaw -= 15){
					    loc.setYaw(yaw);
					    loc.setPitch(pitch);
					 
					    Arrow arrow = e.getPlayer().launchProjectile(Arrow.class);
					 
					    arrow.setVelocity(loc.getDirection());
					  }
					}
				}
			}
		}
	}

	public void onPlayerChangeItem(PlayerItemHeldEvent e){
		ItemStack is=e.getPlayer().getInventory().getItem(e.getPreviousSlot());
		if(is!=null){
			if(is.getType().equals(Material.GOLD_SWORD)){
				abortPlayerLaunchRocket(e.getPlayer());
			}
		}	
		
	}
	
	public void onPlayerReleaseMouse(Player player){
		ItemStack is=player.getItemInHand();
		if(is!=null){
			if(is.getType().equals(Material.GOLD_SWORD)){
				tryPlayerLaunchRocket(player);
			}
		}		
	}

	public void tryPlayerLaunchRocket(Player player){
		PlayerData pd=plugin.getGameManager().getPlayersData().get(player);
		if(pd.aiming){
			if(pd.aimingTimer==0)
				new Rocket(player,pd.target);else
				new Rocket(player,null);
			pd.aiming=false;
		}
	}
	
	public void abortPlayerLaunchRocket(Player player){
		PlayerData pd=plugin.getGameManager().getPlayersData().get(player);
		if(pd.aiming){
			pd.aiming=false;
		}
	}
	
}
