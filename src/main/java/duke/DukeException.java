package duke;
/**
 * The superclass for Duke exceptions for Duke related issues
 */
public class DukeException extends RuntimeException {
     public DukeException(String errorMessage) {
         super(errorMessage);
     }
     @Override
    public String toString() {
         return "OOPS!!! " + super.getMessage();
     }
}
