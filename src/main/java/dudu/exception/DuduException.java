package dudu.exception;

/**
 * Main exception class for Dudu
 */
public class DuduException extends Exception {
    private String detail;

    /**
     * Constructor for Dudu exception.
     * @param msg Error message.
     */
    public DuduException(String msg) {
        super(msg);
    }

    /**
     * Constructor for Dudu exception, with detailed message.
     * @param detail Detailed message from Dudu.
     * @param msg Error message
     */
    public DuduException(String detail, String msg) {
        super(msg);
        this.detail = detail;
    }
    @Override
    public String toString() {
        return this.detail;
    }
}
