package com.gmail.ZiomuuSs.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public final class UsefulTools {
  private static FileConfiguration msgConfig;
  private static FileConfiguration dataConfig;

  public static void sendAccessors (ConfigAccessor ma, ConfigAccessor da) {
    msgConfig = ma.getConfig();
    dataConfig = da.getConfig();
  }
  
  public static void loadData(FileConfiguration data) {
    //todo
  }

  public static String getMsg (String path, String...opt) {
    String msg = msgConfig.getString("prefix")+msgConfig.getString("path");
    if (path.contains("error_")) {
      msg += msgConfig.getString("error_color");
      /*for (int i = opt.length; i==0; --i) {
        msg.replaceAll("%"+i, msgConfig.getString("error_variable_color")+opt[i]+msgConfig.getString("error_color"));
      }*/
    } else {
      msg += msgConfig.getString("message_color");
      /*for (int i = opt.length; i==0; --i) {
        msg.replaceAll("%"+i, msgConfig.getString("message_variable_color")+opt[i]+msgConfig.getString("message_color"));
      }*/
    }
    return msg;
  }
}
