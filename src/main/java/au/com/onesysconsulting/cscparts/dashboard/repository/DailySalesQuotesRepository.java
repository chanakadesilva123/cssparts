package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;

@Repository("dailySalesQuotesRepository")
public interface DailySalesQuotesRepository extends JpaRepository<DailySalesQuotes, Integer> {
    List<DailySalesQuotes> findAll();

}
