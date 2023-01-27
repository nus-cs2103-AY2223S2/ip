package duke;

public class MissingContentException extends duke.DukeException {
    public MissingContentException() {
        super("OPPS! The content/detail cannot be empty!");
    }
}