package genie.exception;

import java.awt.*;

public class EmptyInputException extends DukeException {
    public EmptyInputException(String s) {
        super("Uh oh! The description of " + s + " cannot be empty.");
    }
}
