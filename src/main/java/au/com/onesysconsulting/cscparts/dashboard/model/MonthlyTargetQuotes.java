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
@Table(name = "X_VW_OS_MTD_QTOTE_TARGET")
public class MonthlyTargetQuotes {
    @Id
    @Column(name = "PERIOD_NO")
    private int periodNo;
    @Column(name = "PERIOD_NAME")
    private String period;
    @Column(name = "MTD_QTOTE_QTY")
    private Double targetQty;
    @Column(name = "MTD_QTOTE_VAL")
    private Double targetValue;
}
