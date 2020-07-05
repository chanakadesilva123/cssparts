package au.com.onesysconsulting.cscparts.dashboard.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class MonthlyTargetId implements Serializable {

    @Getter @Setter
    int id;
    @Getter @Setter
    int month;
}