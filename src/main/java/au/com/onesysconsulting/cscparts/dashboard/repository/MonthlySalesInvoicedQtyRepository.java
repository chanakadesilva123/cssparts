package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoicedQty;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySalesInvoicedQtyRepository")
public interface MonthlySalesInvoicedQtyRepository extends JpaRepository<MonthlySalesInvoicedQty, Integer> {
    List<MonthlySalesInvoicedQty> findAll();

}
