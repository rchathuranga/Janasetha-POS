package lk.janasetha.thogakade.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;

public class Report {
    public void generateReport() throws JRException {

        InputStream invoice = this.getClass().getResourceAsStream("/lk/janasetha/thogakade/reports/janasetha_bill.jasper");
        System.out.println(invoice);

//        JRBeanCollectionDataSource jr=new JRBeanCollectionDataSource(array);
        HashMap map = new HashMap();
        map.put("orderType", "නිපුණ නාඩු");
        map.put("orderTotal", "45.32");
//        map.put("POSItems",jr);


        JasperPrint print = JasperFillManager.fillReport(invoice, map, new JREmptyDataSource());
        JasperViewer.viewReport(print);
    }
}
