package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.SalesQuotesByDate;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("salesQuotesByDateRepository")
public interface SalesQuotesByDateRepository extends JpaRepository<SalesQuotesByDate, Integer> {
    List<SalesQuotesByDate> findAll();
    List<SalesQuotesByDate> findByDate(String date);
    @Query(value = "from SalesQuotesByDate s where date BETWEEN :start AND :end")
    public List<SalesQuotesByDate> findByBetweenDates(@Param("start")Timestamp startDate,@Param("end")Timestamp endDate);
}
