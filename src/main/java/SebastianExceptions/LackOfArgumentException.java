package SebastianExceptions;

public class LackOfArgumentException extends InputFormatMismatchException{
    public LackOfArgumentException() {
        super("Please specify an argument");
    }
}
