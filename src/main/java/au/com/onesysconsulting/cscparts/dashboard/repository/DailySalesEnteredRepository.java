package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEntered;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesEnteredRepository")
public interface DailySalesEnteredRepository extends JpaRepository<DailySalesEntered, Integer> {
    List<DailySalesEntered> findAll();

}
