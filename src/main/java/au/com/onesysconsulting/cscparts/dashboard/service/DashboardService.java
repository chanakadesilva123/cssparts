package au.com.onesysconsulting.cscparts.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEntered;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoiced;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesEntered;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoiced;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesEnteredRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicedRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesEnteredRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicedRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesTargetRepository;

@Service("dashboardService")
public class DashboardService {

    private MonthlySalesInvoicedRepository monthlySalesInvoicedRepository;
    private DailySalesInvoicedRepository dailySalesInvoicedRepository;
    private MonthlySalesEnteredRepository monthlySalesEnteredRepository;
    private DailySalesEnteredRepository dailySalesEnteredRepository;
    private MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository;
    private DailySalesOrderQtyRepository dailySalesOrderQtyRepository;
    private SalesTargetRepository salesTargetRepository;
    
    @Autowired
    public DashboardService(MonthlySalesInvoicedRepository monthlySalesInvoicedRepository,DailySalesInvoicedRepository dailySalesInvoicedRepository,MonthlySalesEnteredRepository monthlySalesEnteredRepository,DailySalesEnteredRepository dailySalesEnteredRepository,MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository,DailySalesOrderQtyRepository dailySalesOrderQtyRepository,SalesTargetRepository salesTargetRepository)
    {
        this.monthlySalesInvoicedRepository = monthlySalesInvoicedRepository;
        this.dailySalesInvoicedRepository = dailySalesInvoicedRepository;
        this.monthlySalesEnteredRepository = monthlySalesEnteredRepository;
        this.dailySalesEnteredRepository = dailySalesEnteredRepository;
        this.dailySalesOrderQtyRepository = dailySalesOrderQtyRepository;
        this.monthlySalesOrderQtyRepository = monthlySalesOrderQtyRepository;
        this.salesTargetRepository = salesTargetRepository;
    }

    public double findSalesInvoicedMTD()
    {
        List<MonthlySalesInvoiced> monthlySalesInvoicedList = this.monthlySalesInvoicedRepository.findAll();
        if(monthlySalesInvoicedList!=null && monthlySalesInvoicedList.size()>0 && monthlySalesInvoicedList.get(0)!=null && monthlySalesInvoicedList.get(0).getTotal()!=null && !monthlySalesInvoicedList.get(0).getTotal().isNaN())
        {
            return monthlySalesInvoicedList.get(0).getTotal().doubleValue();
        }
        
            return 0D;
        
    }
    public double findSalesInvoicedDaily()
    {
        List<DailySalesInvoiced> dailySalesInvoicedList = this.dailySalesInvoicedRepository.findAll();
        if(dailySalesInvoicedList!=null && dailySalesInvoicedList.size()>0 && dailySalesInvoicedList.get(0)!=null && dailySalesInvoicedList.get(0).getTotal()!=null && !dailySalesInvoicedList.get(0).getTotal().isNaN())
        {
            return dailySalesInvoicedList.get(0).getTotal().doubleValue();
        }
        
            return 0D;
        
    }

	public double findSalesEnteredMTD() {
		List<MonthlySalesEntered> monthlySalesEnteredList = this.monthlySalesEnteredRepository.findAll();
        if(monthlySalesEnteredList!=null && monthlySalesEnteredList.size()>0 && monthlySalesEnteredList.get(0)!=null && monthlySalesEnteredList.get(0).getTotal()!=null && !monthlySalesEnteredList.get(0).getTotal().isNaN())
        {
            return monthlySalesEnteredList.get(0).getTotal().doubleValue();
        }
        
            return 0D;
        
	}

	public double findSalesEnteredDaily() {
		List<DailySalesEntered> dailySalesEnteredList = this.dailySalesEnteredRepository.findAll();
        if(dailySalesEnteredList!=null && dailySalesEnteredList.size()>0 && dailySalesEnteredList.get(0)!=null && dailySalesEnteredList.get(0).getTotal()!=null && !dailySalesEnteredList.get(0).getTotal().isNaN())
        {
            return dailySalesEnteredList.get(0).getTotal().doubleValue();
        }
        return 0D;
       
	}

	public int findSalesOrdersQtyMTD() {
		List<MonthlySalesOrderQty> monthlySalesOrderQtyList = this.monthlySalesOrderQtyRepository.findAll();
        if(monthlySalesOrderQtyList!=null && monthlySalesOrderQtyList.size()>0 && monthlySalesOrderQtyList.get(0)!=null && monthlySalesOrderQtyList.get(0).getTotal()!=null && !monthlySalesOrderQtyList.get(0).getTotal().isNaN())
        {
            return monthlySalesOrderQtyList.get(0).getTotal().intValue();
        }
       
            return 0;
        
	}

	public int findSalesOrdersQtyDaily() {
		List<DailySalesOrderQty> dailySalesOrderQtyList = this.dailySalesOrderQtyRepository.findAll();
        if(dailySalesOrderQtyList!=null && dailySalesOrderQtyList.size()>0 && dailySalesOrderQtyList.get(0)!=null && dailySalesOrderQtyList.get(0).getTotal()!=null && !dailySalesOrderQtyList.get(0).getTotal().isNaN())
        {
            return dailySalesOrderQtyList.get(0).getTotal().intValue();
        }
        return 0;
	}

	public List<SalesTarget> findSalesTarget() {
		return this.salesTargetRepository.findAll();
	}

}