package exceptions;

import java.io.FileNotFoundException;

public class DukeFileNotFoundException extends FileNotFoundException {
    public DukeFileNotFoundException(String message) {
        super(message);
    }
}
