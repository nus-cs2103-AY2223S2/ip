package duke.commands;

import duke.Ui;
import duke.exceptions.CommandExecutionError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Command class for 'todo' command keyword.
 * Creates a new ToDo task and adds it to the task list.
 * <p>
 * Command format: "todo &lt;task_name&gt;"
 */
public class ToDoCmd extends Command {
    private Task toDo;

    /**
     * Constructor method.
     * 
     * @param taskList Task list to add the ToDo task to
     * @param lineInput Command line input that the user entered
     */
    public ToDoCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Adds the ToDo task to the task list. */
    public String execute() throws CommandExecutionError {
        this.toDo = ToDo.create(this.lineInput);
        taskList.add(this.toDo);

        return String.format("Got it. I've added this task:\n%s\n%s",
                Ui.indentString(this.toDo.toString(), 1),
                Ui.numTaskToString(taskList.countTasks()));
    };
}
