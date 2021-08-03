package net.moonlar.bungeeconnect;

import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeConnect extends Plugin {

  @Override
  public void onEnable() {
    getProxy().getPluginManager().registerCommand(this, new ConnectCommand(this));
  }

  @Override
  public void onDisable() {

  }
}
