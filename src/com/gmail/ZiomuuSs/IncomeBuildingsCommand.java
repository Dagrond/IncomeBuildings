package com.gmail.ZiomuuSs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IncomeBuildingsCommand implements CommandExecutor {
  
  IncomeBuildings plugin;
  public IncomeBuildingsCommand (IncomeBuildings instance) {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("IncomeBuildings") || cmd.getName().equalsIgnoreCase("ib")) {
      if (args.length != 0) {
        if (args[0].equalsIgnoreCase("version")) {
          sender.sendMessage("IncomeBuildings wersja: v0.1");
          return true;
        }
      } else {
        sender.sendMessage("Tutaj bedzie error ze trzeba podac argument czy costam");
        return true;
      }
    }
    return true;
  }
}
