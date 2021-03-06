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
@Table(name = "X_VW_OS_SALEORDERS_MTD")
public class MonthlySalesOrders {
    @Id
    @Column(name = "ORDERTOTAL")
    private Double total;
    @Column(name = "ORDERQTY")
    private Double quantity;
    @Column(name = "ORDERPROFIT")
    private Double profit;
}
