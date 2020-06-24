package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotesQty;

@Repository("monthlySalesQuotesQtyRepository")
public interface MonthlySalesQuotesQtyRepository extends JpaRepository<MonthlySalesQuotesQty, Integer> {
    List<MonthlySalesQuotesQty> findAll();

}
