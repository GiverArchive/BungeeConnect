package net.moonlar.bungeeconnect;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ConnectCommand extends Command {
  private final BungeeConnect plugin;

  public ConnectCommand(BungeeConnect plugin) {
    super("connect");
    this.plugin = plugin;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if(!(sender instanceof ProxiedPlayer) && args.length < 2) {
      msg(sender, ChatColor.RED + "Especifique o servidor e o jogador.");
      return;
    }

    if(args.length == 0) {
      msg(sender, ChatColor.RED + "Especifique um servidor.");
      return;
    }

    ServerInfo server = plugin.getProxy().getServerInfo(args[0]);

    if(server == null) {
      msg(sender, ChatColor.RED + "Este servidor não existe...");
      return;
    }

    ProxiedPlayer player;

    if(args.length == 2) {
      player = plugin.getProxy().getPlayer(args[1]);

      if(player == null) {
        msg(sender, ChatColor.RED + "Este jogador não está conectado.");
        return;
      }
    } else {
      player = (ProxiedPlayer) sender;
    }

    if(server.getName().equals(player.getServer().getInfo().getName())) {
      msg(sender, ChatColor.RED
              + (player == sender ? "Você" : player.getName())
              + " já está conectado neste servidor!");
      return;
    }

    if(sender != player) {
      msg(sender, ChatColor.GREEN + "Enviando " + player.getName() + " para " + server.getName());
    }

    player.connect(server);
    msg(sender, ChatColor.GREEN + "Conectado em " + server.getName());
  }

  private void msg(CommandSender sender, String message) {
    sender.sendMessage(new TextComponent(message));
  }
}
