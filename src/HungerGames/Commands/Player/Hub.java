package HungerGames.Commands.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import HungerGames.HungerMain;

public class Hub implements CommandExecutor {

	private	HungerMain plugin;


	public Hub(HungerMain _plugin) {
		plugin=_plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(((Player)sender).getName());
		out.writeUTF("lobby");
		((Player)sender).sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
		return true;
	}

}
