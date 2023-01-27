package duke;

public class DukeException extends Exception {
    public String message;

    public DukeException(String message) {
        this.message = message;
    }
}
