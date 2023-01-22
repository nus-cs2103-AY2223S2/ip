package duke.exception;

public abstract class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    public abstract String getExceptionName();

    @Override
    public String toString() {
        String s = String.format("%s: %s", this.getExceptionName(), super.getMessage());
        return s;
    }
}
