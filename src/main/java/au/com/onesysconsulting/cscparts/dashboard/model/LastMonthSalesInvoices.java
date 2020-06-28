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
@Table(name = "X_VW_OS_SALEINVOICES_LAST_MNT")
public class LastMonthSalesInvoices {
    @Id
    @Column(name = "INVOICETOTAL")
    private Double total;
    @Column(name = "INVOICEQTY")
    private Double quantity;
    @Column(name = "INVOICEPROFIT")
    private Double profit;
}
