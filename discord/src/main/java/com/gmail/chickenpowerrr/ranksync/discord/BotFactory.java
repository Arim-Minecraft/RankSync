package com.gmail.chickenpowerrr.ranksync.discord;

import com.gmail.chickenpowerrr.ranksync.api.Bot;
import com.gmail.chickenpowerrr.ranksync.api.Properties;
import com.gmail.chickenpowerrr.ranksync.api.RankSyncApi;
import com.gmail.chickenpowerrr.ranksync.api.event.BotEnabledEvent;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class BotFactory implements com.gmail.chickenpowerrr.ranksync.api.BotFactory {

    @Getter private static final BotFactory instance = new BotFactory();

    private final Map<Properties, Bot> botCache = new HashMap<>();

    private BotFactory(){}

    @Override
    public Bot getBot() {
        return null;
    }

    @Override
    public Bot getBot(Properties properties) {
        if(this.botCache.containsKey(properties)) {
            return this.botCache.get(properties);
        } else {
            Bot bot = new DiscordBot(properties);
            this.botCache.put(properties, bot);
            RankSyncApi.getApi().execute(new BotEnabledEvent(bot));
            return bot;
        }
    }
}