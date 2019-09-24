package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoiced;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesInvoicedRepository")
public interface MonthlySalesInvoicedRepository extends JpaRepository<MonthlySalesInvoiced, Integer> {
    List<MonthlySalesInvoiced> findAll();

}
