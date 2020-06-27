package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesInvoicesRepository")
public interface DailySalesInvoicesRepository extends JpaRepository<DailySalesInvoices, Integer> {
    List<DailySalesInvoices> findAll();

}
