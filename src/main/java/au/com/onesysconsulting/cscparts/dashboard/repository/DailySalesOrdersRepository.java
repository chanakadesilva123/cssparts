package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesOrdersRepository")
public interface DailySalesOrdersRepository extends JpaRepository<DailySalesOrders, Integer> {
    List<DailySalesOrders> findAll();

}
