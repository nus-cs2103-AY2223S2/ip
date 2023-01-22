public class EmptyDescription extends Exception {
    public EmptyDescription(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}
