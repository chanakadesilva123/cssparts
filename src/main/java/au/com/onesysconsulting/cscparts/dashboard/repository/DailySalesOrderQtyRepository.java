package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrderQty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesOrderQtyRepository")
public interface DailySalesOrderQtyRepository extends JpaRepository<DailySalesOrderQty, Integer> {
    List<DailySalesOrderQty> findAll();

}
