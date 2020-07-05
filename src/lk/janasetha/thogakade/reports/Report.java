package lk.janasetha.thogakade.reports;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Report {
    public void generateReport() throws JRException, IOException, FontFormatException {
//        InputStream is = this.getClass().getResourceAsStream("/myfont.TTF");
//        Font font = Font.createFont(Font.TRUETYPE_FONT, is);

//        font.
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
