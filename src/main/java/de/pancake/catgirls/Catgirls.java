package de.pancake.catgirls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class Catgirls {

	private static final File PROPERTIES = new File("bot2.properties");
	private static final String[] SUFFIXES = { ".jpg", ".png", ".jpeg", ".gif" };
	private static final Map<String, Integer> COUNT = new HashMap<>() {
		private static final long serialVersionUID = -7905474885686024897L;

		{
			this.put("neko", 469);
			this.put("kitsune", 79);
			this.put("hug", 36);
			this.put("pat", 42);
			this.put("waifu", 49);
			this.put("cry", 28);
			this.put("kiss", 32);
			this.put("slap", 17);
			this.put("punch", 13);
			this.put("nekolewd", 43);
		}
	};
	private static final Random rand = new Random();
	private static final Map<String, String> URL = new HashMap<>() {
		private static final long serialVersionUID = 2230852566164481347L;

		{
			this.put("neko", "https://neko-love.xyz/v1/neko/neko-love_");
			this.put("kitsune", "https://neko-love.xyz/v1/kitsune/neko-love-kitsune_");
			this.put("hug", "https://neko-love.xyz/v1/hug/neko-love_hug_");
			this.put("pat", "https://neko-love.xyz/v1/pat/neko-love_pat_");
			this.put("waifu", "https://neko-love.xyz/v1/waifu/neko-love_waifu_");
			this.put("cry", "https://neko-love.xyz/v1/cry/neko-love-cry_");
			this.put("kiss", "https://neko-love.xyz/v1/kiss/neko-love_kiss_");
			this.put("slap", "https://neko-love.xyz/v1/slap/neko-love_slap_");
			this.put("punch", "https://neko-love.xyz/v1/punch/neko-love-punch_");
			this.put("nekolewd", "https://neko-love.xyz/v1/nekolewd/neko-love-lewd_");
		}
	};

	public static void main(String[] args) throws Exception {
		// load configuration
		var configuration = new Properties();
		if (!PROPERTIES.exists())
			loadDefaultConfiguration();
		configuration.load(new FileInputStream(PROPERTIES));

		// run bot
		new DiscordBot(configuration);
	}

	public static void loadDefaultConfiguration() throws IOException {
		PROPERTIES.createNewFile();
		var stream = new FileOutputStream(PROPERTIES);
		stream.write("# This is an auto-generated Configuration File. Please set a value for \"token\"\ntoken=".getBytes(Charset.defaultCharset()));
		stream.close();
		System.exit(0);
	}

	public static String fetch(String tag) {
		var i = rand.nextInt(COUNT.get(tag)) + 1;
		return "||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​||||​|| _ _ _ _ _ _ " + URL.get(tag) + i + SUFFIXES[0] + '\n' + URL.get(tag) + i + SUFFIXES[1] + '\n' + URL.get(tag) + i + SUFFIXES[2] + '\n' + URL.get(tag) + i + SUFFIXES[3];
	}

}
