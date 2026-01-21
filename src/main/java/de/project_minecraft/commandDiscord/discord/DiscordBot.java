package de.project_minecraft.commandDiscord.discord;

import de.project_minecraft.commandDiscord.CommandDiscord;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class DiscordBot {
    private String token;

    public DiscordBot(String token) {
        this.token = token;
    }


    public void Initialize() {
        JDABuilder.createLight(this.token, EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
                .build();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
