package au.com.onesysconsulting.cscparts.dashboard.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEntered;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEnteredQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoiced;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoicedQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotesQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesEntered;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoiced;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesEnteredQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesEnteredRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicedQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicedRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesEnteredRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicedRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesTargetRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesTargetRepository;

@Service("dashboardService")
public class DashboardService {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardService.class);

    private MonthlySalesInvoicedRepository monthlySalesInvoicedRepository;
    private DailySalesInvoicedRepository dailySalesInvoicedRepository;
    private MonthlySalesEnteredRepository monthlySalesEnteredRepository;
    private DailySalesEnteredRepository dailySalesEnteredRepository;
    private DailySalesQuotesRepository dailySalesQuotesRepository;
    private MonthlySalesQuotesRepository monthlySalesQuotesRepository;
    private MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository;
    private DailySalesOrderQtyRepository dailySalesOrderQtyRepository;
    private SalesTargetRepository salesTargetRepository;
    private MonthlySalesTargetRepository monthlySalesTargetRepository;
    private DailySalesEnteredQtyRepository dailySalesEnteredQtyRepository;
    private DailySalesQuotesQtyRepository dailySalesQuotesQtyRepository;
    private DailySalesInvoicedQtyRepository dailySalesInvoicedQtyRepository;
    
    
    @Autowired
    public DashboardService(MonthlySalesInvoicedRepository monthlySalesInvoicedRepository,DailySalesInvoicedRepository dailySalesInvoicedRepository,MonthlySalesEnteredRepository monthlySalesEnteredRepository,DailySalesEnteredRepository dailySalesEnteredRepository,DailySalesQuotesRepository dailySalesQuotesRepository, MonthlySalesQuotesRepository monthlySalesQuotesRepository, MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository,DailySalesOrderQtyRepository dailySalesOrderQtyRepository,SalesTargetRepository salesTargetRepository,MonthlySalesTargetRepository monthlySalesTargetRepository,DailySalesEnteredQtyRepository dailySalesEnteredQtyRepository,DailySalesQuotesQtyRepository dailySalesQuotesQtyRepository,DailySalesInvoicedQtyRepository dailySalesInvoicedQtyRepository)
    {
        this.monthlySalesInvoicedRepository = monthlySalesInvoicedRepository;
        this.dailySalesInvoicedRepository = dailySalesInvoicedRepository;
        this.monthlySalesEnteredRepository = monthlySalesEnteredRepository;
        this.dailySalesEnteredRepository = dailySalesEnteredRepository;
        this.dailySalesQuotesRepository = dailySalesQuotesRepository;
        this.monthlySalesQuotesRepository = monthlySalesQuotesRepository;
        this.dailySalesOrderQtyRepository = dailySalesOrderQtyRepository;
        this.monthlySalesOrderQtyRepository = monthlySalesOrderQtyRepository;
        this.salesTargetRepository = salesTargetRepository;
        this.monthlySalesTargetRepository = monthlySalesTargetRepository;
        this.dailySalesEnteredQtyRepository = dailySalesEnteredQtyRepository;
        this.dailySalesQuotesQtyRepository = dailySalesQuotesQtyRepository;
        this.dailySalesInvoicedQtyRepository = dailySalesInvoicedQtyRepository;
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
    
    public double findSalesQuotesDaily() {
		List<DailySalesQuotes> dailySalesQuotesList = this.dailySalesQuotesRepository.findAll();
        if(dailySalesQuotesList!=null && dailySalesQuotesList.size()>0 && dailySalesQuotesList.get(0)!=null && dailySalesQuotesList.get(0).getTotal()!=null && !dailySalesQuotesList.get(0).getTotal().isNaN())
        {
            return dailySalesQuotesList.get(0).getTotal().doubleValue();
        }
        return 0D;
       
    }
    
    public double findSalesQuotesMTD() {
		List<MonthlySalesQuotes> monthlySalesQuotesList = this.monthlySalesQuotesRepository.findAll();
        if(monthlySalesQuotesList!=null && monthlySalesQuotesList.size()>0 && monthlySalesQuotesList.get(0)!=null && monthlySalesQuotesList.get(0).getTotal()!=null && !monthlySalesQuotesList.get(0).getTotal().isNaN())
        {
            return monthlySalesQuotesList.get(0).getTotal().doubleValue();
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
    public int findSalesEnteredQtyDaily() {
		List<DailySalesEnteredQty> dailySalesEnteredQtyList = this.dailySalesEnteredQtyRepository.findAll();
        if(dailySalesEnteredQtyList!=null && dailySalesEnteredQtyList.size()>0 && dailySalesEnteredQtyList.get(0)!=null && dailySalesEnteredQtyList.get(0).getTotal()!=null && !dailySalesEnteredQtyList.get(0).getTotal().isNaN())
        {
            return dailySalesEnteredQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }
    public int findSalesInvoicedQtyDaily() {
		List<DailySalesInvoicedQty> dailySalesInvoicedQtyList = this.dailySalesInvoicedQtyRepository.findAll();
        if(dailySalesInvoicedQtyList!=null && dailySalesInvoicedQtyList.size()>0 && dailySalesInvoicedQtyList.get(0)!=null && dailySalesInvoicedQtyList.get(0).getTotal()!=null && !dailySalesInvoicedQtyList.get(0).getTotal().isNaN())
        {
            return dailySalesInvoicedQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }
    public int findSalesQuotesQtyDaily() {
		List<DailySalesQuotesQty> dailySalesQuotesQtyList = this.dailySalesQuotesQtyRepository.findAll();
        if(dailySalesQuotesQtyList!=null && dailySalesQuotesQtyList.size()>0 && dailySalesQuotesQtyList.get(0)!=null && dailySalesQuotesQtyList.get(0).getTotal()!=null && !dailySalesQuotesQtyList.get(0).getTotal().isNaN())
        {
            return dailySalesQuotesQtyList.get(0).getTotal().intValue();
        }
        return 0;
	}
	public List<SalesTarget> findSalesTarget() {
		return this.salesTargetRepository.findAll();
    }
    
    public List<MonthlySalesTarget> findMonthlySalesTarget() {
		return this.monthlySalesTargetRepository.findAll();
	}

    public double findSalesTargetDaily() {
		List<MonthlySalesTarget> monthlySalesTargetList = this.findMonthlySalesTarget();
        if(monthlySalesTargetList!=null && monthlySalesTargetList.size()>0 && monthlySalesTargetList.get(0)!=null && monthlySalesTargetList.get(0).getTarget()!=null && !monthlySalesTargetList.get(0).getTarget().isNaN())
        {
            double monthlyTraget = monthlySalesTargetList.get(0).getTarget().doubleValue();
            Calendar calendar = Calendar.getInstance();
            int noOfDaysInCurrentMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            LOG.info("noOfDaysInCurrentMonth="+noOfDaysInCurrentMonth+", monthlyTraget="+monthlyTraget);
            return monthlyTraget/noOfDaysInCurrentMonth;
        }
        return 0D;
       
    }
}