package com.gmail.ZiomuuSs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WGBukkit;

import static com.gmail.ZiomuuSs.Utils.UsefulTools.getMsg;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.getSimpleMsg;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.getData;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.newIncomeBuilding;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.getProperties;
import static com.gmail.ZiomuuSs.Utils.UsefulTools.getBuilding;

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
                  if (newIncomeBuilding(args[2], args[1], player.getWorld())) {
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
        } else if (args[0].equalsIgnoreCase("info")) {
          if (sender instanceof Player && !sender.hasPermission("IncomeBuildings.list") && !sender.hasPermission("IncomeBuildings.*")) {
            Player player = (Player) sender;
            if (args.length > 1) {
              if (getProperties(player.getUniqueId()).toString().contains(args[1])) {
                //info about player building
              } else {
                //info about someone's else building (So, there's fewer info)
              }
            } else if (getProperties(player.getUniqueId()).size() == 1) {
              //info about player building (if he owns only 1 building)
            } else {
              sender.sendMessage(getMsg("error_use", "/ib info <name>"));
              return true;
            }
          } else {
            if (args.length > 1) {
              if (getBuilding(args[1]) != null) {
                //full info about someone's else building
                IncomeBuilding prop = getBuilding(args[1]);
                sender.sendMessage(getMsg("info_building_admin", args[1]));
                if (prop.getOwner() == null)
                  sender.sendMessage(getMsg("info_owner", getSimpleMsg("none")));
                else
                  sender.sendMessage(getMsg("info_owner", Bukkit.getOfflinePlayer(prop.getOwner()).getName()));
                if (prop.getRegion() == null)
                  sender.sendMessage(getMsg("info_region", getSimpleMsg("none")));
                else
                  sender.sendMessage(getMsg("info_region", prop.getRegion(), prop.getWorld().toString()));
                int hours = Math.toIntExact(prop.nextIncome()/60/60/60);
                int minutes = Math.toIntExact((prop.nextIncome()/60/60)%60/10);
                sender.sendMessage(getMsg("info_next_income", Integer.toString(hours), Integer.toString(minutes)));
                sender.sendMessage(getMsg("info_account", Integer.toString(prop.getAccount()), Integer.toString(prop.getMaxAccount())));
                return true;
              } else {
                sender.sendMessage(getMsg("error_no_building", args[1]));
                return true;
              }
            } else {
              sender.sendMessage(getMsg("error_use", "/ib info <name>"));
              return true;
            }
          }
        } else if (args[0].equalsIgnoreCase("list")) {
          if (sender instanceof Player && !sender.hasPermission("IncomeBuildings.list") && !sender.hasPermission("IncomeBuildings.*")) {
            //list of player's buildings
            return true;
          } else {
            if (args.length == 1) {
              sender.sendMessage("Temp list: "+getData().toString());
              return true;
            } else {
              //list of someone's buildings
            }
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
