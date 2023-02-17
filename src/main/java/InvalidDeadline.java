public class InvalidDeadline extends DukeExceptions {
    public InvalidDeadline() {
        super("Deadline invalid. Please fill in your deadline properly eg. deadline [description] /by [by]");
    }

    public InvalidDeadline(String msg) {
        super(msg);
    }
}
