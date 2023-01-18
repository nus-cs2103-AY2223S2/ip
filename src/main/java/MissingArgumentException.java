public class MissingArgumentException extends TwoFiveException {
    private String argumentType;

    public MissingArgumentException(String argumentType) {
        this.argumentType = argumentType;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You are missing the argument " + argumentType;
    }
}
