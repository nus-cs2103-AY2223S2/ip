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
    public Deadline(boolean isCompleted, String userInput) {
        super(isCompleted, userInput.substring(9, userInput.indexOf("/by ") - 1), 'D');
        this.byDate = userInput.substring(userInput.indexOf("/by ") + 4);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + byDate + ")";
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.byDate;
    }
}
