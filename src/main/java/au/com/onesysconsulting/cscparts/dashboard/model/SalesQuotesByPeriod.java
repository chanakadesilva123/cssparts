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
@Table(name = "X_VW_OS_SALEQUOTES_BYPERIOD")
public class SalesQuotesByPeriod {
    @Id
    @Column(name = "YEARPERIODNO")
    private String yearPeriod;
    @Column(name = "QUOTETOTAL")
    private Double total;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "PERIOD_NO")
    private Integer periodNo;
    @Column(name = "QUOTEQTY")
    private Double quantity;
    @Column(name = "QUOTEPROFIT")
    private Double profit;
    @Column(name = "QUOTEVALUETARGET")
    private Double totalTarget;
    @Column(name = "QUOTEQTYTARGET")
    private Double quantityTarget;
    @Column(name = "QUOTEPROFITTARGET")
    private Double profitTarget;
    @Column(name= "NOOFWORKINGDAYS")
    private Double noOfWorkingDays;
    @Transient
    private Double cumulativeTarget;
    @Transient
    private Double cumulativeTotal;
    @Transient
    private Double averageTotal;
    @Transient
    private Double averageTarget;
}
