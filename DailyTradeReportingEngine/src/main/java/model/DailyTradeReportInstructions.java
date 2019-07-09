package main.java.model;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Currency;

public class DailyTradeReportInstructions{

  private final String entity;
  private final String tradeAction;
  private final BigDecimal agreedFx;
  private final Currency currency;
  private final Date instructionDate;
  private final Date settlementDate;
  private final int units;
  private final BigDecimal pricePerUnit;

 public DailyTradeReportInstructions(String entity, String tradeAction, BigDecimal agreedFx,Currency currency, Date instructionDate, Date settlementDate, int units, BigDecimal pricePerUnit) {
        this.entity = entity;
        this.tradeAction= tradeAction;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

  public String getEntity(){
    return entity;
  }
  public String getBuySellAction(){
    return tradeAction;
  }
  public BigDecimal getAgreedFx(){
    return agreedFx;
  }
  public Currency getCurrency(){
    return currency;
  }
  public Date getInstructionDate(){
    return instructionDate;
  }
  public Date getSettlementDate(){
    return settlementDate;
  }
  public int getUnits(){
    return units;
  }
  public BigDecimal getPricePerUnit(){
    return pricePerUnit;
  }
}