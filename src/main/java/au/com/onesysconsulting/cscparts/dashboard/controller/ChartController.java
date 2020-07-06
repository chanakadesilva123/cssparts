package au.com.onesysconsulting.cscparts.dashboard.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.LastMonthSalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.LastThreeMonthsSalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlyTargets;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesInvoicesByDate;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesOrdersByDate;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesQuotesByDate;
import au.com.onesysconsulting.cscparts.dashboard.service.DashboardService;

@Controller
public class ChartController {

    private static final Logger LOG = LoggerFactory.getLogger(ChartController.class);

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = {"/admin/chartToday","/screen/chartToday"}, method = RequestMethod.GET)
    public ModelAndView chartToday(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LOG.info("chartToday-->"+request.getServletPath());
        modelAndView.addObject("isFullScreen", request.getServletPath().startsWith("/screen/"));
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        DailySalesOrders salesOrderedDaily = dashboardService.findSalesOrdersDaily();
        DailySalesInvoices salesInvoicedDaily = dashboardService.findSalesInvoicesDaily();
        DailySalesQuotes salesQuotesDaily = dashboardService.findSalesQuotesDaily();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getOrderValue():0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesInvoicedTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getInvoiceValue():0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesQuotesTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getQuoteValue():0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);

        double salesOrderedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getOrderQty():0,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesInvoicedTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getInvoiceQty():0,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesQuotesTargetQtyDaily = dashboardService.findDailyTarget(monthlyTargets!=null?monthlyTargets.getQuoteQty():0,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);

        double salesOrderedQtyDaily = salesOrderedDaily.getQuantity().doubleValue();
        double salesInvoicedQtyDaily = salesInvoicedDaily.getQuantity().doubleValue();
        double salesQuotesQtyDaily = salesQuotesDaily.getQuantity().doubleValue();

        double salesOrderedProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null && monthlyTargets.getOrderProfit()!=null?(monthlyTargets.getOrderValue().doubleValue() * monthlyTargets.getOrderProfit().doubleValue())/100:0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesInvoicedProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null && monthlyTargets.getInvoiceProfit()!=null?(monthlyTargets.getInvoiceValue().doubleValue() * monthlyTargets.getInvoiceProfit().doubleValue())/100:0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);
        double salesQuotesProfitTargetDaily = dashboardService.findDailyTarget(monthlyTargets!=null && monthlyTargets.getQuoteProfit()!=null?(monthlyTargets.getQuoteValue().doubleValue() * monthlyTargets.getQuoteProfit().doubleValue())/100:0D,monthlyTargets!=null?monthlyTargets.getNoOfWorkingDays():0);


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
        modelAndView.addObject("salesOrderedDaily", salesOrderedDaily.getTotal().doubleValue());
        modelAndView.addObject("salesInvoicedDaily", salesInvoicedDaily.getTotal().doubleValue());
        modelAndView.addObject("salesQuotesDaily", salesQuotesDaily.getTotal().doubleValue());

        modelAndView.addObject("salesOrderedTargetDaily", salesOrderedTargetDaily);
        modelAndView.addObject("salesInvoicedTargetDaily", salesInvoicedTargetDaily);
        modelAndView.addObject("salesQuotesTargetDaily", salesQuotesTargetDaily);

        modelAndView.addObject("salesOrderedQtyDaily", (int) Math.round(salesOrderedQtyDaily));
        modelAndView.addObject("salesInvoicedQtyDaily", (int) Math.round(salesInvoicedQtyDaily));
        modelAndView.addObject("salesQuotesQtyDaily", (int) Math.round(salesQuotesQtyDaily));

        modelAndView.addObject("salesOrderedTargetQtyDaily", (int) Math.round(salesOrderedTargetQtyDaily));
        modelAndView.addObject("salesInvoicedTargetQtyDaily", (int) Math.round(salesInvoicedTargetQtyDaily));
        modelAndView.addObject("salesQuotesTargetQtyDaily", (int) Math.round(salesQuotesTargetQtyDaily));

        modelAndView.addObject("salesOrderedProfitTargetDaily",(salesOrderedProfitTargetDaily));
        modelAndView.addObject("salesInvoicedProfitTargetDaily",(salesInvoicedProfitTargetDaily));
        modelAndView.addObject("salesQuotesProfitTargetDaily",(salesQuotesProfitTargetDaily));
                
        modelAndView.addObject("salesOrderedProfitDaily",(salesOrderedProfitDaily));
        modelAndView.addObject("salesInvoicedProfitDaily",(salesInvoicedProfitDaily));
        modelAndView.addObject("salesQuotesProfitDaily",(salesQuotesProfitDaily));
         

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

        double averageDailyOrdersProfitTarget = (salesOrderedTargetDaily>0?(new BigDecimal((salesOrderedProfitTargetDaily/salesOrderedTargetDaily)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotesProfitTarget = (salesQuotesTargetDaily>0?(new BigDecimal((salesQuotesProfitTargetDaily/salesQuotesTargetDaily)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoicesProfitTarget = (salesInvoicedTargetDaily>0?(new BigDecimal((salesInvoicedProfitTargetDaily/salesInvoicedTargetDaily)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrdersProfitTarget",averageDailyOrdersProfitTarget);
        modelAndView.addObject("averageDailyQuotesProfitTarget",averageDailyQuotesProfitTarget);
        modelAndView.addObject("averageDailyInvoicesProfitTarget",averageDailyInvoicesProfitTarget);

        double averageDailyOrdersProfit = (salesOrderedDaily.getTotal().doubleValue()>0?(new BigDecimal((salesOrderedProfitDaily/salesOrderedDaily.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyQuotesProfit = (salesQuotesDaily.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesProfitDaily/salesQuotesDaily.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageDailyInvoicesProfit = (salesInvoicedDaily.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicedProfitDaily/salesInvoicedDaily.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageDailyOrdersProfit",averageDailyOrdersProfit);
        modelAndView.addObject("averageDailyQuotesProfit",averageDailyQuotesProfit);
        modelAndView.addObject("averageDailyInvoicesProfit",averageDailyInvoicesProfit);


        List<SalesOrdersByDate> salesOrdersList = dashboardService.findMonthToDateSalesOrders();
        List<SalesQuotesByDate> salesQuotesList = dashboardService.findMonthToDateSalesQuotes();
        List<SalesInvoicesByDate> salesInvoicesList = dashboardService.findMonthToDateSalesInvoices();

        modelAndView.addObject("salesOrdersList",salesOrdersList);
        modelAndView.addObject("salesQuotesList",salesQuotesList);
        modelAndView.addObject("salesInvoicesList",salesInvoicesList);

        LOG.info("salesOrdersList==>"+salesOrdersList.size());
        LOG.info("salesQuotesList==>"+salesQuotesList.size());
        LOG.info("salesInvoicesList==>"+salesInvoicesList.size());
        List<SalesOrdersByDate>  salesOrderTargets = dashboardService.getSalesOrderTargetList(salesOrdersList);
        List<SalesQuotesByDate> salesQuoteTargets = dashboardService.getSalesQuoteTargetList(salesQuotesList);
        List<SalesInvoicesByDate> salesInvoiceTargets = dashboardService.getSalesInvoiceTargetList(salesInvoicesList);

        modelAndView.addObject("salesOrderTargets",salesOrderTargets);
        modelAndView.addObject("salesQuoteTargets",salesQuoteTargets);
        modelAndView.addObject("salesInvoiceTargets",salesInvoiceTargets);

        SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy");
        modelAndView.addObject("lastUpdated", new Date());
        modelAndView.addObject("toDay", sdf.format(new Date()));

        modelAndView.setViewName("admin/chartToday");
        return modelAndView;
    }

    @RequestMapping(value ={"/admin/chartMonthToDate","/screen/chartMonthToDate"}, method = RequestMethod.GET)
    public ModelAndView chartMonthToDate(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LOG.info("chartMonthToDate-->"+request.getServletPath());
        modelAndView.addObject("isFullScreen", request.getServletPath().startsWith("/screen/"));
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        MonthlySalesOrders salesOrderedMTD = dashboardService.findSalesOrdersMTD();
        MonthlySalesInvoices salesInvoicesMTD = dashboardService.findSalesInvoicesMTD();
        MonthlySalesQuotes salesQuotesMTD = dashboardService.findSalesQuotesMTD();

        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH));
        double salesOrderedTargetMonthly = monthlyTargets!=null?monthlyTargets.getOrderValue():0D;
        double salesInvoicedTargetMonthly = monthlyTargets!=null?monthlyTargets.getInvoiceValue():0D;
        double salesQuotesTargetMonthly = monthlyTargets!=null?monthlyTargets.getQuoteValue():0D;

        double salesOrderedTargetQtyMonthly = monthlyTargets!=null?monthlyTargets.getOrderQty().doubleValue():0D;
        double salesInvoicedTargetQtyMonthly = monthlyTargets!=null?monthlyTargets.getInvoiceQty().doubleValue():0D;
        double salesQuotesTargetQtyMonthly = monthlyTargets!=null?monthlyTargets.getQuoteQty().doubleValue():0D;
        
        double salesOrderedProfitTargetMonthly = monthlyTargets!=null && monthlyTargets.getOrderProfit()!=null?(monthlyTargets.getOrderValue().doubleValue() * monthlyTargets.getOrderProfit().doubleValue())/100:0D;
        double salesInvoicedProfitTargetMonthly = monthlyTargets!=null && monthlyTargets.getInvoiceProfit()!=null?(monthlyTargets.getInvoiceValue().doubleValue() * monthlyTargets.getInvoiceProfit().doubleValue())/100:0D;
        double salesQuotesProfitTargetMonthly = monthlyTargets!=null && monthlyTargets.getQuoteProfit()!=null?(monthlyTargets.getQuoteValue().doubleValue() * monthlyTargets.getQuoteProfit().doubleValue())/100:0D;

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
        modelAndView.addObject("salesOrderedMTD",(salesOrderedMTD!=null && salesOrderedMTD.getTotal()!=null?salesOrderedMTD.getTotal().doubleValue():0D));
        modelAndView.addObject("salesInvoicedMTD",(salesInvoicesMTD!=null && salesInvoicesMTD.getTotal()!=null?salesInvoicesMTD.getTotal().doubleValue():0D));
        modelAndView.addObject("salesQuotesMTD",(salesQuotesMTD!=null && salesQuotesMTD.getTotal()!=null?salesQuotesMTD.getTotal().doubleValue():0D));
        
        modelAndView.addObject("salesOrderedTargetMonthly",(salesOrderedTargetMonthly));
        modelAndView.addObject("salesInvoicedTargetMonthly",(salesInvoicedTargetMonthly));
        modelAndView.addObject("salesQuotesTargetMonthly",(salesQuotesTargetMonthly));
        
        modelAndView.addObject("salesOrderedQtyMTD",(int) Math.round(salesOrderedMTD!=null && salesOrderedMTD.getQuantity()!=null?salesOrderedMTD.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesInvoicedQtyMTD",(int) Math.round(salesInvoicesMTD!=null && salesInvoicesMTD.getQuantity()!=null?salesInvoicesMTD.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesQuotesQtyMTD",(int) Math.round(salesQuotesMTD!=null && salesQuotesMTD.getQuantity()!=null?salesQuotesMTD.getQuantity().doubleValue():0D));

        modelAndView.addObject("salesOrderedTargetQtyMonthly",(int) Math.round(salesOrderedTargetQtyMonthly));
        modelAndView.addObject("salesInvoicedTargetQtyMonthly",(int) Math.round(salesInvoicedTargetQtyMonthly));
        modelAndView.addObject("salesQuotesTargetQtyMonthly",(int) Math.round(salesQuotesTargetQtyMonthly));

        modelAndView.addObject("salesOrderedProfitTargetMonthly",(salesOrderedProfitTargetMonthly));
        modelAndView.addObject("salesInvoicedProfitTargetMonthly",(salesInvoicedProfitTargetMonthly));
        modelAndView.addObject("salesQuotesProfitTargetMonthly",(salesQuotesProfitTargetMonthly));
                
        modelAndView.addObject("salesOrderedProfitMonthly",(salesOrderedProfitMonthly));
        modelAndView.addObject("salesInvoicedProfitMonthly",(salesInvoicedProfitMonthly));
        modelAndView.addObject("salesQuotesProfitMonthly",(salesQuotesProfitMonthly));
         
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


        double averageMonthlyOrdersProfitTarget = (salesOrderedTargetMonthly>0?(new BigDecimal((salesOrderedProfitTargetMonthly/salesOrderedTargetMonthly)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesProfitTarget = (salesQuotesTargetMonthly>0?(new BigDecimal((salesQuotesProfitTargetMonthly/salesQuotesTargetMonthly)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesProfitTarget = (salesInvoicedTargetMonthly>0?(new BigDecimal((salesInvoicedProfitTargetMonthly/salesInvoicedTargetMonthly)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersProfitTarget",averageMonthlyOrdersProfitTarget);
        modelAndView.addObject("averageMonthlyQuotesProfitTarget",averageMonthlyQuotesProfitTarget);
        modelAndView.addObject("averageMonthlyInvoicesProfitTarget",averageMonthlyInvoicesProfitTarget);

        double averageMonthlyOrdersProfit = (salesOrderedMTD.getTotal().doubleValue()>0?(new BigDecimal((salesOrderedProfitMonthly/salesOrderedMTD.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyQuotesProfit = (salesQuotesMTD.getTotal().doubleValue()>0?(new BigDecimal((salesQuotesProfitMonthly/salesQuotesMTD.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageMonthlyInvoicesProfit = (salesInvoicesMTD.getTotal().doubleValue()>0?(new BigDecimal((salesInvoicedProfitMonthly/salesInvoicesMTD.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageMonthlyOrdersProfit",averageMonthlyOrdersProfit);
        modelAndView.addObject("averageMonthlyQuotesProfit",averageMonthlyQuotesProfit);
        modelAndView.addObject("averageMonthlyInvoicesProfit",averageMonthlyInvoicesProfit);

        List<SalesOrdersByDate> salesOrdersList = dashboardService.findMonthToDateSalesOrders();
        List<SalesQuotesByDate> salesQuotesList = dashboardService.findMonthToDateSalesQuotes();
        List<SalesInvoicesByDate> salesInvoicesList = dashboardService.findMonthToDateSalesInvoices();

        modelAndView.addObject("salesOrdersList",salesOrdersList);
        modelAndView.addObject("salesQuotesList",salesQuotesList);
        modelAndView.addObject("salesInvoicesList",salesInvoicesList);

        LOG.info("salesOrdersList==>"+salesOrdersList.size());
        LOG.info("salesQuotesList==>"+salesQuotesList.size());
        LOG.info("salesInvoicesList==>"+salesInvoicesList.size());
        List<SalesOrdersByDate>  salesOrderTargets = dashboardService.getSalesOrderTargetList(salesOrdersList);
        List<SalesQuotesByDate> salesQuoteTargets = dashboardService.getSalesQuoteTargetList(salesQuotesList);
        List<SalesInvoicesByDate> salesInvoiceTargets = dashboardService.getSalesInvoiceTargetList(salesInvoicesList);

        modelAndView.addObject("salesOrderTargets",salesOrderTargets);
        modelAndView.addObject("salesQuoteTargets",salesQuoteTargets);
        modelAndView.addObject("salesInvoiceTargets",salesInvoiceTargets);

        modelAndView.addObject("thisMonth",Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("thisYear",Calendar.getInstance().get(Calendar.YEAR));

        modelAndView.addObject("lastUpdated", new Date());
        modelAndView.setViewName("admin/chartMonthToDate");
        
        return modelAndView;
    }
    @RequestMapping(value = {"/admin/chartLastMonth","/screen/chartLastMonth"}, method = RequestMethod.GET)
    public ModelAndView chartLastMonth(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LOG.info("chartLastMonth-->"+request.getServletPath());
        modelAndView.addObject("isFullScreen", request.getServletPath().startsWith("/screen/"));
        
        LOG.info("chartLastMonth");
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        LastMonthSalesOrders salesOrderedLastMonth = dashboardService.findSalesOrdersLastMonth();
        LastMonthSalesInvoices salesInvoicesLastMonth = dashboardService.findSalesInvoicesLastMonth();
        LastMonthSalesQuotes salesQuotesLastMonth = dashboardService.findSalesQuotesLastMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        MonthlyTargets monthlyTargets = dashboardService.findMonthlyTargets(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
        double salesOrderedTargetLastMonth = monthlyTargets!=null?monthlyTargets.getOrderValue():0D;
        double salesInvoicedTargetLastMonth = monthlyTargets!=null?monthlyTargets.getInvoiceValue():0D;
        double salesQuotesTargetLastMonth = monthlyTargets!=null?monthlyTargets.getQuoteValue():0D;

        double salesOrderedTargetQtyLastMonth = monthlyTargets!=null?monthlyTargets.getOrderQty().doubleValue():0D;
        double salesInvoicedTargetQtyLastMonth = monthlyTargets!=null?monthlyTargets.getInvoiceQty().doubleValue():0D;
        double salesQuotesTargetQtyLastMonth = monthlyTargets!=null?monthlyTargets.getQuoteQty().doubleValue():0D;
        
        double salesOrderedProfitTargetLastMonth = monthlyTargets!=null && monthlyTargets.getOrderProfit()!=null?(monthlyTargets.getOrderValue().doubleValue() * monthlyTargets.getOrderProfit().doubleValue())/100:0D;
        double salesInvoicedProfitTargetLastMonth = monthlyTargets!=null && monthlyTargets.getInvoiceProfit()!=null?(monthlyTargets.getInvoiceValue().doubleValue() * monthlyTargets.getInvoiceProfit().doubleValue())/100:0D;
        double salesQuotesProfitTargetLastMonth = monthlyTargets!=null && monthlyTargets.getQuoteProfit()!=null?(monthlyTargets.getQuoteValue().doubleValue() * monthlyTargets.getQuoteProfit().doubleValue())/100:0D;

        double salesOrderedProfitLastMonth = salesOrderedLastMonth.getProfit().doubleValue();
        double salesInvoicedProfitLastMonth = salesInvoicesLastMonth.getProfit().doubleValue();
        double salesQuotesProfitLastMonth = salesQuotesLastMonth.getProfit().doubleValue();

        //to be deleted
        /*salesOrderedLastMonth = 5689.2653;
        salesInvoicedLastMonth = 8956.3625;
        salesQuotesLastMonth = 9588;
        salesTargetLastMonth = 10000;

        salesOrderedQtyLastMonth = 34;
        salesInvoicedQtyLastMonth = 52;
        salesQuotesQtyLastMonth = 61;
        */
        modelAndView.addObject("salesOrderedLastMonth",(salesOrderedLastMonth!=null && salesOrderedLastMonth.getTotal()!=null?salesOrderedLastMonth.getTotal().doubleValue():0D));
        modelAndView.addObject("salesInvoicedLastMonth",(salesInvoicesLastMonth!=null && salesInvoicesLastMonth.getTotal()!=null?salesInvoicesLastMonth.getTotal().doubleValue():0D));
        modelAndView.addObject("salesQuotesLastMonth",(salesQuotesLastMonth!=null && salesQuotesLastMonth.getTotal()!=null?salesQuotesLastMonth.getTotal().doubleValue():0D));
        
        modelAndView.addObject("salesOrderedTargetLastMonth",(salesOrderedTargetLastMonth));
        modelAndView.addObject("salesInvoicedTargetLastMonth",(salesInvoicedTargetLastMonth));
        modelAndView.addObject("salesQuotesTargetLastMonth",(salesQuotesTargetLastMonth));
        
        modelAndView.addObject("salesOrderedQtyLastMonth",(int) Math.round(salesOrderedLastMonth!=null && salesOrderedLastMonth.getQuantity()!=null?salesOrderedLastMonth.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesInvoicedQtyLastMonth",(int) Math.round(salesInvoicesLastMonth!=null && salesInvoicesLastMonth.getQuantity()!=null?salesInvoicesLastMonth.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesQuotesQtyLastMonth",(int) Math.round(salesQuotesLastMonth!=null && salesQuotesLastMonth.getQuantity()!=null?salesQuotesLastMonth.getQuantity().doubleValue():0D));

        modelAndView.addObject("salesOrderedTargetQtyLastMonth",(int) Math.round(salesOrderedTargetQtyLastMonth));
        modelAndView.addObject("salesInvoicedTargetQtyLastMonth",(int) Math.round(salesInvoicedTargetQtyLastMonth));
        modelAndView.addObject("salesQuotesTargetQtyLastMonth",(int) Math.round(salesQuotesTargetQtyLastMonth));

        modelAndView.addObject("salesOrderedProfitTargetLastMonth",(salesOrderedProfitTargetLastMonth));
        modelAndView.addObject("salesInvoicedProfitTargetLastMonth",(salesInvoicedProfitTargetLastMonth));
        modelAndView.addObject("salesQuotesProfitTargetLastMonth",(salesQuotesProfitTargetLastMonth));
                
        modelAndView.addObject("salesOrderedProfitLastMonth",(salesOrderedProfitLastMonth));
        modelAndView.addObject("salesInvoicedProfitLastMonth",(salesInvoicedProfitLastMonth));
        modelAndView.addObject("salesQuotesProfitLastMonth",(salesQuotesProfitLastMonth));
         
        double targetAchievedOrders = (salesOrderedTargetLastMonth>0?(new BigDecimal((salesOrderedLastMonth.getTotal().doubleValue()/salesOrderedTargetLastMonth) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesQuotesTargetLastMonth>0?(new BigDecimal((salesQuotesLastMonth.getTotal().doubleValue()/salesQuotesTargetLastMonth) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesInvoicedTargetLastMonth>0?(new BigDecimal((salesInvoicesLastMonth.getTotal().doubleValue()/salesInvoicedTargetLastMonth) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        double averageLastMonthOrders = (salesOrderedLastMonth.getQuantity().doubleValue()>0?(new BigDecimal((salesOrderedLastMonth.getTotal().doubleValue()/salesOrderedLastMonth.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthQuotes = (salesQuotesLastMonth.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesLastMonth.getTotal().doubleValue()/salesQuotesLastMonth.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthInvoices = (salesInvoicesLastMonth.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicesLastMonth.getTotal().doubleValue()/salesInvoicesLastMonth.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastMonthOrders",averageLastMonthOrders);
        modelAndView.addObject("averageLastMonthQuotes",averageLastMonthQuotes);
        modelAndView.addObject("averageLastMonthInvoices",averageLastMonthInvoices);

        double averageLastMonthOrdersTarget = (salesOrderedTargetQtyLastMonth>0?(new BigDecimal((salesOrderedTargetLastMonth/salesOrderedTargetQtyLastMonth))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthQuotesTarget = (salesQuotesTargetQtyLastMonth>0?(new BigDecimal((salesQuotesTargetLastMonth/salesQuotesTargetQtyLastMonth))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthInvoicesTarget = (salesInvoicedTargetQtyLastMonth>0?(new BigDecimal((salesInvoicedTargetLastMonth/salesInvoicedTargetQtyLastMonth))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastMonthOrdersTarget",averageLastMonthOrdersTarget);
        modelAndView.addObject("averageLastMonthQuotesTarget",averageLastMonthQuotesTarget);
        modelAndView.addObject("averageLastMonthInvoicesTarget",averageLastMonthInvoicesTarget);


        double averageLastMonthOrdersProfitTarget = (salesOrderedTargetLastMonth>0?(new BigDecimal((salesOrderedProfitTargetLastMonth/salesOrderedTargetLastMonth)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthQuotesProfitTarget = (salesQuotesTargetLastMonth>0?(new BigDecimal((salesQuotesProfitTargetLastMonth/salesQuotesTargetLastMonth)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthInvoicesProfitTarget = (salesInvoicedTargetLastMonth>0?(new BigDecimal((salesInvoicedProfitTargetLastMonth/salesInvoicedTargetLastMonth)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastMonthOrdersProfitTarget",averageLastMonthOrdersProfitTarget);
        modelAndView.addObject("averageLastMonthQuotesProfitTarget",averageLastMonthQuotesProfitTarget);
        modelAndView.addObject("averageLastMonthInvoicesProfitTarget",averageLastMonthInvoicesProfitTarget);

        double averageLastMonthOrdersProfit = (salesOrderedLastMonth.getTotal().doubleValue()>0?(new BigDecimal((salesOrderedProfitLastMonth/salesOrderedLastMonth.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthQuotesProfit = (salesQuotesLastMonth.getTotal().doubleValue()>0?(new BigDecimal((salesQuotesProfitLastMonth/salesQuotesLastMonth.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastMonthInvoicesProfit = (salesInvoicesLastMonth.getTotal().doubleValue()>0?(new BigDecimal((salesInvoicedProfitLastMonth/salesInvoicesLastMonth.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastMonthOrdersProfit",averageLastMonthOrdersProfit);
        modelAndView.addObject("averageLastMonthQuotesProfit",averageLastMonthQuotesProfit);
        modelAndView.addObject("averageLastMonthInvoicesProfit",averageLastMonthInvoicesProfit);

        List<SalesOrdersByDate> salesOrdersList = dashboardService.findMonthToDateSalesOrders();
        List<SalesQuotesByDate> salesQuotesList = dashboardService.findMonthToDateSalesQuotes();
        List<SalesInvoicesByDate> salesInvoicesList = dashboardService.findMonthToDateSalesInvoices();

        modelAndView.addObject("salesOrdersList",salesOrdersList);
        modelAndView.addObject("salesQuotesList",salesQuotesList);
        modelAndView.addObject("salesInvoicesList",salesInvoicesList);

        LOG.info("salesOrdersList==>"+salesOrdersList.size());
        LOG.info("salesQuotesList==>"+salesQuotesList.size());
        LOG.info("salesInvoicesList==>"+salesInvoicesList.size());
        List<SalesOrdersByDate>  salesOrderTargets = dashboardService.getSalesOrderTargetList(salesOrdersList);
        List<SalesQuotesByDate> salesQuoteTargets = dashboardService.getSalesQuoteTargetList(salesQuotesList);
        List<SalesInvoicesByDate> salesInvoiceTargets = dashboardService.getSalesInvoiceTargetList(salesInvoicesList);

        modelAndView.addObject("salesOrderTargets",salesOrderTargets);
        modelAndView.addObject("salesQuoteTargets",salesQuoteTargets);
        modelAndView.addObject("salesInvoiceTargets",salesInvoiceTargets);

        modelAndView.addObject("lastMonth",calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("thisYear",calendar.get(Calendar.YEAR));
        
        modelAndView.addObject("lastUpdated", new Date());
        
        modelAndView.setViewName("admin/chartLastMonth");
        return modelAndView;
    }
    @RequestMapping(value = {"/admin/chartLastThreeMonths","/screen/chartLastThreeMonths"}, method = RequestMethod.GET)
    public ModelAndView chartLastThreeMonths(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LOG.info("chartLastThreeMonths-->"+request.getServletPath().startsWith("/screen/"));
        modelAndView.addObject("isFullScreen", request.getServletPath().startsWith("/screen/"));
      
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        LastThreeMonthsSalesOrders salesOrderedLastThreeMonths = dashboardService.findSalesOrdersLastThreeMonths();
        LastThreeMonthsSalesInvoices salesInvoicesLastThreeMonths = dashboardService.findSalesInvoicesLastThreeMonths();
        LastThreeMonthsSalesQuotes salesQuotesLastThreeMonths = dashboardService.findSalesQuotesLastThreeMonths();

        MonthlyTargets monthlyTargets = dashboardService.findLastThreeMonthsTargets();
        double salesOrderedTargetLastThreeMonths = monthlyTargets.getOrderValue();
        double salesInvoicedTargetLastThreeMonths = monthlyTargets.getInvoiceValue();
        double salesQuotesTargetLastThreeMonths = monthlyTargets.getQuoteValue();

        double salesOrderedTargetQtyLastThreeMonths = monthlyTargets.getOrderQty().doubleValue();
        double salesInvoicedTargetQtyLastThreeMonths = monthlyTargets.getInvoiceQty().doubleValue();
        double salesQuotesTargetQtyLastThreeMonths = monthlyTargets.getQuoteQty().doubleValue();
        
        double salesOrderedProfitTargetLastThreeMonths = monthlyTargets.getOrderProfit()!=null?monthlyTargets.getOrderProfit().doubleValue():0D;
        double salesInvoicedProfitTargetLastThreeMonths = monthlyTargets.getInvoiceProfit()!=null?monthlyTargets.getInvoiceProfit().doubleValue():0D;
        double salesQuotesProfitTargetLastThreeMonths = monthlyTargets.getQuoteProfit()!=null?monthlyTargets.getQuoteProfit().doubleValue():0D;

        double salesOrderedProfitLastThreeMonths = salesOrderedLastThreeMonths.getProfit().doubleValue();
        double salesInvoicedProfitLastThreeMonths = salesInvoicesLastThreeMonths.getProfit().doubleValue();
        double salesQuotesProfitLastThreeMonths = salesQuotesLastThreeMonths.getProfit().doubleValue();

        //to be deleted
        /*salesOrderedLastThreeMonths = 5689.2653;
        salesInvoicedLastThreeMonths = 8956.3625;
        salesQuotesLastThreeMonths = 9588;
        salesTargetLastThreeMonths = 10000;

        salesOrderedQtyLastThreeMonths = 34;
        salesInvoicedQtyLastThreeMonths = 52;
        salesQuotesQtyLastThreeMonths = 61;
        */
        modelAndView.addObject("salesOrderedLastThreeMonths",(salesOrderedLastThreeMonths!=null && salesOrderedLastThreeMonths.getTotal()!=null?salesOrderedLastThreeMonths.getTotal().doubleValue():0D));
        modelAndView.addObject("salesInvoicedLastThreeMonths",(salesInvoicesLastThreeMonths!=null && salesInvoicesLastThreeMonths.getTotal()!=null?salesInvoicesLastThreeMonths.getTotal().doubleValue():0D));
        modelAndView.addObject("salesQuotesLastThreeMonths",(salesQuotesLastThreeMonths!=null && salesQuotesLastThreeMonths.getTotal()!=null?salesQuotesLastThreeMonths.getTotal().doubleValue():0D));
        
        modelAndView.addObject("salesOrderedTargetLastThreeMonths",(salesOrderedTargetLastThreeMonths));
        modelAndView.addObject("salesInvoicedTargetLastThreeMonths",(salesInvoicedTargetLastThreeMonths));
        modelAndView.addObject("salesQuotesTargetLastThreeMonths",(salesQuotesTargetLastThreeMonths));
        
        modelAndView.addObject("salesOrderedQtyLastThreeMonths",(int) Math.round(salesOrderedLastThreeMonths!=null && salesOrderedLastThreeMonths.getQuantity()!=null?salesOrderedLastThreeMonths.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesInvoicedQtyLastThreeMonths",(int) Math.round(salesInvoicesLastThreeMonths!=null && salesInvoicesLastThreeMonths.getQuantity()!=null?salesInvoicesLastThreeMonths.getQuantity().doubleValue():0D));
        modelAndView.addObject("salesQuotesQtyLastThreeMonths",(int) Math.round(salesQuotesLastThreeMonths!=null && salesQuotesLastThreeMonths.getQuantity()!=null?salesQuotesLastThreeMonths.getQuantity().doubleValue():0D));

        modelAndView.addObject("salesOrderedTargetQtyLastThreeMonths",(int) Math.round(salesOrderedTargetQtyLastThreeMonths));
        modelAndView.addObject("salesInvoicedTargetQtyLastThreeMonths",(int) Math.round(salesInvoicedTargetQtyLastThreeMonths));
        modelAndView.addObject("salesQuotesTargetQtyLastThreeMonths",(int) Math.round(salesQuotesTargetQtyLastThreeMonths));

        modelAndView.addObject("salesOrderedProfitTargetLastThreeMonths",(salesOrderedProfitTargetLastThreeMonths));
        modelAndView.addObject("salesInvoicedProfitTargetLastThreeMonths",(salesInvoicedProfitTargetLastThreeMonths));
        modelAndView.addObject("salesQuotesProfitTargetLastThreeMonths",(salesQuotesProfitTargetLastThreeMonths));
                
        modelAndView.addObject("salesOrderedProfitLastThreeMonths",(salesOrderedProfitLastThreeMonths));
        modelAndView.addObject("salesInvoicedProfitLastThreeMonths",(salesInvoicedProfitLastThreeMonths));
        modelAndView.addObject("salesQuotesProfitLastThreeMonths",(salesQuotesProfitLastThreeMonths));
         
        double targetAchievedOrders = (salesOrderedTargetLastThreeMonths>0?(new BigDecimal((salesOrderedLastThreeMonths.getTotal().doubleValue()/salesOrderedTargetLastThreeMonths) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesQuotesTargetLastThreeMonths>0?(new BigDecimal((salesQuotesLastThreeMonths.getTotal().doubleValue()/salesQuotesTargetLastThreeMonths) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesInvoicedTargetLastThreeMonths>0?(new BigDecimal((salesInvoicesLastThreeMonths.getTotal().doubleValue()/salesInvoicedTargetLastThreeMonths) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        double averageLastThreeMonthsOrders = (salesOrderedLastThreeMonths.getQuantity().doubleValue()>0?(new BigDecimal((salesOrderedLastThreeMonths.getTotal().doubleValue()/salesOrderedLastThreeMonths.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsQuotes = (salesQuotesLastThreeMonths.getQuantity().doubleValue()>0?(new BigDecimal((salesQuotesLastThreeMonths.getTotal().doubleValue()/salesQuotesLastThreeMonths.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsInvoices = (salesInvoicesLastThreeMonths.getQuantity().doubleValue()>0?(new BigDecimal((salesInvoicesLastThreeMonths.getTotal().doubleValue()/salesInvoicesLastThreeMonths.getQuantity().doubleValue()))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastThreeMonthsOrders",averageLastThreeMonthsOrders);
        modelAndView.addObject("averageLastThreeMonthsQuotes",averageLastThreeMonthsQuotes);
        modelAndView.addObject("averageLastThreeMonthsInvoices",averageLastThreeMonthsInvoices);

        double averageLastThreeMonthsOrdersTarget = (salesOrderedTargetQtyLastThreeMonths>0?(new BigDecimal((salesOrderedTargetLastThreeMonths/salesOrderedTargetQtyLastThreeMonths))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsQuotesTarget = (salesQuotesTargetQtyLastThreeMonths>0?(new BigDecimal((salesQuotesTargetLastThreeMonths/salesQuotesTargetQtyLastThreeMonths))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsInvoicesTarget = (salesInvoicedTargetQtyLastThreeMonths>0?(new BigDecimal((salesInvoicedTargetLastThreeMonths/salesInvoicedTargetQtyLastThreeMonths))).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastThreeMonthsOrdersTarget",averageLastThreeMonthsOrdersTarget);
        modelAndView.addObject("averageLastThreeMonthsQuotesTarget",averageLastThreeMonthsQuotesTarget);
        modelAndView.addObject("averageLastThreeMonthsInvoicesTarget",averageLastThreeMonthsInvoicesTarget);

        double averageLastThreeMonthsOrdersProfitTarget = (salesOrderedTargetLastThreeMonths>0?(new BigDecimal((salesOrderedProfitTargetLastThreeMonths/salesOrderedTargetLastThreeMonths)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsQuotesProfitTarget = (salesQuotesTargetLastThreeMonths>0?(new BigDecimal((salesQuotesProfitTargetLastThreeMonths/salesQuotesTargetLastThreeMonths)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsInvoicesProfitTarget = (salesInvoicedTargetLastThreeMonths>0?(new BigDecimal((salesInvoicedProfitTargetLastThreeMonths/salesInvoicedTargetLastThreeMonths)*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        
        modelAndView.addObject("averageLastThreeMonthsOrdersProfitTarget",averageLastThreeMonthsOrdersProfitTarget);
        modelAndView.addObject("averageLastThreeMonthsQuotesProfitTarget",averageLastThreeMonthsQuotesProfitTarget);
        modelAndView.addObject("averageLastThreeMonthsInvoicesProfitTarget",averageLastThreeMonthsInvoicesProfitTarget);

        double averageLastThreeMonthsOrdersProfit = (salesOrderedLastThreeMonths.getTotal().doubleValue()>0?(new BigDecimal((salesOrderedProfitLastThreeMonths/salesOrderedLastThreeMonths.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsQuotesProfit = (salesQuotesLastThreeMonths.getTotal().doubleValue()>0?(new BigDecimal((salesQuotesProfitLastThreeMonths/salesQuotesLastThreeMonths.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double averageLastThreeMonthsInvoicesProfit = (salesInvoicesLastThreeMonths.getTotal().doubleValue()>0?(new BigDecimal((salesInvoicedProfitLastThreeMonths/salesInvoicesLastThreeMonths.getTotal().doubleValue())*100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        modelAndView.addObject("averageLastThreeMonthsOrdersProfit",averageLastThreeMonthsOrdersProfit);
        modelAndView.addObject("averageLastThreeMonthsQuotesProfit",averageLastThreeMonthsQuotesProfit);
        modelAndView.addObject("averageLastThreeMonthsInvoicesProfit",averageLastThreeMonthsInvoicesProfit);

        List<SalesOrdersByDate> salesOrdersList = dashboardService.findMonthToDateSalesOrders();
        List<SalesQuotesByDate> salesQuotesList = dashboardService.findMonthToDateSalesQuotes();
        List<SalesInvoicesByDate> salesInvoicesList = dashboardService.findMonthToDateSalesInvoices();

        modelAndView.addObject("salesOrdersList",salesOrdersList);
        modelAndView.addObject("salesQuotesList",salesQuotesList);
        modelAndView.addObject("salesInvoicesList",salesInvoicesList);

        LOG.info("salesOrdersList==>"+salesOrdersList.size());
        LOG.info("salesQuotesList==>"+salesQuotesList.size());
        LOG.info("salesInvoicesList==>"+salesInvoicesList.size());
        List<SalesOrdersByDate>  salesOrderTargets = dashboardService.getSalesOrderTargetList(salesOrdersList);
        List<SalesQuotesByDate> salesQuoteTargets = dashboardService.getSalesQuoteTargetList(salesQuotesList);
        List<SalesInvoicesByDate> salesInvoiceTargets = dashboardService.getSalesInvoiceTargetList(salesInvoicesList);

        modelAndView.addObject("salesOrderTargets",salesOrderTargets);
        modelAndView.addObject("salesQuoteTargets",salesQuoteTargets);
        modelAndView.addObject("salesInvoiceTargets",salesInvoiceTargets);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        modelAndView.addObject("toMonth",calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("toYear",calendar.get(Calendar.YEAR));
        calendar.add(Calendar.MONTH, -2);
        modelAndView.addObject("fromMonth",calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        modelAndView.addObject("fromYear",calendar.get(Calendar.YEAR));
        
        modelAndView.addObject("lastUpdated", new Date());
        
        modelAndView.setViewName("admin/chartLastThreeMonths");
        return modelAndView;
    }

}
