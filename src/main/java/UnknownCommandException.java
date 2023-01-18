public class UnknownCommandException extends Exception {
    public UnknownCommandException(String errMsg) {
        super(errMsg);
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
