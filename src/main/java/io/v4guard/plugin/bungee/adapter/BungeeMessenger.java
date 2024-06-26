package io.v4guard.plugin.bungee.adapter;

import io.v4guard.plugin.bungee.BungeeInstance;
import io.v4guard.plugin.core.compatibility.Messenger;
import io.v4guard.plugin.core.compatibility.PlayerFetchResult;
import io.v4guard.plugin.core.constants.ListenersConstants;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeMessenger implements Messenger {

    @Override
    public void broadcastWithPermission(String message, String permission) {
        boolean sendToAll = permission.equals(ListenersConstants.ALL_PLAYERS_PERMISSION);
        ComponentBuilder componentBuilder = new ComponentBuilder(message);
        ProxiedPlayer[] players = ProxyServer.getInstance().getPlayers().toArray(new ProxiedPlayer[0]);

        for (ProxiedPlayer player : players) {
            if (sendToAll || player.hasPermission(permission)) {
                player.sendMessage(componentBuilder.create());
            }
        }
    }

    @Override
    public void sendMessageTo(String playerName, String message) {
        PlayerFetchResult<ProxiedPlayer> fetchedPlayer = BungeeInstance.get().fetchPlayer(playerName);

        if (fetchedPlayer.isOnline()) {
            fetchedPlayer.getPlayer().sendMessage(new ComponentBuilder(message).create());
        }
    }
}
