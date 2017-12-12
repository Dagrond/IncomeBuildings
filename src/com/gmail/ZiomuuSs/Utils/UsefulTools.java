package com.gmail.ZiomuuSs.Utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.gmail.ZiomuuSs.IncomeBuilding;
import com.gmail.ZiomuuSs.Main;

public final class UsefulTools {
  private static FileConfiguration msgConfig;
  private static FileConfiguration dataConfig;
  private static Main plugin;
  private static HashMap<String, IncomeBuilding> data = new HashMap<String, IncomeBuilding>();

  public static void sendAccessors (ConfigAccessor ma, ConfigAccessor da, Main instance) {
    msgConfig = ma.getConfig();
    dataConfig = da.getConfig();
    plugin = instance;
  }
  
  public static void loadData(FileConfiguration data) {
    //todo
  }
  
  public static void saveData() {
    //todo
  }

  public static boolean newIncomeBuilding(String name, String region, World world, UUID owner) {
    for(String key: data.keySet()) {
      if (data.get(key).getName().equals(name))
        return false;
      if (data.get(key).getWorld().equals(world) && data.get(key).getRegion().equals(region))
        return false;
    }
    if (data.containsKey(name))
      return false;
    else {
      data.put(name, new IncomeBuilding(plugin, region, world, name, owner));
      return true;
    }
  }

  public static HashMap<String, IncomeBuilding> getData() {
    return data;
  }

  public static String getMsg (String path, String...opt) {
    String msg = msgConfig.getString("prefix");
    if (path.contains("error_")) {
      msg += msgConfig.getString("error_color")+msgConfig.getString(path);
      msg = msg.replaceAll("%s", msgConfig.getString("error_variable_color"));
      msg = msg.replaceAll("%n", msgConfig.getString("error_color"));
      if (opt.length > 0) {
        for (int i = 0; i<opt.length; i++) {
          msg = msg.replaceAll("%"+(i+1), msgConfig.getString("error_variable_color")+opt[i]+msgConfig.getString("error_color"));
        }
      }
    } else {
      msg += msgConfig.getString("message_color")+msgConfig.getString(path);
      msg = msg.replaceAll("%s", msgConfig.getString("message_variable_color"));
      msg = msg.replaceAll("%n", msgConfig.getString("message_color"));
      if (opt.length > 0) {
        for (int i = 0; i<opt.length; i++) {
          msg = msg.replaceAll("%"+(i+1), msgConfig.getString("message_variable_color")+opt[i]+msgConfig.getString("message_color"));
        }
      }
    }
    return ChatColor.translateAlternateColorCodes('&', msg);
  }
}
