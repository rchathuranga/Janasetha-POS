package lk.janasetha.thogakade.utill;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

    public static final Logger logger = Logger.getLogger("Janasetha Logger");

    public static void debug(){
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("G:\\Janasetha\\New\\janasetha_log\\MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
//            logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
