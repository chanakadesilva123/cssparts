package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesQuotes;

@Repository("lastMonthSalesQuotesRepository")
public interface LastMonthSalesQuotesRepository extends JpaRepository<LastMonthSalesQuotes, Integer> {
    List<LastMonthSalesQuotes> findAll();

}
