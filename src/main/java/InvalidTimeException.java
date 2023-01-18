public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super("Incomplete timestamp was given!");
    }
}
