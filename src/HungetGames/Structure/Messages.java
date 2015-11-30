package HungetGames.Structure;

import org.bukkit.ChatColor;


public enum Messages {
	error_no_permission,
	error_no_enough_arguments,
	error_null_arguments,
	error_command_only_for_player,
	error_command_not_permitted,

	error_word_already_exists,
	
	draw_chat_draw_word,
	draw_title_draw_word,
	draw_title_draw_to_others,
	draw_title_draw_to_others_subtitle,
	draw_chat_player_guessed_word,
	draw_chat_word_was,
	draw_chat_all_guessed,
	draw_chat_dont_guess_again,

	misc_game_stopped,
	misc_game_stopped_with_reason,
	misc_game_enabled,
	
	misc_chat_winners,
	misc_no_enough_players,
	misc_chat_game_started_at,
	misc_chat_greetings,
	misc_title_greeting;

private String plugName=ChatColor.AQUA+""+ChatColor.BOLD+"Draw"+ChatColor.GREEN+ChatColor.BOLD+"It";
private String header=ChatColor.DARK_GRAY+""+ChatColor.BOLD+"\u258C "+plugName+ChatColor.DARK_GRAY+""+ChatColor.BOLD+" | ";
private String bar=ChatColor.DARK_GRAY+	"\u2584\u2584\u2584\u2584\u2584\u2584\u2584"+
										"\u2584\u2584\u2584\u2584\u2584\u2584\u2584"+
										"\u2584\u2584\u2584\u2584\u2584\u2584\u2584"+
										"\u2584\u2584\u2584\u2584\u2584\u2584\u2584"+
										"\u2584\u2584\u2584\u2584\u2584\u2584\u2584";
	
	@Override
	public String toString(){
		if(this.equals(error_no_enough_arguments))return ChatColor.RED+"������: �� ���������� ����������";
		if(this.equals(error_null_arguments))return ChatColor.RED+"������: ������ ���������";
		if(this.equals(error_no_permission))return ChatColor.RED+"������: �� ���������� ����";
		if(this.equals(error_command_only_for_player))return ChatColor.RED+"������: ������� ����������� ������ �� ������";
		if(this.equals(error_command_not_permitted))return ChatColor.RED+"��� ������ ������������ �� ����� ������";
		if(this.equals(draw_title_draw_word))return ChatColor.GRAY+"�������: "+ChatColor.AQUA+""+ChatColor.BOLD+"%1$s";
		if(this.equals(misc_chat_game_started_at))return header+ChatColor.GRAY+"���� �������� �����: "+ChatColor.AQUA+""+ChatColor.BOLD+"%1$d"+ChatColor.GRAY+" c.";
		if(this.equals(draw_chat_all_guessed))return header+ChatColor.RED+ChatColor.BOLD+"����� ��������."+ChatColor.GREEN+ChatColor.BOLD+" ��� �������� �����.";

		if(this.equals(draw_chat_draw_word))return (	bar+"\n \n"+
				ChatColor.LIGHT_PURPLE+"  ���� �����: "+ChatColor.GOLD+ChatColor.BOLD+"%1$s \n"+
				ChatColor.GRAY+"  ������� ������� "+ChatColor.AQUA+" ������ ������ �����\n"+
				ChatColor.GRAY+""+"\n \n"+bar);
		
		if(this.equals(misc_chat_winners))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  1-�� �����: "+ChatColor.BLUE+"%1$s "+ChatColor.GRAY+"(%4$d �����)\n"+
				ChatColor.GOLD+ChatColor.BOLD+"  2-�� �����: "+ChatColor.BLUE+"%2$s "+ChatColor.GRAY+"(%5$d �����)\n"+
				ChatColor.GRAY+ChatColor.BOLD+"  3-�� �����: "+ChatColor.BLUE+"%3$s "+ChatColor.GRAY+"(%6$d �����)\n"+
				"\n \n"+bar);
		
		if(this.equals(misc_game_stopped))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  ���� ����������� ���������������"+
				"\n \n"+bar);

		if(this.equals(draw_chat_dont_guess_again))return header+ChatColor.RED+"�� ������ ��������� ����� � ����. �� ������������� �������.";
		
		if(this.equals(misc_game_enabled))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  ���� ������������"+
				"\n \n"+bar);
		
		if(this.equals(misc_game_stopped_with_reason))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  ���� ����������� ���������������. �������: "+ChatColor.AQUA+"%1$s"+
				"\n \n"+bar);

		if(this.equals(draw_title_draw_to_others))return (ChatColor.AQUA+""+ChatColor.BOLD+"����� �����");
		if(this.equals(draw_title_draw_to_others_subtitle))return (ChatColor.GREEN+"%1$s"+ChatColor.DARK_AQUA+" ������");
		if(this.equals(draw_chat_player_guessed_word))return 	header+ChatColor.YELLOW+""+ChatColor.BOLD+"%1$s"+ChatColor.GREEN+ChatColor.BOLD+
				" �������(�) ����� �� "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%2$d ���%3$s";

		if(this.equals(draw_chat_word_was))return 	header+ChatColor.GRAY+ChatColor.BOLD+"����� ����: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%1$s";
		if(this.equals(misc_no_enough_players))return 	header+ChatColor.GRAY+ChatColor.BOLD+"������������ ������� ��� ������ ����. ���������: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%1$d"+ChatColor.GRAY+ChatColor.BOLD+". � ����: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%2$d";

		if(this.equals(error_word_already_exists))return ChatColor.RED+"������: ����� ����� ��� ���� � �������";

		if(this.equals(misc_chat_greetings))return header+ChatColor.GREEN+"����� ���������� � "+plugName+ChatColor.GREEN+". �����. �����.";
		if(this.equals(misc_title_greeting))return plugName;
		return null;
	}
	
	public String format(Object... args){
		return String.format(this.toString(), args);
	}

}
