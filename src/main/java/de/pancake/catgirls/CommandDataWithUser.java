package de.pancake.catgirls;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class CommandDataWithUser extends CommandData {

	public CommandDataWithUser(String name, String description) {
		super(name, description);
		this.addOption(OptionType.USER, "person", "Who to interact with", true);
	}

}
