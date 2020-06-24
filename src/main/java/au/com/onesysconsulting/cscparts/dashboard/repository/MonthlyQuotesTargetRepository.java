package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargetQuotes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlyQuotesTargetRepository")
public interface MonthlyQuotesTargetRepository extends JpaRepository<MonthlyTargetQuotes, Integer> {
    List<MonthlyTargetQuotes> findAll();

}
