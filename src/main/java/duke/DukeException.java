package duke;

public class DukeException extends Exception {

    /**
     * DukeException constructor. passes on errorMessage into superclass
     * @param errorMessage the description of the duke exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * returns string representation of the duke exception
     * @return the string of the duke exception
     */
    @Override
    public String toString() {
        return "duke.Duke Exception: OOPS! " + super.getMessage();
    }
}
