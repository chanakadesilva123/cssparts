package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesQuotes;

@Repository("lastThreeMonthsSalesQuotesRepository")
public interface LastThreeMonthsSalesQuotesRepository extends JpaRepository<LastThreeMonthsSalesQuotes, Integer> {
    List<LastThreeMonthsSalesQuotes> findAll();

}
