package au.com.onesysconsulting.cscparts.dashboard.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesEnteredQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoicedQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotesQty;
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoicedQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrderQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotesQty;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesInvoicesByDate;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesInvoicesByPeriod;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesOrdersByDate;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesOrdersByPeriod;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesQuotesByDate;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesQuotesByPeriod;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesEnteredQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicedQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.DailySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastMonthSalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastMonthSalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastMonthSalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastThreeMonthsSalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastThreeMonthsSalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.LastThreeMonthsSalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicedQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesInvoicesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrderQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesOrdersRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesQuotesQtyRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesQuotesRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlySalesTargetRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.MonthlyTargetsRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesInvoicesByDateRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesInvoicesByPeriodRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesOrdersByDateRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesOrdersByPeriodRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesQuotesByDateRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesQuotesByPeriodRepository;
import au.com.onesysconsulting.cscparts.dashboard.repository.SalesTargetRepository;

@Service("dashboardService")
public class DashboardService {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardService.class);

    private MonthlySalesInvoicesRepository monthlySalesInvoicesRepository;
    private DailySalesInvoicesRepository dailySalesInvoicesRepository;
    private LastMonthSalesInvoicesRepository lastMonthSalesInvoicesRepository;
    private LastThreeMonthsSalesInvoicesRepository lastThreeMonthsSalesInvoicesRepository;
    private MonthlySalesOrdersRepository monthlySalesOrdersRepository;
    private DailySalesOrdersRepository dailySalesOrdersRepository;
    private LastMonthSalesOrdersRepository lastMonthSalesOrdersRepository;
    private LastThreeMonthsSalesOrdersRepository lastThreeMonthsSalesOrdersRepository;
    private DailySalesQuotesRepository dailySalesQuotesRepository;
    private MonthlySalesQuotesRepository monthlySalesQuotesRepository;
    private LastMonthSalesQuotesRepository lastMonthSalesQuotesRepository;
    private LastThreeMonthsSalesQuotesRepository lastThreeMonthsSalesQuotesRepository;
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
    private SalesQuotesByDateRepository salesQuotesByDateRepository;
    private SalesOrdersByDateRepository salesOrdersByDateRepository;
    private SalesInvoicesByDateRepository salesInvoicesByDateRepository;
    private SalesQuotesByPeriodRepository salesQuotesByPeriodRepository;
    private SalesOrdersByPeriodRepository salesOrdersByPeriodRepository;
    private SalesInvoicesByPeriodRepository salesInvoicesByPeriodRepository;

    @Autowired
    public DashboardService(MonthlySalesInvoicesRepository monthlySalesInvoicesRepository,
            DailySalesInvoicesRepository dailySalesInvoicesRepository,
            LastMonthSalesInvoicesRepository lastMonthSalesInvoicesRepository,
            LastThreeMonthsSalesInvoicesRepository lastThreeMonthsSalesInvoicesRepository,
            MonthlySalesOrdersRepository monthlySalesOrdersRepository,
            DailySalesOrdersRepository dailySalesOrdersRepository,
            LastMonthSalesOrdersRepository lastMonthSalesOrdersRepository,
            LastThreeMonthsSalesOrdersRepository lastThreeMonthsSalesOrdersRepository,
            DailySalesQuotesRepository dailySalesQuotesRepository,
            MonthlySalesQuotesRepository monthlySalesQuotesRepository,
            LastMonthSalesQuotesRepository lastMonthSalesQuotesRepository,
            LastThreeMonthsSalesQuotesRepository lastThreeMonthsSalesQuotesRepository,
            MonthlySalesOrderQtyRepository monthlySalesOrderQtyRepository,
            DailySalesOrderQtyRepository dailySalesOrderQtyRepository, SalesTargetRepository salesTargetRepository,
            MonthlySalesTargetRepository monthlySalesTargetRepository,
            DailySalesEnteredQtyRepository dailySalesEnteredQtyRepository,
            DailySalesQuotesQtyRepository dailySalesQuotesQtyRepository,
            DailySalesInvoicedQtyRepository dailySalesInvoicedQtyRepository,
            MonthlySalesInvoicedQtyRepository monthlySalesInvoicedQtyRepository,
            MonthlySalesQuotesQtyRepository monthlySalesQuotesQtyRepository,
            MonthlyTargetsRepository monthlyTargetsRepository, SalesQuotesByDateRepository salesQuotesByDateRepository,
            SalesOrdersByDateRepository salesOrdersByDateRepository,
            SalesInvoicesByDateRepository salesInvoicesByDateRepository, SalesQuotesByPeriodRepository salesQuotesByPeriodRepository,
            SalesOrdersByPeriodRepository salesOrdersByPeriodRepository,
            SalesInvoicesByPeriodRepository salesInvoicesByPeriodRepository) {
        this.monthlySalesInvoicesRepository = monthlySalesInvoicesRepository;
        this.dailySalesInvoicesRepository = dailySalesInvoicesRepository;
        this.lastMonthSalesInvoicesRepository = lastMonthSalesInvoicesRepository;
        this.lastThreeMonthsSalesInvoicesRepository = lastThreeMonthsSalesInvoicesRepository;
        this.monthlySalesOrdersRepository = monthlySalesOrdersRepository;
        this.dailySalesOrdersRepository = dailySalesOrdersRepository;
        this.lastMonthSalesOrdersRepository = lastMonthSalesOrdersRepository;
        this.lastThreeMonthsSalesOrdersRepository = lastThreeMonthsSalesOrdersRepository;
        this.dailySalesQuotesRepository = dailySalesQuotesRepository;
        this.monthlySalesQuotesRepository = monthlySalesQuotesRepository;
        this.lastMonthSalesQuotesRepository = lastMonthSalesQuotesRepository;
        this.lastThreeMonthsSalesQuotesRepository = lastThreeMonthsSalesQuotesRepository;
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
        this.salesQuotesByDateRepository = salesQuotesByDateRepository;
        this.salesOrdersByDateRepository = salesOrdersByDateRepository;
        this.salesInvoicesByDateRepository = salesInvoicesByDateRepository;
        this.salesQuotesByPeriodRepository = salesQuotesByPeriodRepository;
        this.salesOrdersByPeriodRepository = salesOrdersByPeriodRepository;
        this.salesInvoicesByPeriodRepository = salesInvoicesByPeriodRepository;
    }

    public MonthlySalesInvoices findSalesInvoicesMTD() {
        List<MonthlySalesInvoices> monthlySalesInvoicedList = this.monthlySalesInvoicesRepository.findAll();
        if (monthlySalesInvoicedList != null && monthlySalesInvoicedList.size() > 0
                && monthlySalesInvoicedList.get(0) != null && monthlySalesInvoicedList.get(0).getTotal() != null
                && !monthlySalesInvoicedList.get(0).getTotal().isNaN()) {
            return monthlySalesInvoicedList.get(0);
        }
        MonthlySalesInvoices monthlySalesInvoices = new MonthlySalesInvoices();
        monthlySalesInvoices.setTotal(0D);
        monthlySalesInvoices.setQuantity(0D);
        monthlySalesInvoices.setProfit(0D);
        return monthlySalesInvoices;

    }

    public DailySalesInvoices findSalesInvoicesDaily() {
        List<DailySalesInvoices> dailySalesInvoicedList = this.dailySalesInvoicesRepository.findAll();
        if (dailySalesInvoicedList != null && dailySalesInvoicedList.size() > 0 && dailySalesInvoicedList.get(0) != null
                && dailySalesInvoicedList.get(0).getTotal() != null
                && !dailySalesInvoicedList.get(0).getTotal().isNaN()) {
            return dailySalesInvoicedList.get(0);
        }
        DailySalesInvoices dailySalesInvoices = new DailySalesInvoices();
        dailySalesInvoices.setTotal(0D);
        dailySalesInvoices.setQuantity(0D);
        dailySalesInvoices.setProfit(0D);
        return dailySalesInvoices;

    }

    public LastMonthSalesInvoices findSalesInvoicesLastMonth() {
        List<LastMonthSalesInvoices> lastMonthSalesInvoicedList = this.lastMonthSalesInvoicesRepository.findAll();
        if (lastMonthSalesInvoicedList != null && lastMonthSalesInvoicedList.size() > 0
                && lastMonthSalesInvoicedList.get(0) != null && lastMonthSalesInvoicedList.get(0).getTotal() != null
                && !lastMonthSalesInvoicedList.get(0).getTotal().isNaN()) {
            return lastMonthSalesInvoicedList.get(0);
        }
        LastMonthSalesInvoices lastMonthSalesInvoices = new LastMonthSalesInvoices();
        lastMonthSalesInvoices.setTotal(0D);
        lastMonthSalesInvoices.setQuantity(0D);
        lastMonthSalesInvoices.setProfit(0D);
        return lastMonthSalesInvoices;

    }

    public LastThreeMonthsSalesInvoices findSalesInvoicesLastThreeMonths() {
        List<LastThreeMonthsSalesInvoices> lastThreeMonthsSalesInvoicedList = this.lastThreeMonthsSalesInvoicesRepository
                .findAll();
        if (lastThreeMonthsSalesInvoicedList != null && lastThreeMonthsSalesInvoicedList.size() > 0
                && lastThreeMonthsSalesInvoicedList.get(0) != null
                && lastThreeMonthsSalesInvoicedList.get(0).getTotal() != null
                && !lastThreeMonthsSalesInvoicedList.get(0).getTotal().isNaN()) {
            return lastThreeMonthsSalesInvoicedList.get(0);
        }
        LastThreeMonthsSalesInvoices last3MonthsSalesInvoices = new LastThreeMonthsSalesInvoices();
        last3MonthsSalesInvoices.setTotal(0D);
        last3MonthsSalesInvoices.setQuantity(0D);
        last3MonthsSalesInvoices.setProfit(0D);
        return last3MonthsSalesInvoices;

    }

    public MonthlySalesOrders findSalesOrdersMTD() {
        List<MonthlySalesOrders> monthlySalesOrdersList = this.monthlySalesOrdersRepository.findAll();
        if (monthlySalesOrdersList != null && monthlySalesOrdersList.size() > 0 && monthlySalesOrdersList.get(0) != null
                && monthlySalesOrdersList.get(0).getTotal() != null
                && !monthlySalesOrdersList.get(0).getTotal().isNaN()) {
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
        if (dailySalesOrdersList != null && dailySalesOrdersList.size() > 0 && dailySalesOrdersList.get(0) != null
                && dailySalesOrdersList.get(0).getTotal() != null && !dailySalesOrdersList.get(0).getTotal().isNaN()) {
            return dailySalesOrdersList.get(0);
        }

        DailySalesOrders dailySalesOrders = new DailySalesOrders();
        dailySalesOrders.setTotal(0D);
        dailySalesOrders.setQuantity(0D);
        dailySalesOrders.setProfit(0D);
        return dailySalesOrders;

    }

    public LastMonthSalesOrders findSalesOrdersLastMonth() {
        List<LastMonthSalesOrders> lastMonthSalesOrdersList = this.lastMonthSalesOrdersRepository.findAll();
        if (lastMonthSalesOrdersList != null && lastMonthSalesOrdersList.size() > 0
                && lastMonthSalesOrdersList.get(0) != null && lastMonthSalesOrdersList.get(0).getTotal() != null
                && !lastMonthSalesOrdersList.get(0).getTotal().isNaN()) {
            return lastMonthSalesOrdersList.get(0);
        }

        LastMonthSalesOrders lastMonthSalesOrders = new LastMonthSalesOrders();
        lastMonthSalesOrders.setTotal(0D);
        lastMonthSalesOrders.setQuantity(0D);
        lastMonthSalesOrders.setProfit(0D);
        return lastMonthSalesOrders;

    }

    public LastThreeMonthsSalesOrders findSalesOrdersLastThreeMonths() {
        List<LastThreeMonthsSalesOrders> last3MonthsSalesOrdersList = this.lastThreeMonthsSalesOrdersRepository
                .findAll();
        if (last3MonthsSalesOrdersList != null && last3MonthsSalesOrdersList.size() > 0
                && last3MonthsSalesOrdersList.get(0) != null && last3MonthsSalesOrdersList.get(0).getTotal() != null
                && !last3MonthsSalesOrdersList.get(0).getTotal().isNaN()) {
            return last3MonthsSalesOrdersList.get(0);
        }

        LastThreeMonthsSalesOrders last3MonthsSalesOrders = new LastThreeMonthsSalesOrders();
        last3MonthsSalesOrders.setTotal(0D);
        last3MonthsSalesOrders.setQuantity(0D);
        last3MonthsSalesOrders.setProfit(0D);
        return last3MonthsSalesOrders;

    }

    public DailySalesQuotes findSalesQuotesDaily() {
        List<DailySalesQuotes> dailySalesQuotesList = this.dailySalesQuotesRepository.findAll();
        if (dailySalesQuotesList != null && dailySalesQuotesList.size() > 0 && dailySalesQuotesList.get(0) != null
                && dailySalesQuotesList.get(0).getTotal() != null && !dailySalesQuotesList.get(0).getTotal().isNaN()) {
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
        if (monthlySalesQuotesList != null && monthlySalesQuotesList.size() > 0 && monthlySalesQuotesList.get(0) != null
                && monthlySalesQuotesList.get(0).getTotal() != null
                && !monthlySalesQuotesList.get(0).getTotal().isNaN()) {
            return monthlySalesQuotesList.get(0);
        }
        MonthlySalesQuotes monthlySalesQuotes = new MonthlySalesQuotes();
        monthlySalesQuotes.setTotal(0D);
        monthlySalesQuotes.setQuantity(0D);
        monthlySalesQuotes.setProfit(0D);
        return monthlySalesQuotes;

    }

    public LastMonthSalesQuotes findSalesQuotesLastMonth() {
        List<LastMonthSalesQuotes> lastMonthSalesQuotesList = this.lastMonthSalesQuotesRepository.findAll();
        if (lastMonthSalesQuotesList != null && lastMonthSalesQuotesList.size() > 0
                && lastMonthSalesQuotesList.get(0) != null && lastMonthSalesQuotesList.get(0).getTotal() != null
                && !lastMonthSalesQuotesList.get(0).getTotal().isNaN()) {
            return lastMonthSalesQuotesList.get(0);
        }
        LastMonthSalesQuotes lastMonthSalesQuote = new LastMonthSalesQuotes();
        lastMonthSalesQuote.setTotal(0D);
        lastMonthSalesQuote.setQuantity(0D);
        lastMonthSalesQuote.setProfit(0D);
        return lastMonthSalesQuote;

    }

    public LastThreeMonthsSalesQuotes findSalesQuotesLastThreeMonths() {
        List<LastThreeMonthsSalesQuotes> last3MonthsSalesQuotesList = this.lastThreeMonthsSalesQuotesRepository
                .findAll();
        if (last3MonthsSalesQuotesList != null && last3MonthsSalesQuotesList.size() > 0
                && last3MonthsSalesQuotesList.get(0) != null && last3MonthsSalesQuotesList.get(0).getTotal() != null
                && !last3MonthsSalesQuotesList.get(0).getTotal().isNaN()) {
            return last3MonthsSalesQuotesList.get(0);
        }
        LastThreeMonthsSalesQuotes last3MonthsSalesQuote = new LastThreeMonthsSalesQuotes();
        last3MonthsSalesQuote.setTotal(0D);
        last3MonthsSalesQuote.setQuantity(0D);
        last3MonthsSalesQuote.setProfit(0D);
        return last3MonthsSalesQuote;

    }

    public int findSalesOrdersQtyMTD() {
        List<MonthlySalesOrderQty> monthlySalesOrderQtyList = this.monthlySalesOrderQtyRepository.findAll();
        if (monthlySalesOrderQtyList != null && monthlySalesOrderQtyList.size() > 0
                && monthlySalesOrderQtyList.get(0) != null && monthlySalesOrderQtyList.get(0).getTotal() != null
                && !monthlySalesOrderQtyList.get(0).getTotal().isNaN()) {
            return monthlySalesOrderQtyList.get(0).getTotal().intValue();
        }

        return 0;

    }

    public int findSalesOrdersQtyDaily() {
        List<DailySalesOrderQty> dailySalesOrderQtyList = this.dailySalesOrderQtyRepository.findAll();
        if (dailySalesOrderQtyList != null && dailySalesOrderQtyList.size() > 0 && dailySalesOrderQtyList.get(0) != null
                && dailySalesOrderQtyList.get(0).getTotal() != null
                && !dailySalesOrderQtyList.get(0).getTotal().isNaN()) {
            return dailySalesOrderQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }

    public int findSalesEnteredQtyDaily() {
        List<DailySalesEnteredQty> dailySalesEnteredQtyList = this.dailySalesEnteredQtyRepository.findAll();
        if (dailySalesEnteredQtyList != null && dailySalesEnteredQtyList.size() > 0
                && dailySalesEnteredQtyList.get(0) != null && dailySalesEnteredQtyList.get(0).getTotal() != null
                && !dailySalesEnteredQtyList.get(0).getTotal().isNaN()) {
            return dailySalesEnteredQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }

    public int findSalesInvoicedQtyMTD() {
        List<MonthlySalesInvoicedQty> monthlySalesInvoicedQtyList = this.monthlySalesInvoicedQtyRepository.findAll();
        if (monthlySalesInvoicedQtyList != null && monthlySalesInvoicedQtyList.size() > 0
                && monthlySalesInvoicedQtyList.get(0) != null && monthlySalesInvoicedQtyList.get(0).getTotal() != null
                && !monthlySalesInvoicedQtyList.get(0).getTotal().isNaN()) {
            return monthlySalesInvoicedQtyList.get(0).getTotal().intValue();
        }

        return 0;

    }

    public int findSalesInvoicedQtyDaily() {
        List<DailySalesInvoicedQty> dailySalesInvoicedQtyList = this.dailySalesInvoicedQtyRepository.findAll();
        if (dailySalesInvoicedQtyList != null && dailySalesInvoicedQtyList.size() > 0
                && dailySalesInvoicedQtyList.get(0) != null && dailySalesInvoicedQtyList.get(0).getTotal() != null
                && !dailySalesInvoicedQtyList.get(0).getTotal().isNaN()) {
            return dailySalesInvoicedQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }

    public int findSalesQuotesQtyDaily() {
        List<DailySalesQuotesQty> dailySalesQuotesQtyList = this.dailySalesQuotesQtyRepository.findAll();
        if (dailySalesQuotesQtyList != null && dailySalesQuotesQtyList.size() > 0
                && dailySalesQuotesQtyList.get(0) != null && dailySalesQuotesQtyList.get(0).getTotal() != null
                && !dailySalesQuotesQtyList.get(0).getTotal().isNaN()) {
            return dailySalesQuotesQtyList.get(0).getTotal().intValue();
        }
        return 0;
    }

    public int findSalesQuotesQtyMTD() {
        List<MonthlySalesQuotesQty> monthlySalesQuotesQtyList = this.monthlySalesQuotesQtyRepository.findAll();
        if (monthlySalesQuotesQtyList != null && monthlySalesQuotesQtyList.size() > 0
                && monthlySalesQuotesQtyList.get(0) != null && monthlySalesQuotesQtyList.get(0).getTotal() != null
                && !monthlySalesQuotesQtyList.get(0).getTotal().isNaN()) {
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

    public double findDailyTarget(Double monthlyTraget, int noOfDaysInCurrentMonth) {
        if (monthlyTraget != null && !monthlyTraget.isNaN() && noOfDaysInCurrentMonth > 0) {
            LOG.info("noOfDaysInCurrentMonth=" + noOfDaysInCurrentMonth + ", monthlyTraget="
                    + monthlyTraget.doubleValue());
            return monthlyTraget.doubleValue() / noOfDaysInCurrentMonth;
        }
        return 0D;

    }

    public double findSalesTargetMTD() {
        List<MonthlySalesTarget> monthlySalesTargetList = this.findMonthlySalesTarget();
        if (monthlySalesTargetList != null && monthlySalesTargetList.size() > 0 && monthlySalesTargetList.get(0) != null
                && monthlySalesTargetList.get(0).getTarget() != null
                && !monthlySalesTargetList.get(0).getTarget().isNaN()) {
            return monthlySalesTargetList.get(0).getTarget().doubleValue();
        }
        return 0D;

    }

    public MonthlyTargets findMonthlyTargets(int year, int monthNo) {
        int financialMonthNo = monthNo < 6 ? (monthNo + 7) : (monthNo - 5);
        LOG.info("findMonthlyTargets=>Year ,financialMonthNo=" + year + " , " + financialMonthNo);
        List<MonthlyTargets> monthlyTargets = this.monthlyTargetsRepository.findByYearAndMonth(year, financialMonthNo);
        if (monthlyTargets != null && !monthlyTargets.isEmpty()) {
            return monthlyTargets.get(0);
        }
        return null;
    }

    public MonthlyTargets findLastThreeMonthsTargets() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        List<MonthlyTargets> threeMonthTargets = new ArrayList<MonthlyTargets>();
        MonthlyTargets lastMonthTargets = findMonthlyTargets(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        if (lastMonthTargets != null)
            threeMonthTargets.add(lastMonthTargets);

        calendar.add(Calendar.MONTH, -1);
        MonthlyTargets lastSecondMonthTargets = findMonthlyTargets(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH));
        if (lastSecondMonthTargets != null)
            threeMonthTargets.add(lastSecondMonthTargets);

        calendar.add(Calendar.MONTH, -1);
        MonthlyTargets lastThirdMonthTargets = findMonthlyTargets(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH));
        if (lastThirdMonthTargets != null)
            threeMonthTargets.add(lastThirdMonthTargets);

        MonthlyTargets lastThreeMonthsTarget = new MonthlyTargets();
        lastThreeMonthsTarget.setQuoteQty(0D);
        lastThreeMonthsTarget.setQuoteValue(0D);
        lastThreeMonthsTarget.setQuoteProfit(0D);
        lastThreeMonthsTarget.setInvoiceQty(0D);
        lastThreeMonthsTarget.setInvoiceValue(0D);
        lastThreeMonthsTarget.setInvoiceProfit(0D);
        lastThreeMonthsTarget.setOrderQty(0D);
        lastThreeMonthsTarget.setOrderValue(0D);
        lastThreeMonthsTarget.setOrderProfit(0D);

        for (MonthlyTargets monthTargets : threeMonthTargets) {
            lastThreeMonthsTarget.setQuoteQty(lastThreeMonthsTarget.getQuoteQty()
                    + (monthTargets.getQuoteQty() != null ? monthTargets.getQuoteQty() : 0D));
            lastThreeMonthsTarget.setQuoteValue(lastThreeMonthsTarget.getQuoteValue()
                    + (monthTargets.getQuoteValue() != null ? monthTargets.getQuoteValue() : 0D));
            lastThreeMonthsTarget.setQuoteProfit(lastThreeMonthsTarget.getQuoteProfit()
                    + (monthTargets.getQuoteProfit() != null
                            ? (monthTargets.getQuoteValue().doubleValue() * monthTargets.getQuoteProfit().doubleValue())
                                    / 100
                            : 0D));

            lastThreeMonthsTarget.setInvoiceQty(lastThreeMonthsTarget.getInvoiceQty()
                    + (monthTargets.getInvoiceQty() != null ? monthTargets.getInvoiceQty() : 0D));
            lastThreeMonthsTarget.setInvoiceValue(lastThreeMonthsTarget.getInvoiceValue()
                    + (monthTargets.getInvoiceValue() != null ? monthTargets.getInvoiceValue() : 0D));
            lastThreeMonthsTarget.setInvoiceProfit(lastThreeMonthsTarget.getInvoiceProfit()
                    + (monthTargets.getInvoiceProfit() != null
                            ? (monthTargets.getInvoiceValue().doubleValue()
                                    * monthTargets.getInvoiceProfit().doubleValue()) / 100
                            : 0D));

            lastThreeMonthsTarget.setOrderQty(lastThreeMonthsTarget.getOrderQty()
                    + (monthTargets.getOrderQty() != null ? monthTargets.getOrderQty() : 0D));
            lastThreeMonthsTarget.setOrderValue(lastThreeMonthsTarget.getOrderValue()
                    + (monthTargets.getOrderValue() != null ? monthTargets.getOrderValue() : 0D));
            lastThreeMonthsTarget.setOrderProfit(lastThreeMonthsTarget.getOrderProfit()
                    + (monthTargets.getOrderProfit() != null
                            ? (monthTargets.getOrderValue().doubleValue() * monthTargets.getOrderProfit().doubleValue())
                                    / 100
                            : 0D));
        }
        return lastThreeMonthsTarget;
    }

    public List<SalesOrdersByDate> findWeekToDateSalesOrders() {
        long now = System.currentTimeMillis();
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        long sevenDaysBefore = System.currentTimeMillis() - (7 * DAY_IN_MS);

        return salesOrdersByDateRepository.findByBetweenDates(new Timestamp(sevenDaysBefore), new Timestamp(now));
    }

    public List<SalesQuotesByDate> findWeekToDateSalesQuotes() {
        long now = System.currentTimeMillis();
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        long sevenDaysBefore = System.currentTimeMillis() - (7 * DAY_IN_MS);

        return salesQuotesByDateRepository.findByBetweenDates(new Timestamp(sevenDaysBefore), new Timestamp(now));
    }

    public List<SalesInvoicesByDate> findWeekToDateSalesInvoices() {
        long now = System.currentTimeMillis();
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        long sevenDaysBefore = System.currentTimeMillis() - (7 * DAY_IN_MS);

        return salesInvoicesByDateRepository.findByBetweenDates(new Timestamp(sevenDaysBefore), new Timestamp(now));
    }

    public String[][] getSalesOrderTargetArray(List<SalesOrdersByDate> salesOrdersList) {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String[][] salesOrdersTargets = new String[salesOrdersList.size() + 1][3];
        int i = 0;
        salesOrdersTargets[i][0] = "Date";
        salesOrdersTargets[i][1] = "Sale";
        salesOrdersTargets[i][2] = "Target";
        for (SalesOrdersByDate salesOrdersByDate : salesOrdersList) {
            i++;
            salesOrdersTargets[i][0] = salesOrdersByDate.getDate().toLocaleString().split(" ")[0];
            salesOrdersTargets[i][1] = formatter.format(salesOrdersByDate.getTotal().doubleValue());
            salesOrdersTargets[i][2] = formatter
                    .format(salesOrdersByDate.getTotalTarget().doubleValue() / salesOrdersByDate.getQuantity());
        }
        return salesOrdersTargets;
    }

    public String[][] getSalesQuoteTargetArray(List<SalesQuotesByDate> salesQuotesList) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String[][] salesQuotesTargets = new String[salesQuotesList.size() + 1][3];
        int i = 0;
        salesQuotesTargets[i][i] = "Date";
        salesQuotesTargets[i][1] = "Quote";
        salesQuotesTargets[i][2] = "Target";
        for (SalesQuotesByDate salesQuotesByDate : salesQuotesList) {
            i++;
            salesQuotesTargets[i][0] = salesQuotesByDate.getDate().toLocaleString().split(" ")[0];
            salesQuotesTargets[i][1] = formatter.format(salesQuotesByDate.getTotal().doubleValue());
            salesQuotesTargets[i][2] = formatter
                    .format(salesQuotesByDate.getTotalTarget().doubleValue() / salesQuotesByDate.getQuantity());
        }
        return salesQuotesTargets;
    }

    public String[][] getSalesInvoiceTargetArray(List<SalesInvoicesByDate> salesInvoicesList) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String[][] salesInvoicesTargets = new String[salesInvoicesList.size() + 1][3];
        int i = 0;
        salesInvoicesTargets[i][0] = "Date";
        salesInvoicesTargets[i][1] = "Invoice";
        salesInvoicesTargets[i][2] = "Target";
        for (SalesInvoicesByDate salesInvoicesByDate : salesInvoicesList) {
            i++;
            salesInvoicesTargets[i][0] = salesInvoicesByDate.getDate().toLocaleString().split(" ")[0];
            salesInvoicesTargets[i][1] = formatter.format(salesInvoicesByDate.getTotal().doubleValue());
            salesInvoicesTargets[i][2] = formatter
                    .format(salesInvoicesByDate.getTotalTarget().doubleValue() / salesInvoicesByDate.getQuantity());
        }
        return salesInvoicesTargets;
    }

    public List<SalesOrdersByDate> getSalesOrderTargetList(List<SalesOrdersByDate> salesOrdersList) {
        List<SalesOrdersByDate> salesOrderTargetList = new ArrayList<SalesOrdersByDate>();
        double dailyTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesOrdersByDate salesOrdersByDate : salesOrdersList) {
            dailyTarget = salesOrdersByDate.getTotalTarget() != null? (salesOrdersByDate.getTotalTarget().doubleValue() / salesOrdersByDate.getQuantity().doubleValue()): 0D;
            noOfWorkingDays = salesOrdersByDate.getNoOfWorkingDays()/salesOrdersByDate.getQuantity().doubleValue();
            salesOrdersByDate.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += dailyTarget/noOfWorkingDays;
            salesOrdersByDate.setTotalTarget(dailyTarget/noOfWorkingDays);
            salesOrdersByDate.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesOrdersByDate.getTotal() != null ? salesOrdersByDate.getTotal().doubleValue() : 0;
            salesOrdersByDate.setCumulativeTotal(cumulativeTotal);
            salesOrderTargetList.add(salesOrdersByDate);
        }
        for (SalesOrdersByDate salesOrdersByDate : salesOrderTargetList) {
            salesOrdersByDate.setAverageTotal(cumulativeTotal / salesOrdersList.size());
            salesOrdersByDate.setAverageTarget(cumulativeTarget / salesOrdersList.size());
        }
        return salesOrderTargetList;
    }

    public List<SalesQuotesByDate> getSalesQuoteTargetList(List<SalesQuotesByDate> salesQuotesList) {
        List<SalesQuotesByDate> salesQuoteTargetList = new ArrayList<SalesQuotesByDate>();
        double dailyTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesQuotesByDate salesQuotesByDate : salesQuotesList) {
            dailyTarget = salesQuotesByDate.getTotalTarget() != null? (salesQuotesByDate.getTotalTarget().doubleValue() / salesQuotesByDate.getQuantity().doubleValue()): 0D;
            noOfWorkingDays = salesQuotesByDate.getNoOfWorkingDays()/salesQuotesByDate.getQuantity().doubleValue();
            salesQuotesByDate.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += dailyTarget/noOfWorkingDays;
            salesQuotesByDate.setTotalTarget(dailyTarget/noOfWorkingDays);
            salesQuotesByDate.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesQuotesByDate.getTotal() != null ? salesQuotesByDate.getTotal().doubleValue() : 0;
            salesQuotesByDate.setCumulativeTotal(cumulativeTotal);
            salesQuoteTargetList.add(salesQuotesByDate);
        }
        for (SalesQuotesByDate salesQuotesByDate : salesQuoteTargetList) {
            salesQuotesByDate.setAverageTotal(cumulativeTotal / salesQuotesList.size());
            salesQuotesByDate.setAverageTarget(cumulativeTarget / salesQuotesList.size());
        }
        return salesQuoteTargetList;
    }

    public List<SalesInvoicesByDate> getSalesInvoiceTargetList(List<SalesInvoicesByDate> salesInvoicesList) {
        List<SalesInvoicesByDate> salesInvoiceTargetList = new ArrayList<SalesInvoicesByDate>();
        double dailyTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesInvoicesByDate salesInvoicesByDate : salesInvoicesList) {
            dailyTarget = salesInvoicesByDate.getTotalTarget() != null? (salesInvoicesByDate.getTotalTarget().doubleValue()/salesInvoicesByDate.getQuantity().doubleValue()) : 0D;
            noOfWorkingDays = salesInvoicesByDate.getNoOfWorkingDays()/salesInvoicesByDate.getQuantity().doubleValue();
            salesInvoicesByDate.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += dailyTarget/noOfWorkingDays;
            salesInvoicesByDate.setTotalTarget(dailyTarget/noOfWorkingDays);
            salesInvoicesByDate.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesInvoicesByDate.getTotal() != null ? salesInvoicesByDate.getTotal().doubleValue(): 0;
            salesInvoicesByDate.setCumulativeTotal(cumulativeTotal);
            salesInvoiceTargetList.add(salesInvoicesByDate);
        }
        LOG.info("cumulativeTarget-->"+cumulativeTarget);
        for (SalesInvoicesByDate salesInvoicesByDate : salesInvoiceTargetList) {
            salesInvoicesByDate.setAverageTotal(cumulativeTotal / salesInvoicesList.size());
            salesInvoicesByDate.setAverageTarget(cumulativeTarget / salesInvoicesList.size());
        }
        return salesInvoiceTargetList;
    }

    public List<SalesOrdersByDate> findMonthToDateSalesOrders() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        long now = System.currentTimeMillis();
        long firstDayOfThisMonth = calendar.getTime().getTime();
        return salesOrdersByDateRepository.findByBetweenDates(new Timestamp(firstDayOfThisMonth), new Timestamp(now));
    }

    public List<SalesQuotesByDate> findMonthToDateSalesQuotes() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        long now = System.currentTimeMillis();
        long firstDayOfThisMonth = calendar.getTime().getTime();
        return salesQuotesByDateRepository.findByBetweenDates(new Timestamp(firstDayOfThisMonth), new Timestamp(now));
    }

    public List<SalesInvoicesByDate> findMonthToDateSalesInvoices() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        long now = System.currentTimeMillis();
        long firstDayOfThisMonth = calendar.getTime().getTime();
        return salesInvoicesByDateRepository.findByBetweenDates(new Timestamp(firstDayOfThisMonth), new Timestamp(now));
    }

    public List<SalesOrdersByDate> findSalesOrdersByDateRange(String fromDateStr, String toDateStr)  
    {
        LOG.info("findSalesOrdersByDateRange-->fromDate="+fromDateStr+" , toDate="+toDateStr);
        //date format dd/mm/yyyy
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       // you can change format of date
        Date fromDate=new Date();
        Date toDate=new Date();
       try {
           fromDate = formatter.parse(fromDateStr);
           toDate = formatter.parse(toDateStr);
       } catch (ParseException e) {
           e.printStackTrace();
       }
        return salesOrdersByDateRepository.findByBetweenDates(new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()));
    }
    public List<SalesQuotesByDate> findSalesQuotesByDateRange(String fromDateStr, String toDateStr)  
    {
        LOG.info("findSalesQuotesByDateRange-->fromDate="+fromDateStr+" , toDate="+toDateStr);
        //date format dd/mm/yyyy
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       // you can change format of date
       Date fromDate=new Date();
        Date toDate=new Date();
       try {
           fromDate = formatter.parse(fromDateStr);
           toDate = formatter.parse(toDateStr);
       } catch (ParseException e) {
           e.printStackTrace();
       }
        return salesQuotesByDateRepository.findByBetweenDates(new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()));
    }
    public List<SalesInvoicesByDate> findSalesInvoicesByDateRange(String fromDateStr, String toDateStr)  
    {
        LOG.info("findSalesInvoicesByDateRange-->fromDate="+fromDateStr+" , toDate="+toDateStr);
        //date format dd/mm/yyyy
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       // you can change format of date
       Date fromDate=new Date();
       Date toDate=new Date();
      try {
          fromDate = formatter.parse(fromDateStr);
          toDate = formatter.parse(toDateStr);
      } catch (ParseException e) {
          e.printStackTrace();
      }
        return salesInvoicesByDateRepository.findByBetweenDates(new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()));
	}

	public SalesOrdersByDate getSalesOrdersBySalesByDateList(List<SalesOrdersByDate> salesOrdersList) {
        SalesOrdersByDate salesOrdersByDate = new SalesOrdersByDate();
        double total = 0D;
        double qty = 0D;
        double profit = 0D;
        double target = 0D;
        double qtyTarget =0D;
        double profitTarget =0D;
        for (SalesOrdersByDate salesOrder : salesOrdersList) 
        {
            total+=salesOrder.getTotal();
            qty +=salesOrder.getQuantity();
            profit+=salesOrder.getProfit();
            target+=salesOrder.getTotalTarget();
            qtyTarget+=salesOrder.getQuantityTarget();
            profitTarget+=salesOrder.getProfitTarget();
        }
        LOG.info("getSalesOrdersBySalesByDateList-->total="+total+" , Quantity="+qty+" , Profit="+profit+", Target=" +target+" , qtyTarget="+qtyTarget+" , profitTarget="+profitTarget);
        salesOrdersByDate.setTotal(total);
        salesOrdersByDate.setQuantity(qty);
        salesOrdersByDate.setProfit(profit);
        salesOrdersByDate.setTotalTarget(target/qty);
        salesOrdersByDate.setQuantityTarget(qtyTarget/qty);
        salesOrdersByDate.setProfitTarget(profitTarget/qty);

        return salesOrdersByDate;
    }
    
    public SalesInvoicesByDate getSalesInvoicesBySalesByDateList(List<SalesInvoicesByDate> salesInvoicesList) {
        SalesInvoicesByDate salesInvoicesByDate = new SalesInvoicesByDate();
        double total = 0D;
        double qty = 0D;
        double profit = 0D;
        double target = 0D;
        double qtyTarget =0D;
        double profitTarget =0D;
       for (SalesInvoicesByDate salesInvoice : salesInvoicesList) 
        {
            total+=salesInvoice.getTotal();
            qty +=salesInvoice.getQuantity();
            profit+=salesInvoice.getProfit();
            target+=salesInvoice.getTotalTarget();
            qtyTarget+=salesInvoice.getQuantityTarget();
            profitTarget+=salesInvoice.getProfitTarget();
        }
        LOG.info("getSalesInvoicesBySalesByDateList-->total="+total+" , Quantity="+qty+" , Profit="+profit+", Target=" +target+" , qtyTarget="+qtyTarget+" , profitTarget="+profitTarget);
        salesInvoicesByDate.setTotal(total);
        salesInvoicesByDate.setQuantity(qty);
        salesInvoicesByDate.setProfit(profit);
        salesInvoicesByDate.setTotalTarget(target/qty);
        salesInvoicesByDate.setQuantityTarget(qtyTarget/qty);
        salesInvoicesByDate.setProfitTarget(profitTarget/qty);

        return salesInvoicesByDate;
    }
    
    public SalesQuotesByDate getSalesQuotesBySalesByDateList(List<SalesQuotesByDate> salesQuotesList) {
        SalesQuotesByDate salesQuotesByDate = new SalesQuotesByDate();
        double total = 0D;
        double qty = 0D;
        double profit = 0D;
        double target = 0D;
        double qtyTarget =0D;
        double profitTarget =0D;
       for (SalesQuotesByDate salesQuote : salesQuotesList) 
        {
            total+=salesQuote.getTotal();
            qty +=salesQuote.getQuantity();
            profit+=salesQuote.getProfit();
            target+=salesQuote.getTotalTarget();
            qtyTarget+=salesQuote.getQuantityTarget();
            profitTarget+=salesQuote.getProfitTarget();
        }
        LOG.info("getSalesQuotesBySalesByDateList-->total="+total+" , Quantity="+qty+" , Profit="+profit+", Target=" +target+" , qtyTarget="+qtyTarget+" , profitTarget="+profitTarget);
        salesQuotesByDate.setTotal(total);
        salesQuotesByDate.setQuantity(qty);
        salesQuotesByDate.setProfit(profit);
        salesQuotesByDate.setTotalTarget(target/qty);
        salesQuotesByDate.setQuantityTarget(qtyTarget/qty);
        salesQuotesByDate.setProfitTarget(profitTarget/qty);

        return salesQuotesByDate;
	}

	public List<SalesOrdersByDate> getSalesOrderTargetListForEntireMonth(List<SalesOrdersByDate> salesOrderTargets) {
		double salesOrderedCumulativeTargetMonthly = salesOrderTargets!=null && salesOrderTargets.size()>0?(salesOrderTargets.get(0).getAverageTarget()*salesOrderTargets.size()):0D;
        double salesOrderedCumulativeTotal = salesOrderTargets!=null && salesOrderTargets.size()>0?(salesOrderTargets.get(salesOrderTargets.size()-1).getCumulativeTotal()):0D;
        if(salesOrderTargets!=null && !salesOrderTargets.isEmpty())
        {
                double getNoOfWorkingDays = salesOrderTargets.get(0).getNoOfWorkingDays();
                int noOfWorkedDays = salesOrderTargets.size();
                double targetAvg = salesOrderTargets.get(0).getTotalTarget();
                if((int)getNoOfWorkingDays>noOfWorkedDays)
                {
                        SalesOrdersByDate salesOrdersByDate=null;
                        Date toDay = new Date();
                        int count=0;
                        for(int i=noOfWorkedDays;i<(int)getNoOfWorkingDays;i++)
                        {
                                count++;
                                salesOrdersByDate=new SalesOrdersByDate();
                                salesOrdersByDate.setDate(new Timestamp(toDay.getTime()));
                                salesOrdersByDate.setTotal(0D);
                                salesOrdersByDate.setTotalTarget(targetAvg);
                                salesOrdersByDate.setCumulativeTarget(salesOrderedCumulativeTargetMonthly+(targetAvg*count));
                                salesOrdersByDate.setCumulativeTotal(salesOrderedCumulativeTotal);
                                salesOrderTargets.add(salesOrdersByDate);
                        }
                }
        }
        return salesOrderTargets;
    }
    public List<SalesQuotesByDate> getSalesQuoteTargetListForEntireMonth(List<SalesQuotesByDate> salesQuoteTargets) {
		double salesQuotesCumulativeTargetMonthly = salesQuoteTargets!=null && salesQuoteTargets.size()>0?(salesQuoteTargets.get(0).getAverageTarget()*salesQuoteTargets.size()):0D;
        double salesQuoteCumulativeTotal = salesQuoteTargets!=null && salesQuoteTargets.size()>0?(salesQuoteTargets.get(salesQuoteTargets.size()-1).getCumulativeTotal()):0D;
        if(salesQuoteTargets!=null && !salesQuoteTargets.isEmpty())
        {
                double getNoOfWorkingDays = salesQuoteTargets.get(0).getNoOfWorkingDays();
                int noOfWorkedDays = salesQuoteTargets.size();
                double targetAvg = salesQuoteTargets.get(0).getTotalTarget();
                if((int)getNoOfWorkingDays>noOfWorkedDays)
                {
                    SalesQuotesByDate salesQuotesByDate=null;
                        Date toDay = new Date();
                        int count=0;
                        for(int i=noOfWorkedDays;i<(int)getNoOfWorkingDays;i++)
                        {
                            count++;
                            salesQuotesByDate=new SalesQuotesByDate();
                            salesQuotesByDate.setDate(new Timestamp(toDay.getTime()));
                            salesQuotesByDate.setTotal(0D);
                            salesQuotesByDate.setTotalTarget(targetAvg);
                            salesQuotesByDate.setCumulativeTarget(salesQuotesCumulativeTargetMonthly+(targetAvg*count));
                            salesQuotesByDate.setCumulativeTotal(salesQuoteCumulativeTotal);
                            salesQuoteTargets.add(salesQuotesByDate);
                        }
                }
        }
        return salesQuoteTargets;
    }
    public List<SalesInvoicesByDate> getSalesInvoiceTargetListForEntireMonth(List<SalesInvoicesByDate> salesInvoiceTargets) {
		double salesInvoicesCumulativeTargetMonthly = salesInvoiceTargets!=null && salesInvoiceTargets.size()>0?(salesInvoiceTargets.get(0).getAverageTarget()*salesInvoiceTargets.size()):0D;
        double salesInvoiceCumulativeTotal = salesInvoiceTargets!=null && salesInvoiceTargets.size()>0?(salesInvoiceTargets.get(salesInvoiceTargets.size()-1).getCumulativeTotal()):0D;
        if(salesInvoiceTargets!=null && !salesInvoiceTargets.isEmpty())
        {
                double getNoOfWorkingDays = salesInvoiceTargets.get(0).getNoOfWorkingDays();
                int noOfWorkedDays = salesInvoiceTargets.size();
                double targetAvg = salesInvoiceTargets.get(0).getTotalTarget();
                if((int)getNoOfWorkingDays>noOfWorkedDays)
                {
                    SalesInvoicesByDate salesInvoicesByDate=null;
                        Date toDay = new Date();
                        int count=0;
                        for(int i=noOfWorkedDays;i<(int)getNoOfWorkingDays;i++)
                        {
                                count++;
                                salesInvoicesByDate=new SalesInvoicesByDate();
                                salesInvoicesByDate.setDate(new Timestamp(toDay.getTime()));
                                salesInvoicesByDate.setTotal(0D);
                                salesInvoicesByDate.setTotalTarget(targetAvg);
                                salesInvoicesByDate.setCumulativeTarget(salesInvoicesCumulativeTargetMonthly+(targetAvg*count));
                                salesInvoicesByDate.setCumulativeTotal(salesInvoiceCumulativeTotal);
                                salesInvoiceTargets.add(salesInvoicesByDate);
                        }
                }
        }
        return salesInvoiceTargets;
	}

	public List<SalesOrdersByPeriod> findFinancialYearSalesOrders() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        int financeYear = month>6?year+1:year-1;
		return salesOrdersByPeriodRepository.findByBetweenPeriods(year>financeYear?financeYear:year, year>financeYear?year:financeYear);
    }
    public List<SalesInvoicesByPeriod> findFinancialYearSalesInvoices() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        int financeYear = month>6?year+1:year-1;
		return salesInvoicesByPeriodRepository.findByBetweenPeriods(year>financeYear?financeYear:year, year>financeYear?year:financeYear);
    }
    public List<SalesQuotesByPeriod> findFinancialYearSalesQuotes() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        int financeYear = month>6?year+1:year-1;
		return salesQuotesByPeriodRepository.findByBetweenPeriods(year>financeYear?financeYear:year, year>financeYear?year:financeYear);
    }
    public List<SalesOrdersByPeriod> findLast12MonthsSalesOrders() {
        return salesOrdersByPeriodRepository.findByAgeLessThanOrderByAgeDesc(12);
    }
    public List<SalesInvoicesByPeriod> findLast12MonthsSalesInvoices() {
        return salesInvoicesByPeriodRepository.findByAgeLessThanOrderByAgeDesc(12);
    }
    public List<SalesQuotesByPeriod> findLast12MonthsSalesQuotes() {
        return salesQuotesByPeriodRepository.findByAgeLessThanOrderByAgeDesc(12);
    }
	public List<SalesOrdersByPeriod> getFinancialYearSalesOrderTargetList(List<SalesOrdersByPeriod> salesOrdersList) {
		List<SalesOrdersByPeriod> salesOrderTargetList = new ArrayList<SalesOrdersByPeriod>();
        double periodTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesOrdersByPeriod salesOrdersByPeriod : salesOrdersList) {
            periodTarget = (salesOrdersByPeriod.getQuantity() != null && salesOrdersByPeriod.getQuantity()>0)? (salesOrdersByPeriod.getTotalTarget().doubleValue() / salesOrdersByPeriod.getQuantity().doubleValue()): salesOrdersByPeriod.getTotalTarget().doubleValue();
            noOfWorkingDays = (salesOrdersByPeriod.getQuantity() != null && salesOrdersByPeriod.getQuantity()>0)? salesOrdersByPeriod.getNoOfWorkingDays()/salesOrdersByPeriod.getQuantity().doubleValue():salesOrdersByPeriod.getNoOfWorkingDays();
            salesOrdersByPeriod.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += periodTarget;
            salesOrdersByPeriod.setTotalTarget(periodTarget);
            salesOrdersByPeriod.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesOrdersByPeriod.getTotal() != null ? salesOrdersByPeriod.getTotal().doubleValue() : 0;
            salesOrdersByPeriod.setCumulativeTotal(cumulativeTotal);
            salesOrderTargetList.add(salesOrdersByPeriod);
        }
        for (SalesOrdersByPeriod salesOrdersByPeriod : salesOrderTargetList) {
            LOG.info("salesOrdersByPeriod-->"+salesOrdersByPeriod.getYear()+"/"+salesOrdersByPeriod.getPeriodNo());
            salesOrdersByPeriod.setAverageTotal(cumulativeTotal / salesOrdersList.size());
            salesOrdersByPeriod.setAverageTarget(cumulativeTarget / salesOrdersList.size());
        }
        return salesOrderTargetList;
	}

	public List<SalesQuotesByPeriod> getFinancialYearSalesQuoteTargetList(List<SalesQuotesByPeriod> salesQuotesList) {
		List<SalesQuotesByPeriod> salesQuoteTargetList = new ArrayList<SalesQuotesByPeriod>();
        double periodTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesQuotesByPeriod salesQuotesByPeriod : salesQuotesList) {
            periodTarget = (salesQuotesByPeriod.getQuantity() != null && salesQuotesByPeriod.getQuantity() >0)? (salesQuotesByPeriod.getTotalTarget().doubleValue() / salesQuotesByPeriod.getQuantity().doubleValue()): salesQuotesByPeriod.getTotalTarget().doubleValue();
            noOfWorkingDays = (salesQuotesByPeriod.getQuantity() != null && salesQuotesByPeriod.getQuantity()>0)? salesQuotesByPeriod.getNoOfWorkingDays()/salesQuotesByPeriod.getQuantity().doubleValue():salesQuotesByPeriod.getNoOfWorkingDays();
            salesQuotesByPeriod.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += periodTarget;
            salesQuotesByPeriod.setTotalTarget(periodTarget);
            salesQuotesByPeriod.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesQuotesByPeriod.getTotal() != null ? salesQuotesByPeriod.getTotal().doubleValue() : 0;
            salesQuotesByPeriod.setCumulativeTotal(cumulativeTotal);
            salesQuoteTargetList.add(salesQuotesByPeriod);
        }
        for (SalesQuotesByPeriod salesQuotesByPeriod : salesQuoteTargetList) {
            LOG.info("salesQuotesByPeriod-->"+salesQuotesByPeriod.getYear()+"/"+salesQuotesByPeriod.getPeriodNo());
            salesQuotesByPeriod.setAverageTotal(cumulativeTotal / salesQuotesList.size());
            salesQuotesByPeriod.setAverageTarget(cumulativeTarget / salesQuotesList.size());
        }
        return salesQuoteTargetList;
	}

	public List<SalesInvoicesByPeriod> getFinancialYearSalesInvoiceTargetList(List<SalesInvoicesByPeriod> salesInvoicesList) {
		List<SalesInvoicesByPeriod> salesInvoiceTargetList = new ArrayList<SalesInvoicesByPeriod>();
        double periodTarget = 0D;
        double cumulativeTarget = 0;
        double cumulativeTotal = 0;
        double noOfWorkingDays = 0;
        for (SalesInvoicesByPeriod salesInvoicesByPeriod : salesInvoicesList) {
            periodTarget = (salesInvoicesByPeriod.getQuantity() != null && salesInvoicesByPeriod.getQuantity()>0)? (salesInvoicesByPeriod.getTotalTarget().doubleValue() / salesInvoicesByPeriod.getQuantity().doubleValue()): salesInvoicesByPeriod.getTotalTarget().doubleValue();
            noOfWorkingDays = (salesInvoicesByPeriod.getQuantity() != null && salesInvoicesByPeriod.getQuantity()>0)? salesInvoicesByPeriod.getNoOfWorkingDays()/salesInvoicesByPeriod.getQuantity().doubleValue():salesInvoicesByPeriod.getNoOfWorkingDays();
            salesInvoicesByPeriod.setNoOfWorkingDays(noOfWorkingDays);
            cumulativeTarget += periodTarget;
            salesInvoicesByPeriod.setTotalTarget(periodTarget);
            salesInvoicesByPeriod.setCumulativeTarget(cumulativeTarget);
            cumulativeTotal += salesInvoicesByPeriod.getTotal() != null ? salesInvoicesByPeriod.getTotal().doubleValue() : 0;
            salesInvoicesByPeriod.setCumulativeTotal(cumulativeTotal);
            salesInvoiceTargetList.add(salesInvoicesByPeriod);
        }
        for (SalesInvoicesByPeriod salesInvoicesByPeriod : salesInvoiceTargetList) {
            LOG.info("salesInvoicesByPeriod-->"+salesInvoicesByPeriod.getYear()+"/"+salesInvoicesByPeriod.getPeriodNo());
            salesInvoicesByPeriod.setAverageTotal(cumulativeTotal / salesInvoicesList.size());
            salesInvoicesByPeriod.setAverageTarget(cumulativeTarget / salesInvoicesList.size());
        }
        return salesInvoiceTargetList;
	}
}