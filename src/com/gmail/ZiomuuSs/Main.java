package com.gmail.ZiomuuSs;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.ZiomuuSs.Utils.ConfigAccessor;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.loadData;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.sendAccessors;

public final class Main extends JavaPlugin {
  private ConfigAccessor messagesAccessor = new ConfigAccessor(this, "messages.yml");
  private ConfigAccessor dataAccessor = new ConfigAccessor(this, "data.yml");
  
  public void onEnable() {
    getCommand("IncomeBuildings").setExecutor(new IncomeBuildingsCommand(this));
    messagesAccessor.saveDefaultConfig();
    dataAccessor.saveDefaultConfig();
    saveDefaultConfig();
    sendAccessors(messagesAccessor, dataAccessor, this);
    loadData(dataAccessor.getConfig());
  }
  
  public void onDisable() {
    dataAccessor.saveConfig();
  }
}
