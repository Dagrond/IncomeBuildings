package com.gmail.ZiomuuSs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.gmail.ZiomuuSs.Utils.UsefulTools.getMsg;

public class IncomeBuildingsCommand implements CommandExecutor {
  
  IncomeBuildings plugin;
  public IncomeBuildingsCommand (IncomeBuildings instance) {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("IncomeBuildings") || cmd.getName().equalsIgnoreCase("ib")) {
      if (args.length != 0) {
        if (args[0].equalsIgnoreCase("version")) {
          sender.sendMessage(getMsg("version", "v0.1"));
          return true;
        }
      } else {
        sender.sendMessage(getMsg("error_no_argument"));
        return true;
      }
    }
    return true;
  }
}
