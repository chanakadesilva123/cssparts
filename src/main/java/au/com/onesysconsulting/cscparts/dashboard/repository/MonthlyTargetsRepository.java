package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlyTargetsRepository")
public interface MonthlyTargetsRepository extends JpaRepository<MonthlyTargets, Integer> {
    List<MonthlyTargets> findAll();
    MonthlyTargets findByMonth(int month);

}
