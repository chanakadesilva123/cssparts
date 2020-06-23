package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoiced;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesInvoicedRepository")
public interface DailySalesInvoicedRepository extends JpaRepository<DailySalesInvoiced, Integer> {
    List<DailySalesInvoiced> findAll();

}
