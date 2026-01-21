package de.project_minecraft.commandDiscord.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.List;

public class DiscordBot {
    private String token;
    private JDA bot;

    public DiscordBot(String token) {
        this.token = token;
    }


    public void Initialize() {
        this.bot = JDABuilder.createDefault(this.token, EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS))
                .addEventListeners(new Invite() {
                    /**
                     * @return
                     */
                    @Override
                    public @NotNull AuditableRestAction<Void> delete() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull RestAction<Invite> expand() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull InviteType getType() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull TargetType getTargetType() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @Nullable Channel getChannel() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @Nullable Group getGroup() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @Nullable InviteTarget getTarget() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull String getCode() {
                        return "";
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @Nullable Guild getGuild() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @Nullable User getInviter() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull JDA getJDA() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public int getMaxAge() {
                        return 0;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public int getMaxUses() {
                        return 0;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public @NotNull OffsetDateTime getTimeCreated() {
                        return null;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public int getUses() {
                        return 0;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public boolean isExpanded() {
                        return false;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public boolean isTemporary() {
                        return false;
                    }

                    /**
                     * @return
                     */
                    @Override
                    public boolean isGuest() {
                        return false;
                    }
                })
                .build();
    }

    public String getGuildInvite(Invite invite) {
        String inviteURL = invite.getUrl();
        return inviteURL;
    }
    public String getGuildID() {
        List<Guild> guildIDList = this.bot.getGuilds();
        String guildID = guildIDList.get(0).getId();
        return guildID;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
