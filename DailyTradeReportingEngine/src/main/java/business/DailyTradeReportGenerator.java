package main.java.business;

import java.util.ArrayList;

import main.java.model.DailyTradeReportInstructions;
import main.java.util.ReportUtil;
public class DailyTradeReportGenerator implements IDailyTradeReportGenerator {
  

@Override
public String generateDailyTradeReport(ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructions) {
	StringBuilder dailyTradeReportData = new StringBuilder();
	dailyTradeReportData.append(ReportUtil.createOutgoingAmountData(dailyTradeReportInstructions));
	dailyTradeReportData.append(ReportUtil.createIncomingAmountData(dailyTradeReportInstructions));
	dailyTradeReportData.append(ReportUtil.createOutgoingRank());
	dailyTradeReportData.append(ReportUtil.createIncomingRank());
	return dailyTradeReportData.toString();
}
}
