package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargetId;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;

@Repository("monthlyTargetsRepository")
public interface MonthlyTargetsRepository extends JpaRepository<MonthlyTargets, MonthlyTargetId> {
    List<MonthlyTargets> findAll();
    Optional<MonthlyTargets> findById(MonthlyTargetId monthlyTargetId);
}
