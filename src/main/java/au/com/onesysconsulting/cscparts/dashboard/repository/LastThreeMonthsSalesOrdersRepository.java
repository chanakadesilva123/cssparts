package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesOrders;

@Repository("lastThreeMonthsSalesOrdersRepository")
public interface LastThreeMonthsSalesOrdersRepository extends JpaRepository<LastThreeMonthsSalesOrders, Integer> {
    List<LastThreeMonthsSalesOrders> findAll();

}
