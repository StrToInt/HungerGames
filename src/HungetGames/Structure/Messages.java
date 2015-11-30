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
		if(this.equals(error_no_enough_arguments))return ChatColor.RED+"Ошибка: Не достаточно аргументов";
		if(this.equals(error_null_arguments))return ChatColor.RED+"Ошибка: Пустые аргументы";
		if(this.equals(error_no_permission))return ChatColor.RED+"Ошибка: Не достаточно прав";
		if(this.equals(error_command_only_for_player))return ChatColor.RED+"Ошибка: Команда выполняется только от игрока";
		if(this.equals(error_command_not_permitted))return ChatColor.RED+"Это нельзя использовать во время раунда";
		if(this.equals(draw_title_draw_word))return ChatColor.GRAY+"Рисуйте: "+ChatColor.AQUA+""+ChatColor.BOLD+"%1$s";
		if(this.equals(misc_chat_game_started_at))return header+ChatColor.GRAY+"Игра начнется через: "+ChatColor.AQUA+""+ChatColor.BOLD+"%1$d"+ChatColor.GRAY+" c.";
		if(this.equals(draw_chat_all_guessed))return header+ChatColor.RED+ChatColor.BOLD+"Раунд закончен."+ChatColor.GREEN+ChatColor.BOLD+" Все отгадали слово.";

		if(this.equals(draw_chat_draw_word))return (	bar+"\n \n"+
				ChatColor.LIGHT_PURPLE+"  Ваше слово: "+ChatColor.GOLD+ChatColor.BOLD+"%1$s \n"+
				ChatColor.GRAY+"  Рисуйте нажимая "+ChatColor.AQUA+" Правую кнопку мышки\n"+
				ChatColor.GRAY+""+"\n \n"+bar);
		
		if(this.equals(misc_chat_winners))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  1-ое место: "+ChatColor.BLUE+"%1$s "+ChatColor.GRAY+"(%4$d очков)\n"+
				ChatColor.GOLD+ChatColor.BOLD+"  2-ое место: "+ChatColor.BLUE+"%2$s "+ChatColor.GRAY+"(%5$d очков)\n"+
				ChatColor.GRAY+ChatColor.BOLD+"  3-ое место: "+ChatColor.BLUE+"%3$s "+ChatColor.GRAY+"(%6$d очков)\n"+
				"\n \n"+bar);
		
		if(this.equals(misc_game_stopped))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  Игра остановлена администратором"+
				"\n \n"+bar);

		if(this.equals(draw_chat_dont_guess_again))return header+ChatColor.RED+"Не пишите угаданное слово в чате. Не подсказывайте игрокам.";
		
		if(this.equals(misc_game_enabled))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  Игра возобновлена"+
				"\n \n"+bar);
		
		if(this.equals(misc_game_stopped_with_reason))return (	bar+"\n \n"+
				ChatColor.YELLOW+ChatColor.BOLD+"  Игра остановлена администратором. Причина: "+ChatColor.AQUA+"%1$s"+
				"\n \n"+bar);

		if(this.equals(draw_title_draw_to_others))return (ChatColor.AQUA+""+ChatColor.BOLD+"Новый раунд");
		if(this.equals(draw_title_draw_to_others_subtitle))return (ChatColor.GREEN+"%1$s"+ChatColor.DARK_AQUA+" рисует");
		if(this.equals(draw_chat_player_guessed_word))return 	header+ChatColor.YELLOW+""+ChatColor.BOLD+"%1$s"+ChatColor.GREEN+ChatColor.BOLD+
				" отгадал(а) слово за "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%2$d очк%3$s";

		if(this.equals(draw_chat_word_was))return 	header+ChatColor.GRAY+ChatColor.BOLD+"Слово было: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%1$s";
		if(this.equals(misc_no_enough_players))return 	header+ChatColor.GRAY+ChatColor.BOLD+"Недостаточно игроков для начала игры. Требуется: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%1$d"+ChatColor.GRAY+ChatColor.BOLD+". В игре: "+ChatColor.DARK_AQUA+ChatColor.BOLD+"%2$d";

		if(this.equals(error_word_already_exists))return ChatColor.RED+"Ошибка: Такое слово уже есть в словаре";

		if(this.equals(misc_chat_greetings))return header+ChatColor.GREEN+"Добро пожаловать в "+plugName+ChatColor.GREEN+". Играй. Рисуй.";
		if(this.equals(misc_title_greeting))return plugName;
		return null;
	}
	
	public String format(Object... args){
		return String.format(this.toString(), args);
	}

}
