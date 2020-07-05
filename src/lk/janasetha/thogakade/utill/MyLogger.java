package lk.janasetha.thogakade.utill;


import java.util.Date;

public class MyLogger {

    private static MyLogger myLogger;

    private MyLogger() {
    }

    public static MyLogger getLogger() {
        if (myLogger == null) {
            myLogger = new MyLogger();
        }
        return myLogger;
    }

    public void debug(Object msg) {
        System.out.print("Janasetha ");
        System.out.print("Thogakade | ");
        System.out.print(new Date().toString() + " | ");
        System.out.println(msg.toString());
    }
}
