package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesOrders;

@Repository("lastMonthSalesOrdersRepository")
public interface LastMonthSalesOrdersRepository extends JpaRepository<LastMonthSalesOrders, Integer> {
    List<LastMonthSalesOrders> findAll();

}
