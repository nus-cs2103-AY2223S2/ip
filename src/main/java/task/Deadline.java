package task;

import java.time.LocalDate;

/**
 * The Deadline class extends the Task class and represents a deadline task.
 * It contains a due date which is a local date.
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
     * Overrides the toSaveFormat method in the super class to add the due date.
     *
     * @return a string in the format "D" | [markToInt] | [name] | [dueDate]"
     */
    @Override
    public String toSaveFormat() {
        return TASKSAVESYMBOL + super.toSaveFormat() + DIVIDER + dueDate;
    }

    /**
     * Overrides the toString method in the super class to add the due date.
     *
     * @return a string in the format "[D] [status icon] [name] ([dueDate])"
     */
    @Override
    public String toString() {
        return TASKSYMBOL + super.toString() + "(" + dueDate + ")";
    }
}
