public class NumberMissingException extends Exception{
    public NumberMissingException() {
        super("Invalid input. Task Number is required.");
    }
}
