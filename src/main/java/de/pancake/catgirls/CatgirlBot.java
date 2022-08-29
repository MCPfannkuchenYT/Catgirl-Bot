package de.pancake.catgirls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class CatgirlBot extends ListenerAdapter implements Runnable {

	private final JDA jda;
	private final Properties configuration;
	private static List<String> pics = new ArrayList<>();
	
	@Override
	public void onSlashCommand(SlashCommandEvent event) {
		try {
			URL url = new URL("https://neko-love.xyz/api/v1/" + event.getName());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String msg = reader.readLine().split("\"\\:\"")[1].split("\"\\}")[0];
			reader.close();
			if (pics.contains(msg)) {
				onSlashCommand(event);
				return;
			}
			pics.add(msg);
			if (event.getOptions().size() == 0) {
				event.reply(msg).complete();
			} else {
				if (event.getCommandPath().toLowerCase().startsWith("kiss") || event.getCommandPath().toLowerCase().startsWith("punch")) {
					event.reply(event.getUser().getAsMention() + " " + event.getCommandPath().split("/")[0] + "es " + event.getOptions().get(0).getAsUser().getAsMention() + "\n" + msg).complete();
				} else {
					event.reply(event.getUser().getAsMention() + " " + event.getCommandPath().split("/")[0] + "s " + event.getOptions().get(0).getAsUser().getAsMention() + "\n" + msg).complete();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			pics.clear();
			onSlashCommand(event);
		}
	}

	public CatgirlBot(Properties configuration) throws InterruptedException, LoginException {
		this.configuration = configuration;
		final JDABuilder builder = JDABuilder.createDefault(this.configuration.getProperty("token"))
				.setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(this);
		this.jda = builder.build();
		this.jda.awaitReady();
	}

	@Override
	public void run() {
		/* Register the Commands */
		System.out.println("[Catgirls] Preparing Bot...");
		for (Guild guild : jda.getGuilds()) {
			guild.upsertCommand(new CommandData("neko", "Finds a catgirl")).complete();
			guild.upsertCommand(new CommandData("kitsune", "Finds a fox")).complete();
			guild.upsertCommand(new CommandData("cry", "Cries!")).complete();
			guild.upsertCommand(new CommandData("nekolewd", "NSFW: Finds a lewd catgirl")).complete();
			guild.upsertCommand(new CommandData("waifu", "Finds a waifu")).complete();
			
			guild.upsertCommand(new CoolCommandData("hug", "Hugs someone!")).complete();
			guild.upsertCommand(new CoolCommandData("pat", "Pats someone!")).complete();
			guild.upsertCommand(new CoolCommandData("kiss", "Kisses someone!")).complete();
			guild.upsertCommand(new CoolCommandData("punch", "Punches someone!")).complete();
			guild.upsertCommand(new CoolCommandData("slap", "Slaps someone!")).complete();
		}
		System.out.println("[Catgirls] Done preparing bot.");
	}
	
	public class CoolCommandData extends CommandData {

		public CoolCommandData(String name, String description) {
			super(name, description);
			addOption(OptionType.USER, "person", "Who to interact with", true);
		}

	}

	
}
