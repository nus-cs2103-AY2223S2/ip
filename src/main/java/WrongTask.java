public class WrongTask extends Exception {
    public WrongTask(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}

