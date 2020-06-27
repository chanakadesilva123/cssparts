package au.com.onesysconsulting.cscparts.dashboard.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEnteredQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoicedQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotesQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoicedQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotesQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesEnteredQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicedQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicedQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesQuotesQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesTargetRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlyTargetsRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesTargetRepository;

@Service("dashboardService")
public class DashboardService {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardService.class);

    private MonthlySalesInvoicesRepository monthlySalesInvoicesRepository;
    private DailySalesInvoicesRepository dailySalesInvoicesRepository;
    private MonthlySalesOrdersRepository monthlySalesOrdersRepository;
    private DailySalesOrdersRepository dailySalesOrdersRepository;
    private DailySalesQuotesRepository dailySalesQuotesRepository;
    private MonthlySalesQuotesRepository monthlySalesQuotesRepository;
    private MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository;
    private DailySalesOrderQtyRepository dailySalesOrderQtyRepository;
    private SalesTargetRepository salesTargetRepository;
    private MonthlySalesTargetRepository monthlySalesTargetRepository;
    private DailySalesEnteredQtyRepository dailySalesEnteredQtyRepository;
    private DailySalesQuotesQtyRepository dailySalesQuotesQtyRepository;
    private DailySalesInvoicedQtyRepository dailySalesInvoicedQtyRepository;
    private MonthlySalesInvoicedQtyRepository monthlySalesInvoicedQtyRepository;
    private MonthlySalesQuotesQtyRepository monthlySalesQuotesQtyRepository;
    private MonthlyTargetsRepository monthlyTargetsRepository;

    @Autowired
    public DashboardService(MonthlySalesInvoicesRepository monthlySalesInvoicesRepository,DailySalesInvoicesRepository dailySalesInvoicesRepository,MonthlySalesOrdersRepository monthlySalesOrdersRepository,DailySalesOrdersRepository dailySalesOrdersRepository,DailySalesQuotesRepository dailySalesQuotesRepository, MonthlySalesQuotesRepository monthlySalesQuotesRepository, MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository,DailySalesOrderQtyRepository dailySalesOrderQtyRepository,SalesTargetRepository salesTargetRepository,MonthlySalesTargetRepository monthlySalesTargetRepository,DailySalesEnteredQtyRepository dailySalesEnteredQtyRepository,DailySalesQuotesQtyRepository dailySalesQuotesQtyRepository,DailySalesInvoicedQtyRepository dailySalesInvoicedQtyRepository,MonthlySalesInvoicedQtyRepository monthlySalesInvoicedQtyRepository,MonthlySalesQuotesQtyRepository monthlySalesQuotesQtyRepository,MonthlyTargetsRepository monthlyTargetsRepository)
    {
        this.monthlySalesInvoicesRepository = monthlySalesInvoicesRepository;
        this.dailySalesInvoicesRepository = dailySalesInvoicesRepository;
        this.monthlySalesOrdersRepository = monthlySalesOrdersRepository;
        this.dailySalesOrdersRepository = dailySalesOrdersRepository;
        this.dailySalesQuotesRepository = dailySalesQuotesRepository;
        this.monthlySalesQuotesRepository = monthlySalesQuotesRepository;
        this.dailySalesOrderQtyRepository = dailySalesOrderQtyRepository;
        this.monthlySalesOrderQtyRepository = monthlySalesOrderQtyRepository;
        this.salesTargetRepository = salesTargetRepository;
        this.monthlySalesTargetRepository = monthlySalesTargetRepository;
        this.dailySalesEnteredQtyRepository = dailySalesEnteredQtyRepository;
        this.dailySalesQuotesQtyRepository = dailySalesQuotesQtyRepository;
        this.dailySalesInvoicedQtyRepository = dailySalesInvoicedQtyRepository;
        this.monthlySalesInvoicedQtyRepository = monthlySalesInvoicedQtyRepository;
        this.monthlySalesQuotesQtyRepository = monthlySalesQuotesQtyRepository;
        this.monthlyTargetsRepository = monthlyTargetsRepository;
    }

    public MonthlySalesInvoices findSalesInvoicesMTD()
    {
        List<MonthlySalesInvoices> monthlySalesInvoicedList = this.monthlySalesInvoicesRepository.findAll();
        if(monthlySalesInvoicedList!=null && monthlySalesInvoicedList.size()>0 && monthlySalesInvoicedList.get(0)!=null && monthlySalesInvoicedList.get(0).getTotal()!=null && !monthlySalesInvoicedList.get(0).getTotal().isNaN())
        {
            return monthlySalesInvoicedList.get(0);
        }
        MonthlySalesInvoices monthlySalesInvoices = new MonthlySalesInvoices();
        monthlySalesInvoices.setTotal(0D);
        monthlySalesInvoices.setQuantity(0D);
        monthlySalesInvoices.setProfit(0D);
        return monthlySalesInvoices;
        
    }
    public DailySalesInvoices findSalesInvoicesDaily()
    {
        List<DailySalesInvoices> dailySalesInvoicedList = this.dailySalesInvoicesRepository.findAll();
        if(dailySalesInvoicedList!=null && dailySalesInvoicedList.size()>0 && dailySalesInvoicedList.get(0)!=null && dailySalesInvoicedList.get(0).getTotal()!=null && !dailySalesInvoicedList.get(0).getTotal().isNaN())
        {
            return dailySalesInvoicedList.get(0);
        }
        DailySalesInvoices dailySalesInvoices = new DailySalesInvoices();
        dailySalesInvoices.setTotal(0D);
        dailySalesInvoices.setQuantity(0D);
        dailySalesInvoices.setProfit(0D);
        return dailySalesInvoices;
        
    }

	public MonthlySalesOrders findSalesOrdersMTD() {
		List<MonthlySalesOrders> monthlySalesOrdersList = this.monthlySalesOrdersRepository.findAll();
        if(monthlySalesOrdersList!=null && monthlySalesOrdersList.size()>0 && monthlySalesOrdersList.get(0)!=null && monthlySalesOrdersList.get(0).getTotal()!=null && !monthlySalesOrdersList.get(0).getTotal().isNaN())
        {
            return monthlySalesOrdersList.get(0);
        }
        
        MonthlySalesOrders monthlySalesOrders = new MonthlySalesOrders();
        monthlySalesOrders.setTotal(0D);
        monthlySalesOrders.setQuantity(0D);
        monthlySalesOrders.setProfit(0D);
        return monthlySalesOrders;
        
	}

	public DailySalesOrders findSalesOrdersDaily() {
		List<DailySalesOrders> dailySalesOrdersList = this.dailySalesOrdersRepository.findAll();
        if(dailySalesOrdersList!=null && dailySalesOrdersList.size()>0 && dailySalesOrdersList.get(0)!=null && dailySalesOrdersList.get(0).getTotal()!=null && !dailySalesOrdersList.get(0).getTotal().isNaN())
        {
            return dailySalesOrdersList.get(0);
        }
        
        DailySalesOrders dailySalesOrders = new DailySalesOrders();
        dailySalesOrders.setTotal(0D);
        dailySalesOrders.setQuantity(0D);
        dailySalesOrders.setProfit(0D);
        return dailySalesOrders;
        
	}
    
    public DailySalesQuotes findSalesQuotesDaily() {
		List<DailySalesQuotes> dailySalesQuotesList = this.dailySalesQuotesRepository.findAll();
        if(dailySalesQuotesList!=null && dailySalesQuotesList.size()>0 && dailySalesQuotesList.get(0)!=null && dailySalesQuotesList.get(0).getTotal()!=null && !dailySalesQuotesList.get(0).getTotal().isNaN())
        {
            return dailySalesQuotesList.get(0);
        }
        DailySalesQuotes dailySalesQuotes = new DailySalesQuotes();
        dailySalesQuotes.setTotal(0D);
        dailySalesQuotes.setQuantity(0D);
        dailySalesQuotes.setProfit(0D);
        return dailySalesQuotes;
    }
    
    public MonthlySalesQuotes findSalesQuotesMTD() {
		List<MonthlySalesQuotes> monthlySalesQuotesList = this.monthlySalesQuotesRepository.findAll();
        if(monthlySalesQuotesList!=null && monthlySalesQuotesList.size()>0 && monthlySalesQuotesList.get(0)!=null && monthlySalesQuotesList.get(0).getTotal()!=null && !monthlySalesQuotesList.get(0).getTotal().isNaN())
        {
            return monthlySalesQuotesList.get(0);
        }
        MonthlySalesQuotes monthlySalesQuotes = new MonthlySalesQuotes();
        monthlySalesQuotes.setTotal(0D);
        monthlySalesQuotes.setQuantity(0D);
        monthlySalesQuotes.setProfit(0D);
        return monthlySalesQuotes;
       
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
    public int findSalesInvoicedQtyMTD() {
		List<MonthlySalesInvoicedQty> monthlySalesInvoicedQtyList = this.monthlySalesInvoicedQtyRepository.findAll();
        if(monthlySalesInvoicedQtyList!=null && monthlySalesInvoicedQtyList.size()>0 && monthlySalesInvoicedQtyList.get(0)!=null && monthlySalesInvoicedQtyList.get(0).getTotal()!=null && !monthlySalesInvoicedQtyList.get(0).getTotal().isNaN())
        {
            return monthlySalesInvoicedQtyList.get(0).getTotal().intValue();
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
    public int findSalesQuotesQtyMTD() {
		List<MonthlySalesQuotesQty> monthlySalesQuotesQtyList = this.monthlySalesQuotesQtyRepository.findAll();
        if(monthlySalesQuotesQtyList!=null && monthlySalesQuotesQtyList.size()>0 && monthlySalesQuotesQtyList.get(0)!=null && monthlySalesQuotesQtyList.get(0).getTotal()!=null && !monthlySalesQuotesQtyList.get(0).getTotal().isNaN())
        {
            return monthlySalesQuotesQtyList.get(0).getTotal().intValue();
        }
        return 0;
	}
	public List<SalesTarget> findSalesTarget() {
		return this.salesTargetRepository.findAll();
    }
    
    public List<MonthlySalesTarget> findMonthlySalesTarget() {
		return this.monthlySalesTargetRepository.findAll();
	}

    public double findDailyTarget(Double monthlyTraget) {
		if(monthlyTraget!=null && !monthlyTraget.isNaN())
        {
            Calendar calendar = Calendar.getInstance();
            int noOfDaysInCurrentMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            LOG.info("noOfDaysInCurrentMonth="+noOfDaysInCurrentMonth+", monthlyTraget="+monthlyTraget.doubleValue());
            return monthlyTraget.doubleValue()/noOfDaysInCurrentMonth;
        }
        return 0D;
       
    }
    public double findSalesTargetMTD() {
		List<MonthlySalesTarget> monthlySalesTargetList = this.findMonthlySalesTarget();
        if(monthlySalesTargetList!=null && monthlySalesTargetList.size()>0 && monthlySalesTargetList.get(0)!=null && monthlySalesTargetList.get(0).getTarget()!=null && !monthlySalesTargetList.get(0).getTarget().isNaN())
        {
            return monthlySalesTargetList.get(0).getTarget().doubleValue();
        }
        return 0D;
       
    }
    public MonthlyTargets findMonthlyTargets(int monthNo) {
		return this.monthlyTargetsRepository.findByMonth(monthNo);
    }

}