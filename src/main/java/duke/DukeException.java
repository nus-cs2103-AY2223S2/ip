package duke;

// class to handle any undesirable inputs by the user and display an error message
public class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}
