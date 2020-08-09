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
@Table(name = "X_VW_OS_SALEINVOICES_BYPERIOD")
public class SalesInvoicesByPeriod {
    @Id
    @Column(name = "YEARPERIODNO")
    private String yearPeriod;
    @Column(name = "INVOICETOTAL")
    private Double total;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "PERIOD_NO")
    private Integer periodNo;
    @Column(name = "INVOICEQTY")
    private Double quantity;
    @Column(name = "INVOICEPROFIT")
    private Double profit;
    @Column(name = "INVOICEVALUETARGET")
    private Double totalTarget;
    @Column(name = "INVOICEQTYTARGET")
    private Double quantityTarget;
    @Column(name = "INVOICEPROFITTARGET")
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
