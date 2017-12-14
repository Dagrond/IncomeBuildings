package com.gmail.ZiomuuSs;

import java.util.UUID;

import org.bukkit.World;

public class IncomeBuilding {
  protected String name; //Name of that region
  protected UUID owner; //owner's UUID
  protected String region; //real name of that region (WorldGuard's region name)
  protected World world; //world of that region
  protected long time; //date (in milis) of last income (to region's account)
  protected int account; //current balance of that region
  protected int maxAccount; //max balance of that region
  Main plugin; //instance of main
  
  public IncomeBuilding (Main instance, String region, World world, String name) {
    plugin = instance;
    this.region = region;
    this.name = name;
    this.world = world;
    this.maxAccount = 15000;
    this.time = System.currentTimeMillis();
  }
  
  public void setOwner(UUID player) {
	this.owner = player;
  }

  public int getAccount() {
    return account;
  }

  public int getMaxAccount() {
    return maxAccount;
  }

  public long nextIncome() {
    return System.currentTimeMillis()-time;
  }

  public String getName() {
    return name;
  }
  
  public UUID getOwner() {
    return owner;
  }
  
  public World getWorld() {
    return world;
  }
  
  public String getRegion() {
    return region;
  }
}
