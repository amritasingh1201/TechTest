package main.java.business;

import java.util.ArrayList;

import main.java.model.DailyTradeReportInstructions;
public interface IDailyTradeReportGenerator {
  public String generateDailyTradeReport(ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructions);
}