package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargetOrders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlyOrdersTargetRepository")
public interface MonthlyOrdersTargetRepository extends JpaRepository<MonthlyTargetOrders, Integer> {
    List<MonthlyTargetOrders> findAll();

}
