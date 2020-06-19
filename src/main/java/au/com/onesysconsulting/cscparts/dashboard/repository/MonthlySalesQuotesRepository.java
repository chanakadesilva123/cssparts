package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;

@Repository("monthlySalesQuotesRepository")
public interface MonthlySalesQuotesRepository extends JpaRepository<MonthlySalesQuotes, Integer> {
    List<MonthlySalesQuotes> findAll();

}
