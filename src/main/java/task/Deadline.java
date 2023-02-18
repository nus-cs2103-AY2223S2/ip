package task;

import java.time.LocalDate;

/**
 * The Deadline class extends the Task class and represents a deadline task.
 * It extends the `Task` class and contains additional information
 * about the due date of the deadline.
 */
public class Deadline extends Task {
    static final String TASKSYMBOL = "[D]";
    static final String TASKSAVESYMBOL = "D";
    private LocalDate dueDate;

    /**
     * Constructor for the Deadline class which takes in a name and a due date.
     *
     * @param name     name of the task
     * @param dueDate  due date of the task
     */
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Returns the Deadline task in the format
     * "[TASKSAVESYMBOL | [mark] | [tag] | [task name] | [dueDate] | [tag name]" where
     * TASKSAVESYMBOL is a static final variable that represents a Deadline task for saving
     * mark is "1" if the task is marked done, and "0" otherwise.
     * tag is "1" if the task is tagged, and "0" otherwise. If tag is 0, there is no tag name.
     *
     * @return a formatted string for deadline task that can be saved to a file
     */
    @Override
    public String toSaveFormat() {
        return TASKSAVESYMBOL + super.toSaveFormat() + DIVIDER + dueDate + getSaveTag();
    }

    /**
     * Method that returns the Deadline task as a string in the format
     * "[TASKSYMBOL][status icon][task name][dueDate][tag name]" where
     * TASKSYMBOL is a static final variable that represents a deadline task to display to user
     * status icon is "X" if the task is marked done, and " " otherwise.
     * tag is "1" if the task is tagged, and "0" otherwise. If tag is 0, there is no tag name.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return TASKSYMBOL + super.toString() + "(" + dueDate + ")";
    }
}
