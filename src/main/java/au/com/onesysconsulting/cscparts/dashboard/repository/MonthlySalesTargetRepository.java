package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesTarget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesTargetRepository")
public interface MonthlySalesTargetRepository extends JpaRepository<MonthlySalesTarget, Integer> {
    List<MonthlySalesTarget> findAll();

}
