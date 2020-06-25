package au.com.onesysconsulting.cscparts.dashboard.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;
import au.com.onesysconsulting.cscparts.dashboard.service.DashboardService;

@Controller
public class ChartController {

    private static final Logger LOG = LoggerFactory.getLogger(ChartController.class);
    
    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "/admin/chartToday", method = RequestMethod.GET)
    public ModelAndView chartToday() {
        ModelAndView modelAndView = new ModelAndView();
        
        LOG.info("chartToday");
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        
        double salesOrderedDaily = dashboardService.findSalesEnteredDaily();
        double salesInvoicedDaily = dashboardService.findSalesInvoicedDaily();
        double salesQuotesDaily = dashboardService.findSalesQuotesDaily();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getOrderValue());
        double salesInvoicedTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getInvoiceValue());
        double salesQuotesTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getQuoteValue());

        double salesOrderedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getOrderQty());
        double salesInvoicedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getInvoiceQty());
        double salesQuotesTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getQuoteQty());

        double salesOrderedQtyDaily = dashboardService.findSalesEnteredQtyDaily();
        double salesInvoicedQtyDaily = dashboardService.findSalesInvoicedQtyDaily();
        double salesQuotesQtyDaily = dashboardService.findSalesQuotesQtyDaily();

        //to be deleted
        /*salesOrderedDaily = 5689.2653;
        salesInvoicedDaily = 8956.3625;
        salesQuotesDaily = 9588;
        salesTargetDaily = 10000;

        salesOrderedQtyDaily = 34;
        salesInvoicedQtyDaily = 52;
        salesQuotesQtyDaily = 61;
        */
        modelAndView.addObject("salesOrderedDaily",formatter.format(salesOrderedDaily));
        modelAndView.addObject("salesInvoicedDaily",formatter.format(salesInvoicedDaily));
        modelAndView.addObject("salesQuotesDaily",formatter.format(salesQuotesDaily));

        modelAndView.addObject("salesOrderedTargetDaily",formatter.format(salesOrderedTargetDaily));
        modelAndView.addObject("salesInvoicedTargetDaily",formatter.format(salesInvoicedTargetDaily));
        modelAndView.addObject("salesQuotesTargetDaily",formatter.format(salesQuotesTargetDaily));
        
        modelAndView.addObject("salesOrderedQtyDaily",(int) Math.round(salesOrderedQtyDaily));
        modelAndView.addObject("salesInvoicedQtyDaily",(int) Math.round(salesInvoicedQtyDaily));
        modelAndView.addObject("salesQuotesQtyDaily",(int) Math.round(salesQuotesQtyDaily));

        modelAndView.addObject("salesOrderedTargetQtyDaily",(int) Math.round(salesOrderedTargetQtyDaily));
        modelAndView.addObject("salesInvoicedTargetQtyDaily",(int) Math.round(salesInvoicedTargetQtyDaily));
        modelAndView.addObject("salesQuotesTargetQtyDaily",(int) Math.round(salesQuotesTargetQtyDaily));
                       
        double targetAchievedOrders = (salesOrderedTargetDaily>0?(new BigDecimal((salesOrderedDaily/salesOrderedTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesQuotesTargetDaily>0?(new BigDecimal((salesQuotesDaily/salesQuotesTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesInvoicedTargetDaily>0?(new BigDecimal((salesInvoicedDaily/salesInvoicedTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        double averageDailyOrders = (salesOrderedQtyDaily>0?(new BigDecimal((salesOrderedDaily/salesOrderedQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotes = (salesQuotesQtyDaily>0?(new BigDecimal((salesQuotesDaily/salesQuotesQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoices = (salesInvoicedQtyDaily>0?(new BigDecimal((salesInvoicedDaily/salesInvoicedQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrders",averageDailyOrders);
        modelAndView.addObject("averageDailyQuotes",averageDailyQuotes);
        modelAndView.addObject("averageDailyInvoices",averageDailyInvoices);

        double averageDailyOrdersTarget = (salesOrderedTargetQtyDaily>0?(new BigDecimal((salesOrderedTargetDaily/salesOrderedTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotesTarget = (salesQuotesTargetQtyDaily>0?(new BigDecimal((salesQuotesTargetDaily/salesQuotesTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoicesTarget = (salesInvoicedTargetQtyDaily>0?(new BigDecimal((salesInvoicedTargetDaily/salesInvoicedTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrdersTarget",averageDailyOrdersTarget);
        modelAndView.addObject("averageDailyQuotesTarget",averageDailyQuotesTarget);
        modelAndView.addObject("averageDailyInvoicesTarget",averageDailyInvoicesTarget);

        modelAndView.addObject("toDay",new Date());

        modelAndView.setViewName("admin/chartToday");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/chartMonthToDate", method = RequestMethod.GET)
    public ModelAndView chartMonthToDate() {
        ModelAndView modelAndView = new ModelAndView();
        
        LOG.info("chartMonthToDate");
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        
        double salesOrderedMTD = dashboardService.findSalesEnteredMTD();
        double salesInvoicedMTD = dashboardService.findSalesInvoicedMTD();
        double salesQuotesMTD = dashboardService.findSalesQuotesMTD();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetMonthly = monthlyTargets.getOrderValue();
        double salesInvoicedTargetMonthly = monthlyTargets.getInvoiceValue();
        double salesQuotesTargetMonthly = monthlyTargets.getQuoteValue();


        double salesOrderedQtyMTD = dashboardService.findSalesOrdersQtyMTD();
        double salesInvoicedQtyMTD = dashboardService.findSalesInvoicedQtyMTD();
        double salesQuotesQtyMTD = dashboardService.findSalesQuotesQtyMTD();

        double salesOrderedTargetQtyMonthly = monthlyTargets.getOrderQty().doubleValue();
        double salesInvoicedTargetQtyMonthly = monthlyTargets.getInvoiceQty().doubleValue();
        double salesQuotesTargetQtyMonthly = monthlyTargets.getQuoteQty().doubleValue();
        
        //to be deleted
        /*salesOrderedMTD = 5689.2653;
        salesInvoicedMTD = 8956.3625;
        salesQuotesMTD = 9588;
        salesTargetMTD = 10000;

        salesOrderedQtyMTD = 34;
        salesInvoicedQtyMTD = 52;
        salesQuotesQtyMTD = 61;
        */
        modelAndView.addObject("salesOrderedMTD",formatter.format(salesOrderedMTD));
        modelAndView.addObject("salesInvoicedMTD",formatter.format(salesInvoicedMTD));
        modelAndView.addObject("salesQuotesMTD",formatter.format(salesQuotesMTD));
        
        modelAndView.addObject("salesOrderedTargetMonthly",formatter.format(salesOrderedTargetMonthly));
        modelAndView.addObject("salesInvoicedTargetMonthly",formatter.format(salesInvoicedTargetMonthly));
        modelAndView.addObject("salesQuotesTargetMonthly",formatter.format(salesQuotesTargetMonthly));
        
        modelAndView.addObject("salesOrderedQtyMTD",(int) Math.round(salesOrderedQtyMTD));
        modelAndView.addObject("salesInvoicedQtyMTD",(int) Math.round(salesInvoicedQtyMTD));
        modelAndView.addObject("salesQuotesQtyMTD",(int) Math.round(salesQuotesQtyMTD));

        modelAndView.addObject("salesOrderedTargetQtyMonthly",(int) Math.round(salesOrderedTargetQtyMonthly));
        modelAndView.addObject("salesInvoicedTargetQtyMonthly",(int) Math.round(salesInvoicedTargetQtyMonthly));
        modelAndView.addObject("salesQuotesTargetQtyMonthly",(int) Math.round(salesQuotesTargetQtyMonthly));
                
        double targetAchievedOrders = (salesOrderedTargetMonthly>0?(new BigDecimal((salesOrderedMTD/salesOrderedTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesQuotesTargetMonthly>0?(new BigDecimal((salesQuotesMTD/salesQuotesTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesInvoicedTargetMonthly>0?(new BigDecimal((salesInvoicedMTD/salesInvoicedTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        double averageMonthlyOrders = (salesOrderedQtyMTD>0?(new BigDecimal((salesOrderedMTD/salesOrderedQtyMTD))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotes = (salesQuotesQtyMTD>0?(new BigDecimal((salesQuotesMTD/salesQuotesQtyMTD))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoices = (salesInvoicedQtyMTD>0?(new BigDecimal((salesInvoicedMTD/salesInvoicedQtyMTD))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrders",averageMonthlyOrders);
        modelAndView.addObject("averageMonthlyQuotes",averageMonthlyQuotes);
        modelAndView.addObject("averageMonthlyInvoices",averageMonthlyInvoices);

        double averageMonthlyOrdersTarget = (salesOrderedTargetQtyMonthly>0?(new BigDecimal((salesOrderedTargetMonthly/salesOrderedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesTarget = (salesQuotesTargetQtyMonthly>0?(new BigDecimal((salesQuotesTargetMonthly/salesQuotesTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesTarget = (salesInvoicedTargetQtyMonthly>0?(new BigDecimal((salesInvoicedTargetMonthly/salesInvoicedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersTarget",averageMonthlyOrdersTarget);
        modelAndView.addObject("averageMonthlyQuotesTarget",averageMonthlyQuotesTarget);
        modelAndView.addObject("averageMonthlyInvoicesTarget",averageMonthlyInvoicesTarget);


        modelAndView.addObject("thisMonth",Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("thisYear",Calendar.getInstance().get(Calendar.YEAR));

        modelAndView.setViewName("admin/chartMonthToDate");
        return modelAndView;
    }

}
