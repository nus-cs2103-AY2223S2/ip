package duke.exceptions;

public class DukeException extends Throwable {

    public DukeException() {
        super();
    }

    @Override
    public String toString() {
        return "duke.Duke Exception: Something went wrong! Couldn't really pinpoint it though D:";
    }

}
