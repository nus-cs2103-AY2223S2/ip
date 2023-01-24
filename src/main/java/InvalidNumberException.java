public class InvalidNumberException extends Exception{

    public InvalidNumberException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
