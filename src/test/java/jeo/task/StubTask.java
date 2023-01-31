package jeo.task;

/**
 * Represents a stub task for testing.
 * @author Goh Jun How
 * @version 0.1
 */
public class StubTask extends Task {
    protected String stubDescription;

    /**
     * StubTask constructor to be inherited
     * @param description Describes the task
     */
    public StubTask(String description) {
        super(description);
        stubDescription = description;
    }

    /**
     * String representation of stub task
     * @return String representation of stub task
     */
    @Override
    public String toString() {
        return stubDescription;
    }

}
