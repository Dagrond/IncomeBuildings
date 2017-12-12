package com.gmail.ZiomuuSs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WGBukkit;

import static com.gmail.ZiomuuSs.Utils.UsefulTools.getMsg;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.getData;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.newIncomeBuilding;

public class IncomeBuildingsCommand implements CommandExecutor {
  
  Main plugin;
  public IncomeBuildingsCommand (Main instance) {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("IncomeBuildings") || cmd.getName().equalsIgnoreCase("ib")) {
      if (args.length != 0) {
        if (args[0].equalsIgnoreCase("version")) {
          sender.sendMessage(getMsg("version", "v0.1"));
          return true;
        } else if (args[0].equalsIgnoreCase("create")) {
          if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("IncomeBuildings.create") || sender.hasPermission("IncomeBuildings.*")) {
              if (args.length >= 3) {
                if (WGBukkit.getRegionManager(player.getWorld()).hasRegion(args[1])) {
                  if (newIncomeBuilding(args[2], args[1], player.getWorld(), player.getUniqueId())) {
                    sender.sendMessage(getMsg("successfully_created", args[2]));
                    return true;
                  } else {
                    sender.sendMessage(getMsg("error_exist"));
                    return true;
                  }
                } else {
                  sender.sendMessage(getMsg("error_no_region", args[1]));
                  return true;
                }
              } else {
                sender.sendMessage(getMsg("error_use", "/ib create <region_name> <name>"));
                return true;
              }
            } else {
              sender.sendMessage(getMsg("error_permission"));
              return true;
            }
          } else {
            sender.sendMessage(getMsg("error_player_required"));
            return true;
          }
        } else if (args[0].equalsIgnoreCase("list")) {
          if (sender instanceof Player && !sender.hasPermission("IncomeBuildings.list")) {
            //tralala
            return true;
          } else {
            sender.sendMessage("Lista: "+getData().toString());
            return true;
          }
        }
      } else {
        sender.sendMessage(getMsg("error_no_argument"));
        return true;
      }
    }
    return true;
  }
}
