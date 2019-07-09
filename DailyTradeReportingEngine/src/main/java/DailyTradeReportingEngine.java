/**
 * 
 */
package main.java;

import java.util.ArrayList;

import main.java.business.DailyTradeReportGenerator;
import main.java.business.IDailyTradeReportGenerator;
import main.java.data.SampleDataCreator;
import main.java.model.DailyTradeReportInstructions;

/**
 * @author Amrita
 *
 */
public class DailyTradeReportingEngine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructions = SampleDataCreator
				.getDailyTradeReportInstructions();

		IDailyTradeReportGenerator dailyTradeReportGenerator = new DailyTradeReportGenerator();
		System.out.println(dailyTradeReportGenerator.generateDailyTradeReport(dailyTradeReportInstructions));
	}

}
