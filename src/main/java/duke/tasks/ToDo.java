package duke.tasks;

import duke.Parser;
import duke.exceptions.TaskNameNotSpecified;

/**
 * Wrapper class for 'To do' tasks
 */
public class ToDo extends Task {

    /**
     * Factory method.
     * @param commandInput Command line input that the user entered.
     * @return New ToDo task
     * @throws TaskNameNotSpecified Task name was not specified
     */
    public static ToDo create(String commandInput) throws TaskNameNotSpecified {
        try {
            return new ToDo(Parser.parseToDoCmd(commandInput)[0], false);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified();
        }
    }

    /**
     * Constructor method.
     * @param taskName Task name    
     * @param isDone Completion status of task
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, "T", isDone);
    }

    /**
     * Represents fields of this task as a string
     * @return String representation of fields in this task
     */
    @Override
    public String stringFields() {
        return "";
    }
}