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
@Table(name = "X_VW_OS_SALEQUOTES_MTD")
public class MonthlySalesQuotes {
    @Id
    @Column(name = "QUOTETOTAL")
    private Double total;
    @Column(name = "QUOTEQTY")
    private Double quantity;
    @Column(name = "QUOTEPROFIT")
    private Double profit;
}
