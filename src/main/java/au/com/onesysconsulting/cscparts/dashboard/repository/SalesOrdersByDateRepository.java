package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.SalesOrdersByDate;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("salesOrdersByDateRepository")
public interface SalesOrdersByDateRepository extends JpaRepository<SalesOrdersByDate, Integer> {
    List<SalesOrdersByDate> findAll();
    List<SalesOrdersByDate> findByDate(String date);
    @Query(value = "from SalesOrdersByDate s where date BETWEEN :start AND :end")
    public List<SalesOrdersByDate> findByBetweenDates(@Param("start")Timestamp startDate,@Param("end")Timestamp endDate);
}
