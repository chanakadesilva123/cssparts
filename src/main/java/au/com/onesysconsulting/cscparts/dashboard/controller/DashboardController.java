package au.com.onesysconsulting.cscparts.dashboard.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.DailySalesQuotes;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesInvoices;
import au.com.onesysconsulting.cscparts.dashboard.model.MonthlySalesOrders;
import au.com.onesysconsulting.cscparts.dashboard.model.SalesTarget;
import au.com.onesysconsulting.cscparts.dashboard.service.DashboardService;

@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
    
    @Autowired
    private DashboardService dashboardService;

    
    @RequestMapping(path = "/fetch_currency_value", method = RequestMethod.GET)
    public @ResponseBody String getCurrencyValue(@RequestParam String sqlView) {
        String message="";
        boolean status=false;
        double value=0D;
        LOG.info("getCurrencyValue->sqlView=" + sqlView);
        if(sqlView!=null && !sqlView.isEmpty())
        {
            if(sqlView.equalsIgnoreCase("salesInvoicedMTD"))
            {
                MonthlySalesInvoices monthlySalesInvoices = dashboardService.findSalesInvoicesMTD();
                value = monthlySalesInvoices!=null && monthlySalesInvoices.getTotal()!=null?monthlySalesInvoices.getTotal().doubleValue():0D;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesInvoicedDaily"))
            {
                DailySalesInvoices dailySalesInvoices = dashboardService.findSalesInvoicesDaily();
                value = dailySalesInvoices!=null && dailySalesInvoices.getTotal()!=null?dailySalesInvoices.getTotal().doubleValue():0D;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesEnteredMTD"))
            {
                MonthlySalesOrders monthlySalesOrders = dashboardService.findSalesOrdersMTD();
                value = monthlySalesOrders!=null && monthlySalesOrders.getTotal()!=null?monthlySalesOrders.getTotal().doubleValue():0D;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesEnteredDaily"))
            {
                DailySalesOrders dailySalesOrders = dashboardService.findSalesOrdersDaily();
                value = dailySalesOrders!=null && dailySalesOrders.getTotal()!=null?dailySalesOrders.getTotal().doubleValue():0D;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesQuotesDaily"))
            {
                
                DailySalesQuotes dailySalesQuotes = dashboardService.findSalesQuotesDaily();
                value = dailySalesQuotes!=null && dailySalesQuotes.getTotal()!=null?dailySalesQuotes.getTotal().doubleValue():0D;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesQuotesMTD"))
            {
                value = dashboardService.findSalesQuotesMTD().getTotal().doubleValue();
                status = true;
                message = "SUCCESS";
            }
        }
        else{
            message = "INVALID INPUT PARAM or INPUT PARAM EMPTY";
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("message", message);
        result.put("status", String.valueOf(status));
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        result.put("value", formatter.format(value));
        LOG.info("getCurrencyValue->value=" + value);
        Gson gson = new Gson();
        return gson.toJson(result);
    }
    @RequestMapping(path = "/fetch_quntity_value", method = RequestMethod.GET)
    public @ResponseBody String getQuntityValue(@RequestParam String sqlView) {
        String message="";
        boolean status=false;
        int value=0;
        LOG.info("getQuntityValue->sqlView=" + sqlView);
        if(sqlView!=null && !sqlView.isEmpty())
        {
            if(sqlView.equalsIgnoreCase("salesOrdersQtyMTD"))
            {
                MonthlySalesOrders monthlySalesOrders = dashboardService.findSalesOrdersMTD();
                value = monthlySalesOrders!=null && monthlySalesOrders.getQuantity()!=null?monthlySalesOrders.getQuantity().intValue():0;
                status = true;
                message = "SUCCESS";
            }
            if(sqlView.equalsIgnoreCase("salesOrdersQtyDaily"))
            {
                DailySalesOrders dailySalesOrders = dashboardService.findSalesOrdersDaily();
                value = dailySalesOrders!=null && dailySalesOrders.getQuantity()!=null?dailySalesOrders.getQuantity().intValue():0;
                status = true;
                message = "SUCCESS";
            }
        }
        else{
            message = "INVALID INPUT PARAM or INPUT PARAM EMPTY";
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("message", message);
        result.put("status", String.valueOf(status));
        result.put("value", NumberFormat.getIntegerInstance().format(value));
        LOG.info("getQuntityValue->value=" + value);
        Gson gson = new Gson();
        return gson.toJson(result);
    }
    @RequestMapping(path = "/fetch_sales_target", method = RequestMethod.GET)
    public @ResponseBody String getSalesTarget() {
        List<SalesTarget> salesTarget = dashboardService.findSalesTarget();
        LOG.info("getSalesTarget->salesTarget=" + (salesTarget!=null?salesTarget.size():0));
        Gson gson = new Gson();
        return gson.toJson((salesTarget!=null?salesTarget:new ArrayList<SalesTarget>()));
    }
}
