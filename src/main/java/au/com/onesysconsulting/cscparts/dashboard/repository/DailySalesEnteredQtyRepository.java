package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEnteredQty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesEnteredQtyRepository")
public interface DailySalesEnteredQtyRepository extends JpaRepository<DailySalesEnteredQty, Integer> {
    List<DailySalesEnteredQty> findAll();

}
