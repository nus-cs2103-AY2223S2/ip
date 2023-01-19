public class JamesException extends Exception {

    private final String message;

    public JamesException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
