package duke.task;

/**
 * Task that is used to handle a task without any time limitation.
 */
public class ToDo extends Task {

    /**
     * Class constructor of ToDo.
     * @param input the title of the ToDo Task
     */
    public ToDo(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the todo task.
     * @return the string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task in a format for saving a task into the local data file.
     * @return the string representation of the todo task in a format for saving a task into the local data file
     */
    @Override
    public String toTxtString() {
        return "T" + super.toTxtString();
    }
}
