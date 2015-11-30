package HungerGames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class actBarMsg {
	
	//private Main self;
	
	private static String getVersion() {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}
	
	private static Class<?> getNMSClass(String nmsClassString) throws ClassNotFoundException {
        if(nmsClassString.equals("ChatSerializer")) {
            nmsClassString = "IChatBaseComponent$ChatSerializer";
        }
        return Class.forName("net.minecraft.server."+getVersion()+ nmsClassString);
    }
	
    private static void sendPacket(Player p, Object packet) {
        try {
            Object player = p.getClass().getMethod("getHandle").invoke(p);
            Object connection = player.getClass().getField("playerConnection").get(player);
            connection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(connection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public static void actionBarMsg(Player p, String msg) {
        try {
            Object chat = getNMSClass("ChatSerializer").getMethod("a", String.class).invoke(null, "{text:\"" + msg + "\"}");
            Object packet = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class).newInstance(chat, (byte) 2);
            sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
