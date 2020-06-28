package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesInvoices;

@Repository("lastMonthSalesInvoicesRepository")
public interface LastMonthSalesInvoicesRepository extends JpaRepository<LastMonthSalesInvoices, Integer> {
    List<LastMonthSalesInvoices> findAll();

}
