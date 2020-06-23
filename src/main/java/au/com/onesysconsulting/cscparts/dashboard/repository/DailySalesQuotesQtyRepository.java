package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotesQty;

@Repository("dailySalesQuotesQtyRepository")
public interface DailySalesQuotesQtyRepository extends JpaRepository<DailySalesQuotesQty, Integer> {
    List<DailySalesQuotesQty> findAll();

}
