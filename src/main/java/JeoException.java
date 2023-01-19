public class JeoException extends Exception {

    public JeoException(String e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "[Error] " + super.getMessage();
    }
}
