package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrderQty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesOrderQtyRepository")
public interface MonthlySalesOrderQtyRepository extends JpaRepository<MonthlySalesOrderQty, Integer> {
    List<MonthlySalesOrderQty> findAll();

}
