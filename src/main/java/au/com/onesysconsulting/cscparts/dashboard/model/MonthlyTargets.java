package au.com.onesysconsulting.cscparts.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.data.annotation.Immutable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "X_Targets")
public class MonthlyTargets {
    @Id
    @Column(name = "PERIOD_NO")
    private int month;
    @Column(name = "PERIOD_NAME")
    private String period;
    @Column(name = "MTD_QUOTE_QTY")
    private Double quoteQty;
    @Column(name = "MTD_ORDER_QTY")
    private Double orderQty;
    @Column(name = "MTD_QUOTE_VAL")
    private Double quoteValue;
    @Column(name = "MTD_ORDER_VAL")
    private Double orderValue;
    @Column(name = "MTD_INV_QTY")
    private Double invoiceQty;
    @Column(name = "MTD_INV_VAL")
    private Double invoiceValue;
    @Column(name = "MTD_QUOTE_PROFIT")
    private Double quoteProfit;
    @Column(name = "MTD_ORDER_PROFIT")
    private Double orderProfit;
    @Column(name = "MTD_INV_PROFIT")
    private Double invoiceProfit;
    @Column(name = "NO_OF_WORKING_DAYS")
    private int noOfWorkingDays;
    
}
