package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Command that handles the command to add tasks to list.
 */
public class AddCommand extends Command {
    protected String taskTag;
    protected LocalDate deadline;
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor of AddCommand class. Used by ToDo command.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body the task name.
     * @param taskTag specifies the type of task to add, "T" for todo, "D" for deadline, "E" for event.
     */
    public AddCommand(boolean isExit, boolean isValid,
                       String header, String body, String taskTag) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = LocalDate.parse("1970-01-01");
        this.start = LocalDate.parse("1970-01-01");
        this.end = LocalDate.parse("1970-01-01");
    }

    /**
     * Constructor of AddCommand class. Used by DeadLine command.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body the task name.
     * @param taskTag specifies the type of task to add, "T" for todo, "D" for deadline, "E" for event.
     * @param deadline the deadline of the DeadLine task.
     */
    public AddCommand(boolean isExit, boolean isValid,
                      String header, String body, String taskTag,
                      LocalDate deadline) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = deadline;
        this.start = LocalDate.parse("1970-01-01");
        this.end = LocalDate.parse("1970-01-01");
    }

    /**
     * Constructor of AddCommand class. Used by Event command.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header specify the type the command belongs to, e.g. "add", "delete".
     * @param body specify the key information the command supposed to pass on.
     * @param taskTag specifies the type of task to add, "T" for todo, "D" for deadline, "E" for event.
     * @param start the start date of the event.
     * @param end the end date of the event.
     */
    public AddCommand(boolean isExit, boolean isValid,
                      String header, String body, String taskTag,
                      LocalDate start, LocalDate end) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = LocalDate.parse("1970-01-01");
        this.start = start;
        this.end = end;
    }

    /**
     * Add the new task to the task list,
     * save the new task list to hard disk, and provide feedback to user.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if unexpected runtime issue occurs.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        // I dunno what is the point for ui to be here...
        // to print?
        List<Task> taskList = tasks.getTaskList();
        Task theTask;
        if (this.taskTag.equals("T")) {
            // here body is just taskName
            theTask = new ToDo(this.body);
        } else if (this.taskTag.equals("D")) {
            theTask = new DeadLine(this.body, this.deadline);
        } else {
            theTask = new Event(this.body, this.start, this.end);
        }
        taskList.add(theTask);
        ui.showAdd(theTask, tasks);
        storage.save(tasks);
    }
}
