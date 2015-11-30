package HungetGames.Structure;

import org.bukkit.Location;
import org.bukkit.World;

public class Point {
	private double X,Y,Z,pitch,yaw;

	public Point(double pX, double pY, double pZ){
		X=pX;
		Y=pY;
		Z=pZ;
	}
	
	public Point(){
		X=0;
		Y=0;
		Z=0;
	}
	
	public Point parseString(String source){
		String[] st=source.split(" ");
		if(st.length==5){
			X=Double.valueOf(st[0]);
			Y=Double.valueOf(st[1]);
			Z=Double.valueOf(st[2]);
			pitch=Double.valueOf(st[3]);
			yaw=Double.valueOf(st[4]);
		}
		st=null;
		return this;
	}

	public Point(Location location){
		setPoint(location);
	}

	public Location toLocation(World world){
		Location result=new Location(world,X,Y,Z);
		result.setPitch((float) pitch);
		result.setYaw((float) yaw);
		return result;
	}
	
	public boolean compareLocation(Location location){
		return ((location.getX()==this.getX())&&(location.getY()==this.getY())&&(location.getZ()==this.getZ()));
	}
	
	public void setPoint(double pX, double pY, double pZ, double Pitch, double Yaw){
		X=pX;
		Y=pY;
		Z=pZ;
		setPitch(Pitch);
		setYaw(Yaw);
	}
	
	public void setPoint(Location location){
		X=location.getX();
		Y=location.getY();
		Z=location.getZ();
		pitch=location.getPitch();
		yaw=location.getYaw();
	}
	
	@Override
	public String toString(){
		String result;
		result=	new java.text.DecimalFormat("0.00").format(X)+" "+
				new java.text.DecimalFormat("0.00").format(Y)+" "+
				new java.text.DecimalFormat("0.00").format(Z)+" "+
				new java.text.DecimalFormat("0.00").format(pitch)+" "+
				new java.text.DecimalFormat("0.00").format(yaw);
		result=result.replace(",", ".");
		return result;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		Z = z;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}
}
