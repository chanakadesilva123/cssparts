package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;

@Repository("monthlyTargetsRepository")
public interface MonthlyTargetsRepository extends JpaRepository<MonthlyTargets, Integer> {
    List<MonthlyTargets> findAll();
    List<MonthlyTargets> findByYearAndMonth(int year, int month);
}
