package duke;

public class DukeException extends Exception {
    protected String errorMsg;

    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return this.errorMsg;
    }
}
