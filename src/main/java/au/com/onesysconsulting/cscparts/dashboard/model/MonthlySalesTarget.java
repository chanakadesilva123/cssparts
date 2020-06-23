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
@Table(name = "X_VW_OS_SALESTARGET_MDT")
public class MonthlySalesTarget {
    @Id
    @Column(name = "AGE")
    private int month;
    @Column(name = "PERIODNAME")
    private String period;
    @Column(name = "Target")
    private Double target;
    @Column(name = "Sales")
    private Double sales;
}
