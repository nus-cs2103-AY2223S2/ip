package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.regex.PatternSyntaxException;

/**
 * Represents the command to add the tasks
 */
public class AddTaskCommand extends Command {
    private final String typeOfTask;

    /**
     * Returns an AddTaskCommand with the command stored
     *
     * @param command String of the command to be stored
     */
    public AddTaskCommand(String command) {
        super(command);
        this.typeOfTask = command.split(" ")[0];
    }
    /**
     * Creates the correct type of task and adds the correct task to TaskList
     * Display the output via Ui showing the new task created
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the task is in the wrong format
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            Task task = makeTask();
            tasks.add(task);
            ui.print(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, tasks.size()));
            storage.saveList(tasks);
        } catch (RuntimeException wrongFormat) {
            throw new DukeException("Please enter the command in the correct format:\n" + correctFormat());
        }
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try{
            Task task = makeTask();
            tasks.add(task);
            storage.saveList(tasks);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, tasks.size());
        } catch (RuntimeException wrongFormat) {
            throw new DukeException("Please enter the command in the correct format:\n" + correctFormat());
        }
    }

    /**
     * Returns the correct Task depending on the command that was entered
     *
     * @return task Correct version of Task (ToDo, Event, Deadline)
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     */
    private Task makeTask() throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        Task task = new Task(this.command);
        String cmd = this.command.replace(typeOfTask + " ", "");
        switch (typeOfTask) {
        case "todo":
            task = new ToDo(cmd);
            break;
        case "deadline":
            task = new Deadline(cmd);
            break;
        case "event":
            task = new Event(cmd);
            break;
        }
        return task;
    }

    /**
     * Returns the correct string format for the type of task that is stored in the AddTaskCommand
     *
     * @return String format
     */
    private String correctFormat() {
        String format = "THE TASK";
        switch(this.typeOfTask) {
        case "todo":
            format = "todo [TASK]";
            break;
        case "deadline":
            format = "deadline [TASK] /by yyyy-mm-ddThh:mm:ss";
            break;
        case "event":
            format = "event [TASK] /from TIME /to TIME";
        }
        return format;
    }
}
