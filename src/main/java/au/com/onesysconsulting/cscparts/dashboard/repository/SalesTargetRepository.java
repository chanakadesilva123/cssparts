package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("salesTargetRepository")
public interface SalesTargetRepository extends JpaRepository<SalesTarget, Integer> {
    List<SalesTarget> findAll();

}
