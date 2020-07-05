package lk.janasetha.thogakade.main;

import lk.janasetha.thogakade.reports.Report;
import net.sf.jasperreports.engine.JRException;

import java.awt.*;
import java.io.IOException;

public class ReportUp {
    public static void main(String[] args) {
        try {
            new Report().generateReport();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
