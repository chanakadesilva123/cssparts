package au.com.onesysconsulting.cscparts.dashboard.repository;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoicedQty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dailySalesInvoicedQtyRepository")
public interface DailySalesInvoicedQtyRepository extends JpaRepository<DailySalesInvoicedQty, Integer> {
    List<DailySalesInvoicedQty> findAll();

}
