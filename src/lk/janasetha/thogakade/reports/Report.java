package lk.janasetha.thogakade.reports;

import lk.janasetha.thogakade.dto.CompleteOrderDTO;
import lk.janasetha.thogakade.dto.QueryDTO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

public class Report {
    private static Report report;

    private Report() {
    }

    public static Report getInstance() {
        if (report == null) report = new Report();
        return report;
    }

    public void generateOrderBillReport(CompleteOrderDTO completeOrderDTO, List<QueryDTO> orderItemList) throws JRException {
        InputStream invoice = this.getClass().getResourceAsStream("/lk/janasetha/thogakade/reports/Order_Bill_new.jasper");
        System.out.println(invoice);


        HashMap<String, Object> map = new HashMap<>();

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orderItemList);


        map.put("orderItems", jrBeanCollectionDataSource);
        map.put("orderDate", new Date(System.currentTimeMillis()).toString());
        map.put("orderTime", new Time(System.currentTimeMillis()).toString());
        map.put("orderId", 13253);
        map.put("cashier", "admin");

        JasperPrint print = JasperFillManager.fillReport(invoice, map, new JREmptyDataSource());
        JasperViewer.viewReport(print, false);
    }
}
