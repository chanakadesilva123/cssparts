package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.SalesOrdersByPeriod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("salesOrdersByPeriodRepository")
public interface SalesOrdersByPeriodRepository extends JpaRepository<SalesOrdersByPeriod, Integer> {
    List<SalesOrdersByPeriod> findAll();
    @Query(value = "from SalesOrdersByPeriod s where (year=:startYear AND (periodNo BETWEEN 1 AND 6)) OR (year=:endYear AND (periodNo BETWEEN 7 AND 12)) order by year,periodNo")
    public List<SalesOrdersByPeriod> findByBetweenPeriods(@Param("startYear")Integer startYear,@Param("endYear")Integer endYear);
    public List<SalesOrdersByPeriod> findByAgeLessThanOrderByAgeDesc(Integer age);
}
