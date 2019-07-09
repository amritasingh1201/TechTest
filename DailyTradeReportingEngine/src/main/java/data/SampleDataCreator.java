package main.java.data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;

import main.java.model.DailyTradeReportInstructions;

public class SampleDataCreator {
	final static ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructionsList = new ArrayList<DailyTradeReportInstructions>();

	public static ArrayList<DailyTradeReportInstructions> getDailyTradeReportInstructions() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
			DailyTradeReportInstructions dailyTradeReportInstructions1 = new DailyTradeReportInstructions("foo", "S",
					BigDecimal.valueOf(0.50), Currency.getInstance("AED"), formatter.parse("02 Jul 2019"),
					formatter.parse("02 Jul 2019"), 200, BigDecimal.valueOf(100.50));
			DailyTradeReportInstructions dailyTradeReportInstructions2 = new DailyTradeReportInstructions("aaa", "S",
					BigDecimal.valueOf(0.50), Currency.getInstance("SGD"), formatter.parse("06 Jul 2019"),
					formatter.parse("06 Jul 2019"), 200, BigDecimal.valueOf(200.50));

			dailyTradeReportInstructionsList.add(dailyTradeReportInstructions1);
			dailyTradeReportInstructionsList.add(dailyTradeReportInstructions2);

	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dailyTradeReportInstructionsList;
	}
}