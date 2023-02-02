package duke.utility.ui;

import duke.tasklist.task_types.Task;
import duke.utility.parser.CommandMap;

/**
 * Represents a <code>UiMessage</code> object that contains a command type
 * and the task involved in the command operation.
 * 
 * 
 * @author Brian Quek
 */
public class UiMessage extends Ui {
    public final CommandMap TYPE;
    protected Task task;

    /**
     * Constructor for UiMessage object, default type is toDo
     */
    public UiMessage() {
        TYPE = CommandMap.todo;
    }

    /**
     * Constructor for UiMessage object by taking in 2 parameters for command type and task involved.
     * 
     * @param type command type of the UiMessage
     * @param task task involved in the command
     */
    public UiMessage(CommandMap type, Task task) {
        this.TYPE = type;
        this.task = task;
    }
}
