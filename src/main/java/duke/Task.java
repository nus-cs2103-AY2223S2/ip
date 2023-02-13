package duke;

import java.util.Objects;

/**
 * class for a generic task
 */
public class Task {
    protected static final String DIVIDER = "/!@#&/";
    protected String description;
    protected Boolean isDone;

    /**
     * creates a Task instance with the specified description
     * 
     * @param description description of the task
     * @throws MissingDescriptionException missing description
     */
    public Task(String description) throws MissingDescriptionException {
        if (description.equals("") || description.equals(" ")) {
            throw new MissingDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }

    /**
     * gets the description of the task
     * 
     * @return a string representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the completion status of the task
     * 
     * @return a String "X" representing completed or " " representing uncompleted
     *         task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * marks a task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * unmarks a task ie changes the completion status (isDone) from completed to
     * uncompleted
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * returns string representation of task
     * 
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

    /**
     * returns string representation of task to be saved into the text file
     * 
     * @return string representation of task to be saved into text file
     */
    public String toStorageData() {
        return this.toString();
    }
}
