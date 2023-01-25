package chattime.exception;

public class ChattimeException extends Exception {

    private String errMsg;

    public ChattimeException(String message) {
        super(message);
        errMsg = message;
    }

    @Override
    public String toString() {
        return errMsg;
    }

}
