package au.com.onesysconsulting.cscparts.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

import org.springframework.data.annotation.Immutable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "X_VW_OS_SALEORDERS_BYDATE")
public class SalesOrdersByDate {
    @Id
    @Column(name = "ORDERTOTAL")
    private Double total;
    @Column(name = "ORDERDATE")
    private Timestamp date;
    @Column(name = "ORDERQTY")
    private Double quantity;
    @Column(name = "ORDERPROFIT")
    private Double profit;
    @Column(name = "ORDERVALUETARGET")
    private Double totalTarget;
    @Column(name = "ORDERQTYTARGET")
    private Double quantityTarget;
    @Column(name = "ORDERPROFITTARGET")
    private Double profitTarget;
    @Column(name= "NOOFWORKINGDAYS")
    private Double noOfWorkingDays;
    @Transient
    private Double cumulativeTarget;
    @Transient
    private Double averageTotal;
    @Transient
    private Double averageTarget;
    @Transient
    private Double dailyTarget;
    
}
