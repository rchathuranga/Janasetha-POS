package lk.janasetha.thogakade.main;

import lk.janasetha.thogakade.reports.Report;
import net.sf.jasperreports.engine.JRException;

public class ReportUp {
    public static void main(String[] args) {
        try {
            Report.getInstance().generateOrderBillReport(null, null);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
