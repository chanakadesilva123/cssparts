package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.SalesInvoicesByDate;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("salesInvoicesByDateRepository")
public interface SalesInvoicesByDateRepository extends JpaRepository<SalesInvoicesByDate, Integer> {
    List<SalesInvoicesByDate> findAll();
    List<SalesInvoicesByDate> findByDate(String date);
    @Query(value = "from SalesInvoicesByDate s where date BETWEEN :start AND :end")
    public List<SalesInvoicesByDate> findByBetweenDates(@Param("start")Timestamp startDate,@Param("end")Timestamp endDate);
}
