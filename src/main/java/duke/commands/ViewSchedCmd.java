package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.CommandExecutionError;
import duke.parsing.Parser;
import duke.tasks.TaskList;

/**
 * Command class for 'todo' command keyword.
 * Creates a new ToDo task and adds it to the task list.
 * <p>
 * Command format: "todo &lt;task_name&gt;"
 */
public class ViewSchedCmd extends Command {

    /**
     * Constructor method.
     *
     * @param taskList task list to add the ToDo task to
     * @param lineInput command line input that the user entered
     */
    public ViewSchedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /**
     * Finds tasks in the task list that ends on/before the specified due date.
     */
    public String execute() throws CommandExecutionError {
        try {
            LocalDate date = Parser.parseViewScheduleDate(lineInput);
            TaskList schedResult = this.taskList.viewSched(date);
            return String.format("Here's what you have on %s or before:\n%s",
                    date.toString(), schedResult.toString());
        } catch (DateTimeParseException e) {
            throw new CommandExecutionError(e.toString());
        }
    };
}
