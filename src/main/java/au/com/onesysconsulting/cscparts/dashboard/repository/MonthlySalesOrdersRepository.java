package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesOrdersRepository")
public interface MonthlySalesOrdersRepository extends JpaRepository<MonthlySalesOrders, Integer> {
    List<MonthlySalesOrders> findAll();

}
