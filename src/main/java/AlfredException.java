public class AlfredException extends Exception {
    public AlfredException(String message) {
        super("OOPS!!! " + message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
