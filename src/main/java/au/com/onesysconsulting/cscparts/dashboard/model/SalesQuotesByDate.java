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
@Table(name = "X_VW_OS_SALEQUOTES_BYDATE")
public class SalesQuotesByDate {
    @Id
    @Column(name = "QUOTETOTAL")
    private Double total;
    @Column(name = "ORDERDATE")
    private Timestamp date;
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
    @Transient
    private Double cumulativeTarget;
    @Transient
    private Double averageTotal;
}
