public class DukeException extends RuntimeException {
    private String errMsg = "ERROR!";
    public DukeException() {
        super();
    }
    public DukeException(String errMsg) {
        super();
        this.errMsg = errMsg;
    }

    public String getMessage() {
        return this.errMsg;
    }
}
