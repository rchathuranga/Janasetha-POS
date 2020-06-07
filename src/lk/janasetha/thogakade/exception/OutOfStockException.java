package lk.janasetha.thogakade.exception;

public class OutOfStockException extends Exception{
    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String message) {
        super(message+ "Out Of Stock");
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfStockException(Throwable cause) {
        super(cause);
    }

    protected OutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
