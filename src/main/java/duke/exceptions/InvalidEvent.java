package duke.exceptions;

public class InvalidEvent extends DukeExceptions {
    public InvalidEvent() {
        super("Event invalid. Please fill in your event properly eg. event [description] /from [from] /to [to]");
    }

    public InvalidEvent(String msg) {
        super(msg);
    }
}