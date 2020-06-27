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

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;
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

        DailySalesOrders salesOrderedDaily = dashboardService.findSalesOrdersDaily();
        DailySalesInvoices salesInvoicedDaily = dashboardService.findSalesInvoicesDaily();
        DailySalesQuotes salesQuotesDaily = dashboardService.findSalesQuotesDaily();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getOrderValue());
        double salesInvoicedTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getInvoiceValue());
        double salesQuotesTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getQuoteValue());

        double salesOrderedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getOrderQty());
        double salesInvoicedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getInvoiceQty());
        double salesQuotesTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets.getQuoteQty());

        double salesOrderedQtyDaily = salesOrderedDaily.getQuantity().doubleValue();
        double salesInvoicedQtyDaily = salesInvoicedDaily.getQuantity().doubleValue();
        double salesQuotesQtyDaily = salesQuotesDaily.getQuantity().doubleValue();

        double salesOrderedProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getOrderProfit()!=null?monthlyTargets.getOrderProfit().doubleValue():0D);
        double salesInvoicedProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getInvoiceProfit()!=null?monthlyTargets.getInvoiceProfit().doubleValue():0D);
        double salesQuotesProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets.getQuoteProfit()!=null?monthlyTargets.getQuoteProfit().doubleValue():0D);


        double salesOrderedProfitDaily = salesOrderedDaily.getProfit().doubleValue();
        double salesInvoicedProfitDaily = salesInvoicedDaily.getProfit().doubleValue();
        double salesQuotesProfitDaily = salesQuotesDaily.getProfit().doubleValue();


        // to be deleted
        /*
         * salesOrderedDaily = 5689.2653; salesInvoicedDaily = 8956.3625;
         * salesQuotesDaily = 9588; salesTargetDaily = 10000;
         * 
         * salesOrderedQtyDaily = 34; salesInvoicedQtyDaily = 52; salesQuotesQtyDaily =
         * 61;
         */
        modelAndView.addObject("salesOrderedDaily", formatter.format(salesOrderedDaily.getTotal().doubleValue()));
        modelAndView.addObject("salesInvoicedDaily", formatter.format(salesInvoicedDaily.getTotal().doubleValue()));
        modelAndView.addObject("salesQuotesDaily", formatter.format(salesQuotesDaily.getTotal().doubleValue()));

        modelAndView.addObject("salesOrderedTargetDaily", formatter.format(salesOrderedTargetDaily));
        modelAndView.addObject("salesInvoicedTargetDaily", formatter.format(salesInvoicedTargetDaily));
        modelAndView.addObject("salesQuotesTargetDaily", formatter.format(salesQuotesTargetDaily));

        modelAndView.addObject("salesOrderedQtyDaily", (int) Math.round(salesOrderedQtyDaily));
        modelAndView.addObject("salesInvoicedQtyDaily", (int) Math.round(salesInvoicedQtyDaily));
        modelAndView.addObject("salesQuotesQtyDaily", (int) Math.round(salesQuotesQtyDaily));

        modelAndView.addObject("salesOrderedTargetQtyDaily", (int) Math.round(salesOrderedTargetQtyDaily));
        modelAndView.addObject("salesInvoicedTargetQtyDaily", (int) Math.round(salesInvoicedTargetQtyDaily));
        modelAndView.addObject("salesQuotesTargetQtyDaily", (int) Math.round(salesQuotesTargetQtyDaily));

        modelAndView.addObject("salesOrderedProfitTargetDaily",formatter.format(salesOrderedProfitTargetDaily));
        modelAndView.addObject("salesInvoicedProfitTargetDaily",formatter.format(salesInvoicedProfitTargetDaily));
        modelAndView.addObject("salesQuotesProfitTargetDaily",formatter.format(salesQuotesProfitTargetDaily));
                
        modelAndView.addObject("salesOrderedProfitDaily",formatter.format(salesOrderedProfitDaily));
        modelAndView.addObject("salesInvoicedProfitDaily",formatter.format(salesInvoicedProfitDaily));
        modelAndView.addObject("salesQuotesProfitDaily",formatter.format(salesQuotesProfitDaily));
         

        double targetAchievedOrders = (salesOrderedTargetDaily > 0? (new BigDecimal((salesOrderedDaily.getTotal().doubleValue() / salesOrderedTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue(): 0);
        double targetAchievedQuotes = (salesQuotesTargetDaily > 0
                ? (new BigDecimal((salesQuotesDaily.getTotal().doubleValue() / salesQuotesTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0);
        double targetAchievedInvoices = (salesInvoicedTargetDaily > 0
                ? (new BigDecimal((salesInvoicedDaily.getTotal().doubleValue() / salesInvoicedTargetDaily) * 100))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue()
                : 0);

        LOG.info("targetAchievedOrders-->" + targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->" + targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->" + targetAchievedInvoices);

        modelAndView.addObject("targetAchievedOrders", targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes", targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices", targetAchievedInvoices);

        double averageDailyOrders = (salesOrderedQtyDaily > 0
                ? (new BigDecimal((salesOrderedDaily.getTotal().doubleValue() / salesOrderedQtyDaily))).setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0);
        double averageDailyQuotes = (salesQuotesQtyDaily > 0
                ? (new BigDecimal((salesQuotesDaily.getTotal().doubleValue() / salesQuotesQtyDaily))).setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0);
        double averageDailyInvoices = (salesInvoicedQtyDaily > 0
                ? (new BigDecimal((salesInvoicedDaily.getTotal().doubleValue() / salesInvoicedQtyDaily))).setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0);

        modelAndView.addObject("averageDailyOrders", averageDailyOrders);
        modelAndView.addObject("averageDailyQuotes", averageDailyQuotes);
        modelAndView.addObject("averageDailyInvoices", averageDailyInvoices);

        double averageDailyOrdersTarget = (salesOrderedTargetQtyDaily > 0
                ? (new BigDecimal((salesOrderedTargetDaily / salesOrderedTargetQtyDaily)))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue()
                : 0);
        double averageDailyQuotesTarget = (salesQuotesTargetQtyDaily > 0
                ? (new BigDecimal((salesQuotesTargetDaily / salesQuotesTargetQtyDaily)))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue()
                : 0);
        double averageDailyInvoicesTarget = (salesInvoicedTargetQtyDaily > 0
                ? (new BigDecimal((salesInvoicedTargetDaily / salesInvoicedTargetQtyDaily)))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue()
                : 0);

        modelAndView.addObject("averageDailyOrdersTarget", averageDailyOrdersTarget);
        modelAndView.addObject("averageDailyQuotesTarget", averageDailyQuotesTarget);
        modelAndView.addObject("averageDailyInvoicesTarget", averageDailyInvoicesTarget);

        double averageDailyOrdersProfitTarget = (salesOrderedTargetQtyDaily>0?(new BigDecimal((salesOrderedProfitTargetDaily/salesOrderedTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotesProfitTarget = (salesQuotesTargetQtyDaily>0?(new BigDecimal((salesQuotesProfitTargetDaily/salesQuotesTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoicesProfitTarget = (salesInvoicedTargetQtyDaily>0?(new BigDecimal((salesInvoicedProfitTargetDaily/salesInvoicedTargetQtyDaily))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrdersProfitTarget",averageDailyOrdersProfitTarget);
        modelAndView.addObject("averageDailyQuotesProfitTarget",averageDailyQuotesProfitTarget);
        modelAndView.addObject("averageDailyInvoicesProfitTarget",averageDailyInvoicesProfitTarget);

        double averageDailyOrdersProfit = (salesOrderedDaily.getQuantity().doubleValue()>0?(new BigDecimal((salesOrderedProfitDaily/salesOrderedDaily.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotesProfit = (salesQuotesDaily.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesProfitDaily/salesQuotesDaily.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoicesProfit = (salesInvoicedDaily.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicedProfitDaily/salesInvoicedDaily.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrdersProfit",averageDailyOrdersProfit);
        modelAndView.addObject("averageDailyQuotesProfit",averageDailyQuotesProfit);
        modelAndView.addObject("averageDailyInvoicesProfit",averageDailyInvoicesProfit);


        modelAndView.addObject("toDay", new Date());

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

        MonthlySalesOrders salesOrderedMTD = dashboardService.findSalesOrdersMTD();
        MonthlySalesInvoices salesInvoicesMTD = dashboardService.findSalesInvoicesMTD();
        MonthlySalesQuotes salesQuotesMTD = dashboardService.findSalesQuotesMTD();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetMonthly = monthlyTargets.getOrderValue();
        double salesInvoicedTargetMonthly = monthlyTargets.getInvoiceValue();
        double salesQuotesTargetMonthly = monthlyTargets.getQuoteValue();

        double salesOrderedTargetQtyMonthly = monthlyTargets.getOrderQty().doubleValue();
        double salesInvoicedTargetQtyMonthly = monthlyTargets.getInvoiceQty().doubleValue();
        double salesQuotesTargetQtyMonthly = monthlyTargets.getQuoteQty().doubleValue();
        
        double salesOrderedProfitTargetMonthly = monthlyTargets.getOrderProfit()!=null?monthlyTargets.getOrderProfit().doubleValue():0D;
        double salesInvoicedProfitTargetMonthly = monthlyTargets.getInvoiceProfit()!=null?monthlyTargets.getInvoiceProfit().doubleValue():0D;
        double salesQuotesProfitTargetMonthly = monthlyTargets.getQuoteProfit()!=null?monthlyTargets.getQuoteProfit().doubleValue():0D;

        double salesOrderedProfitMonthly = salesOrderedMTD.getProfit().doubleValue();
        double salesInvoicedProfitMonthly = salesInvoicesMTD.getProfit().doubleValue();
        double salesQuotesProfitMonthly = salesQuotesMTD.getProfit().doubleValue();

        //to be deleted
        /*salesOrderedMTD = 5689.2653;
        salesInvoicedMTD = 8956.3625;
        salesQuotesMTD = 9588;
        salesTargetMTD = 10000;

        salesOrderedQtyMTD = 34;
        salesInvoicedQtyMTD = 52;
        salesQuotesQtyMTD = 61;
        */
        modelAndView.addObject("salesOrderedMTD",formatter.format(salesOrderedMTD!=null && salesOrderedMTD.getTotal()!=null?salesOrderedMTD.getTotal().doubleValue():0D));
        modelAndView.addObject("salesInvoicedMTD",formatter.format(salesInvoicesMTD!=null && salesInvoicesMTD.getTotal()!=null?salesInvoicesMTD.getTotal().doubleValue():0D));
        modelAndView.addObject("salesQuotesMTD",formatter.format(salesQuotesMTD!=null && salesQuotesMTD.getTotal()!=null?salesQuotesMTD.getTotal().doubleValue():0D));
        
        modelAndView.addObject("salesOrderedTargetMonthly",formatter.format(salesOrderedTargetMonthly));
        modelAndView.addObject("salesInvoicedTargetMonthly",formatter.format(salesInvoicedTargetMonthly));
        modelAndView.addObject("salesQuotesTargetMonthly",formatter.format(salesQuotesTargetMonthly));
        
        modelAndView.addObject("salesOrderedQtyMTD",(int) Math.round(salesOrderedMTD!=null && salesOrderedMTD.getQuantity()!=null?salesOrderedMTD.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesInvoicedQtyMTD",(int) Math.round(salesInvoicesMTD!=null && salesInvoicesMTD.getQuantity()!=null?salesInvoicesMTD.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesQuotesQtyMTD",(int) Math.round(salesQuotesMTD!=null && salesQuotesMTD.getQuantity()!=null?salesQuotesMTD.getQuantity().doubleValue():0D));

        modelAndView.addObject("salesOrderedTargetQtyMonthly",(int) Math.round(salesOrderedTargetQtyMonthly));
        modelAndView.addObject("salesInvoicedTargetQtyMonthly",(int) Math.round(salesInvoicedTargetQtyMonthly));
        modelAndView.addObject("salesQuotesTargetQtyMonthly",(int) Math.round(salesQuotesTargetQtyMonthly));

        modelAndView.addObject("salesOrderedProfitTargetMonthly",formatter.format(salesOrderedProfitTargetMonthly));
        modelAndView.addObject("salesInvoicedProfitTargetMonthly",formatter.format(salesInvoicedProfitTargetMonthly));
        modelAndView.addObject("salesQuotesProfitTargetMonthly",formatter.format(salesQuotesProfitTargetMonthly));
                
        modelAndView.addObject("salesOrderedProfitMonthly",formatter.format(salesOrderedProfitMonthly));
        modelAndView.addObject("salesInvoicedProfitMonthly",formatter.format(salesInvoicedProfitMonthly));
        modelAndView.addObject("salesQuotesProfitMonthly",formatter.format(salesQuotesProfitMonthly));
         
        double targetAchievedOrders = (salesOrderedTargetMonthly>0?(new BigDecimal((salesOrderedMTD.getTotal().doubleValue()/salesOrderedTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesQuotesTargetMonthly>0?(new BigDecimal((salesQuotesMTD.getTotal().doubleValue()/salesQuotesTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesInvoicedTargetMonthly>0?(new BigDecimal((salesInvoicesMTD.getTotal().doubleValue()/salesInvoicedTargetMonthly) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        double averageMonthlyOrders = (salesOrderedMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesOrderedMTD.getTotal().doubleValue()/salesOrderedMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotes = (salesQuotesMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesMTD.getTotal().doubleValue()/salesQuotesMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoices = (salesInvoicesMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicesMTD.getTotal().doubleValue()/salesInvoicesMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrders",averageMonthlyOrders);
        modelAndView.addObject("averageMonthlyQuotes",averageMonthlyQuotes);
        modelAndView.addObject("averageMonthlyInvoices",averageMonthlyInvoices);

        double averageMonthlyOrdersTarget = (salesOrderedTargetQtyMonthly>0?(new BigDecimal((salesOrderedTargetMonthly/salesOrderedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesTarget = (salesQuotesTargetQtyMonthly>0?(new BigDecimal((salesQuotesTargetMonthly/salesQuotesTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesTarget = (salesInvoicedTargetQtyMonthly>0?(new BigDecimal((salesInvoicedTargetMonthly/salesInvoicedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersTarget",averageMonthlyOrdersTarget);
        modelAndView.addObject("averageMonthlyQuotesTarget",averageMonthlyQuotesTarget);
        modelAndView.addObject("averageMonthlyInvoicesTarget",averageMonthlyInvoicesTarget);


        double averageMonthlyOrdersProfitTarget = (salesOrderedTargetQtyMonthly>0?(new BigDecimal((salesOrderedProfitTargetMonthly/salesOrderedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesProfitTarget = (salesQuotesTargetQtyMonthly>0?(new BigDecimal((salesQuotesProfitTargetMonthly/salesQuotesTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesProfitTarget = (salesInvoicedTargetQtyMonthly>0?(new BigDecimal((salesInvoicedProfitTargetMonthly/salesInvoicedTargetQtyMonthly))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersProfitTarget",averageMonthlyOrdersProfitTarget);
        modelAndView.addObject("averageMonthlyQuotesProfitTarget",averageMonthlyQuotesProfitTarget);
        modelAndView.addObject("averageMonthlyInvoicesProfitTarget",averageMonthlyInvoicesProfitTarget);

        double averageMonthlyOrdersProfit = (salesOrderedMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesOrderedProfitMonthly/salesOrderedMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesProfit = (salesQuotesMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesProfitMonthly/salesQuotesMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesProfit = (salesInvoicesMTD.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicedProfitMonthly/salesInvoicesMTD.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersProfit",averageMonthlyOrdersProfit);
        modelAndView.addObject("averageMonthlyQuotesProfit",averageMonthlyQuotesProfit);
        modelAndView.addObject("averageMonthlyInvoicesProfit",averageMonthlyInvoicesProfit);

        modelAndView.addObject("thisMonth",Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("thisYear",Calendar.getInstance().get(Calendar.YEAR));

        modelAndView.setViewName("admin/chartMonthToDate");
        return modelAndView;
    }

}
