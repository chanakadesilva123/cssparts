package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesEntered;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesEnteredRepository")
public interface MonthlySalesEnteredRepository extends JpaRepository<MonthlySalesEntered, Integer> {
    List<MonthlySalesEntered> findAll();

}
