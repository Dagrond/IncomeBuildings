package com.gmail.ZiomuuSs;

public class IncomeBuilding {
  protected String name;
  protected String owner;
  protected String region;
  protected int time;
  protected int account;
  protected int maxaccount;
  IncomeBuildings plugin;
  
  public IncomeBuilding (IncomeBuildings instance, String name, String region, int time, int account, String owner) {
    plugin = instance;
  }
  
  
  
}
