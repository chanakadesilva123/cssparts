package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesInvoicesRepository")
public interface MonthlySalesInvoicesRepository extends JpaRepository<MonthlySalesInvoices, Integer> {
    List<MonthlySalesInvoices> findAll();

}
