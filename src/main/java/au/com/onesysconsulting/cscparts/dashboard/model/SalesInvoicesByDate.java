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
@Table(name = "X_VW_OS_SALEINVOICES_BYDATE")
public class SalesInvoicesByDate {
    @Id
    @Column(name = "INVOICETOTAL")
    private Double total;
    @Column(name = "TRANSDATE")
    private Timestamp date;
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
    @Transient
    private Double cumulativeTarget;
    @Transient
    private Double averageTotal;
}
