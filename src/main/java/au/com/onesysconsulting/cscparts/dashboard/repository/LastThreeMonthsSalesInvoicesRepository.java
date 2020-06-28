package au.com.onesysconsulting.cscparts.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesInvoices;

@Repository("lastThreeMonthsSalesInvoicesRepository")
public interface LastThreeMonthsSalesInvoicesRepository extends JpaRepository<LastThreeMonthsSalesInvoices, Integer> {
    List<LastThreeMonthsSalesInvoices> findAll();

}
