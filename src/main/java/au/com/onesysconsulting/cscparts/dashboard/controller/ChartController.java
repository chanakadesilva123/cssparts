package au.com.onesysconsulting.cscparts.dashboard.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        double salesTargetDaily = dashboardService.findSalesTargetDaily();

        double salesOrderedQtyDaily = dashboardService.findSalesEnteredQtyDaily();
        double salesInvoicedQtyDaily = dashboardService.findSalesInvoicedQtyDaily();
        double salesQuotesQtyDaily = dashboardService.findSalesQuotesQtyDaily();

        //to be deleted
        salesOrderedDaily = 5689.2653;
        salesInvoicedDaily = 8956.3625;
        salesQuotesDaily = 9588;
        salesTargetDaily = 10000;

        salesOrderedQtyDaily = 34;
        salesInvoicedQtyDaily = 52;
        salesQuotesQtyDaily = 61;

        modelAndView.addObject("salesOrderedDaily",formatter.format(salesOrderedDaily));
        modelAndView.addObject("salesInvoicedDaily",formatter.format(salesInvoicedDaily));
        modelAndView.addObject("salesQuotesDaily",formatter.format(salesQuotesDaily));
        modelAndView.addObject("salesTargetDaily",formatter.format(salesTargetDaily));
        
        modelAndView.addObject("salesOrderedQtyDaily",(int) Math.round(salesOrderedQtyDaily));
        modelAndView.addObject("salesInvoicedQtyDaily",(int) Math.round(salesInvoicedQtyDaily));
        modelAndView.addObject("salesQuotesQtyDaily",(int) Math.round(salesQuotesQtyDaily));
                
        double targetAchievedOrders = (salesTargetDaily>0?(new BigDecimal((salesOrderedDaily/salesTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedQuotes = (salesTargetDaily>0?(new BigDecimal((salesQuotesDaily/salesTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
        double targetAchievedInvoices = (salesTargetDaily>0?(new BigDecimal((salesInvoicedDaily/salesTargetDaily) * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue():0);
  
        LOG.info("targetAchievedOrders-->"+targetAchievedOrders);
        LOG.info("targetAchievedQuotes-->"+targetAchievedQuotes);
        LOG.info("targetAchievedInvoices-->"+targetAchievedInvoices);


        modelAndView.addObject("targetAchievedOrders",targetAchievedOrders);
        modelAndView.addObject("targetAchievedQuotes",targetAchievedQuotes);
        modelAndView.addObject("targetAchievedInvoices",targetAchievedInvoices);

        modelAndView.addObject("toDay",new Date());

        modelAndView.setViewName("admin/chartToday");
        return modelAndView;
    }

}
