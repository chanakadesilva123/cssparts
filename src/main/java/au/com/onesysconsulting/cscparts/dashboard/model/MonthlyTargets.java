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
    @Column(name = "MTD_QTOTE_QTY")
    private Double quoteQty;
    @Column(name = "MTD_ORDER_QTY")
    private Double orderQty;
    @Column(name = "MTD_QTOTE_VAL")
    private Double quoteValue;
    @Column(name = "MTD_ORDER_VAL")
    private Double orderValue;
    @Column(name = "MTD_INV_QTY")
    private Double invoiceQty;
    @Column(name = "MTD_INV_VAL")
    private Double invoiceValue;
    
}
