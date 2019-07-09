package main.java.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashMap;

import main.java.model.DailyTradeReportInstructions;

/**
 * @author Amrita Singh
 *
 */
public class ReportUtil {
	public static Map<String, BigDecimal> USDSettledIncomingAmount = new HashMap<String, BigDecimal>();
	public static Map<String, BigDecimal> USDSettledOutgoingAmount = new HashMap<String, BigDecimal>();
	public static Map<String, BigDecimal> USDSettledIncomingRanking = new HashMap<String, BigDecimal>();
	public static Map<String, BigDecimal> USDSettledOutgoingRanking = new HashMap<String, BigDecimal>();
	public static String BUY = "B";
	public static String SELL = "S";
	static DateFormat settlementDateFormat = new SimpleDateFormat("dd MMM yyyy");
	public static Calendar calendarObj = Calendar.getInstance();

	/**
	 * Method to generate data for incoming USD amount
	 * 
	 * @param dailyTradeReportInstructions
	 * @return
	 */

	public static String createIncomingAmountData(
			ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructions) {
		StringBuilder incomingAmountData = new StringBuilder();
		for (DailyTradeReportInstructions tradeReportInstructions : dailyTradeReportInstructions) {
			populateUSDAmountData(tradeReportInstructions);
		}
		incomingAmountData.append("\n|| Incoming USD Settlements with Settlement Dates ||");

		for (String settlementDate : USDSettledIncomingAmount.keySet()) {
			incomingAmountData.append("\n||SettlementDate : ").append(settlementDate).append(" ||IncomingAmount : ")
					.append(USDSettledIncomingAmount.get(settlementDate)).append("||");
		}
		return incomingAmountData.toString();
	}

	/**
	 * Method to generate data for Outgoing USD amount
	 * 
	 * @param dailyTradeReportInstructions
	 * @return
	 */
	public static String createOutgoingAmountData(
			ArrayList<DailyTradeReportInstructions> dailyTradeReportInstructions) {
		StringBuilder outgoingAmountData = new StringBuilder();
		for (DailyTradeReportInstructions tradeReportInstructions : dailyTradeReportInstructions) {
			populateUSDAmountData(tradeReportInstructions);
		}
		outgoingAmountData.append("\n|| Outgoing USD Settlements with Settlement Dates ||");

		for (String settlementDate : USDSettledOutgoingAmount.keySet()) {
			outgoingAmountData.append("\n||SettlementDate : ").append(settlementDate).append(" ||OutgoingAmount : ")
					.append(USDSettledOutgoingAmount.get(settlementDate)).append("||");
		}
		return outgoingAmountData.toString();
	}

	/**
	 * Method to display Ranking of Entities for Outgoing USD amount from Highest to
	 * Lowest
	 * 
	 * @return
	 */
	public static String createOutgoingRank() {
		StringBuilder outgoingRankingData = new StringBuilder();
		Set<Entry<String, BigDecimal>> outgoingRankingMapEntries = USDSettledOutgoingRanking.entrySet();
		List<Entry<String, BigDecimal>> outgoingRankingList = new LinkedList<Entry<String, BigDecimal>>(
				outgoingRankingMapEntries);
		Collections.sort(outgoingRankingList, new Comparator<Entry<String, BigDecimal>>() {
			@Override
			public int compare(Entry<String, BigDecimal> ele1, Entry<String, BigDecimal> ele2) {
				return ele2.getValue().compareTo(ele1.getValue());
			}
		});
		Map<String, BigDecimal> outgoingRankingMap = new LinkedHashMap<String, BigDecimal>();
		for (Entry<String, BigDecimal> entry : outgoingRankingList) {
			outgoingRankingMap.put(entry.getKey(), entry.getValue());
		}
		outgoingRankingData.append("\n|| Ranking of outgoing USD Settlements - Highest to Lowest || ");
		for (Entry<String, BigDecimal> entry : outgoingRankingMap.entrySet()) {
			outgoingRankingData.append("\n|| Entity : " + entry.getKey() + " || Amount : " + entry.getValue())
					.append(" ||");
		}

		return outgoingRankingData.toString();
	}

	/**
	 * Method to display Ranking of Entities for Incoming USD amount from Highest to
	 * Lowest
	 * 
	 * @return
	 */
	public static String createIncomingRank() {
		StringBuilder incomingRankingData = new StringBuilder();
		Set<Entry<String, BigDecimal>> incomingRankingMapEntries = USDSettledIncomingRanking.entrySet();
		List<Entry<String, BigDecimal>> incomingRankingList = new LinkedList<Entry<String, BigDecimal>>(
				incomingRankingMapEntries);
		Collections.sort(incomingRankingList, new Comparator<Entry<String, BigDecimal>>() {
			@Override
			public int compare(Entry<String, BigDecimal> ele1, Entry<String, BigDecimal> ele2) {
				return ele2.getValue().compareTo(ele1.getValue());
			}
		});
		Map<String, BigDecimal> outgoingRankingMap = new LinkedHashMap<String, BigDecimal>();
		for (Entry<String, BigDecimal> entry : incomingRankingList) {
			outgoingRankingMap.put(entry.getKey(), entry.getValue());
		}
		incomingRankingData.append("\n|| Ranking of incoming USD Settlements - Highest to Lowest ||");
		for (Entry<String, BigDecimal> entry : outgoingRankingMap.entrySet()) {
			incomingRankingData.append("\n||Entity : " + entry.getKey() + " || Amount : " + entry.getValue())
					.append(" ||");
		}

		return incomingRankingData.toString();
	}

	/**
	 * Method to get Settlement Date
	 * @param tradeReportInstructions
	 * @return
	 */
	private static String getSettlementDate(DailyTradeReportInstructions tradeReportInstructions) {
		Date inputDate = tradeReportInstructions.getSettlementDate();
		calendarObj.setTime(inputDate);
		if (tradeReportInstructions.getCurrency().equals(Currency.getInstance("AED"))
				|| tradeReportInstructions.getCurrency().equals(Currency.getInstance("SAR"))) {
			if (inputDate.toString().startsWith("Fri")) {
				calendarObj.add(Calendar.DATE, 3);
			} else if (inputDate.toString().startsWith("Sat")) {
				calendarObj.add(Calendar.DATE, 2);
			}
		} else {
			if (inputDate.toString().startsWith("Sat")) {
				calendarObj.add(Calendar.DATE, 2);
			} else if (inputDate.toString().startsWith("Sun")) {
				calendarObj.add(Calendar.DATE, 1);
			}
		}
		String settlementDate = settlementDateFormat.format(calendarObj.getTime()).toString();
		return settlementDate;
	}

	/**
	 * Method to calculate USD amount
	 * @param pricePerUnit
	 * @param units
	 * @param AgreedFx
	 * @return
	 */
	private static BigDecimal calculateUSDAmount(BigDecimal pricePerUnit, int units, BigDecimal AgreedFx) {
		return pricePerUnit.multiply(AgreedFx).multiply(new BigDecimal(units));
	}

	/**
	 * Method to populate USD incoming and outgoing Amount data in report
	 * @param tradeReportInstructions
	 */
	private static void populateUSDAmountData(DailyTradeReportInstructions tradeReportInstructions) {
		String settlementDate = getSettlementDate(tradeReportInstructions);
		if (tradeReportInstructions.getBuySellAction().equalsIgnoreCase(SELL)) {
			BigDecimal USDIncomingAmount = calculateUSDAmount(tradeReportInstructions.getPricePerUnit(),
					tradeReportInstructions.getUnits(), tradeReportInstructions.getAgreedFx());
			if (USDSettledIncomingAmount.containsKey(settlementDate)) {
				USDIncomingAmount = USDIncomingAmount.add(USDSettledIncomingAmount.get(settlementDate));
				USDSettledIncomingAmount.put(settlementDate, USDIncomingAmount);
			} else {
				USDSettledIncomingAmount.put(settlementDate, USDIncomingAmount);
			}
			USDSettledIncomingRanking.put(tradeReportInstructions.getEntity(), USDIncomingAmount);
		} else if (tradeReportInstructions.getBuySellAction().equalsIgnoreCase(BUY)) {

			BigDecimal USDOutgoingAmount = calculateUSDAmount(tradeReportInstructions.getPricePerUnit(),
					tradeReportInstructions.getUnits(), tradeReportInstructions.getAgreedFx());
			if (USDSettledOutgoingAmount.containsKey(settlementDate)) {
				USDOutgoingAmount = USDOutgoingAmount.add(USDSettledOutgoingAmount.get(settlementDate));
				USDSettledOutgoingAmount.put(settlementDate, USDOutgoingAmount);
			} else {
				USDSettledOutgoingAmount.put(settlementDate, USDOutgoingAmount);
			}

			USDSettledOutgoingRanking.put(tradeReportInstructions.getEntity(), USDOutgoingAmount);

		}
	}
}
