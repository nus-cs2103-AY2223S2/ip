public class OutOfBounds extends Exception {
    public OutOfBounds(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}