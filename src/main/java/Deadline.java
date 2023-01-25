/**
 * Class that defines the Deadline type of tasks
 */
public class Deadline extends Task {

    /** The end date of this deadline object */
    protected String byDate;

    /**
     * Constructor for objects of type Deadline
     *
     * @param userInput specifies the byDate and title of a Deadline object
     */
    public Deadline(String userInput) {
        super(userInput.substring(9, userInput.indexOf("/by ") - 1));
        this.byDate = userInput.substring(userInput.indexOf("/by ") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}
