package HungetGames.Structure;

public enum Permissions {
	admin,
	disabled_kick_immunity;
	
	@Override
	public String toString(){
		switch(this){
		case admin:return "hungergames.admin";
		case disabled_kick_immunity:return "hungergames.disabled_kick_immunity";
		default:
			break;
		}
		return null;
	}
}
