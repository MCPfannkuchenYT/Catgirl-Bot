package de.pancake.catgirls;

import java.util.Properties;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class DiscordBot extends ListenerAdapter {

	private final JDA jda;
	private final Properties configuration;

	@Override
	public void onSlashCommand(SlashCommandEvent event) {
		var msg = Catgirls.fetch(event.getName());

		if (event.getOptions().size() == 0)
			event.reply(msg).complete();
		else if (event.getCommandPath().toLowerCase().startsWith("kiss") || event.getCommandPath().toLowerCase().startsWith("punch"))
			event.reply(event.getUser().getAsMention() + " " + event.getCommandPath().split("/")[0] + "es " + event.getOptions().get(0).getAsUser().getAsMention() + " " + msg).complete();
		else
			event.reply(event.getUser().getAsMention() + " " + event.getCommandPath().split("/")[0] + "s " + event.getOptions().get(0).getAsUser().getAsMention() + " " + msg).complete();
	}

	public DiscordBot(Properties configuration) throws Exception {
		this.configuration = configuration;
		this.jda = JDABuilder.createDefault(this.configuration.getProperty("token")).setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS).addEventListeners(this).build();
		this.jda.awaitReady();

		// register slash commands
		this.jda.upsertCommand("neko", "Finds a catgirl").complete();
		this.jda.upsertCommand(new CommandData("neko", "Finds a catgirl")).complete();
		this.jda.upsertCommand(new CommandData("kitsune", "Finds a fox")).complete();
		this.jda.upsertCommand(new CommandData("cry", "Cries!")).complete();
		this.jda.upsertCommand(new CommandData("nekolewd", "NSFW: Finds a lewd catgirl")).complete();
		this.jda.upsertCommand(new CommandData("waifu", "Finds a waifu")).complete();

		this.jda.upsertCommand(new CommandDataWithUser("hug", "Hugs someone!")).complete();
		this.jda.upsertCommand(new CommandDataWithUser("pat", "Pats someone!")).complete();
		this.jda.upsertCommand(new CommandDataWithUser("kiss", "Kisses someone!")).complete();
		this.jda.upsertCommand(new CommandDataWithUser("punch", "Punches someone!")).complete();
		this.jda.upsertCommand(new CommandDataWithUser("slap", "Slaps someone!")).complete();
	}

}
