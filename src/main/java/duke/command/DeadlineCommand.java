package duke.command;

import duke.exception.DukeException;
import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class for DeadlineCommand.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor for DeadlineCommand.
     * @param userInput User input.
     */
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Getter method to extract out deadline name from user input.
     * @param input User input.
     * @return Deadline name in String format.
     */
    public String getDeadlineName(String input) throws DukeException {
        String deadlineName = "";
        try {
            deadlineName = input.split(" /by ")[0].substring(9);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException ("Oops! Please enter a valid deadline task format.\n");
        }
        return deadlineName;
    }

    /**
     * Extracts deadline time from user input.
     * @param input User input.
     * @return Deadline time in LocalDateTime format.
     */
    public LocalDateTime getDeadlineTime(String input) throws DukeException {
        LocalDateTime deadlineTime = null;
        try {
            deadlineTime = Parser.dateFormatter(input.split(" /by ")[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
        }
        return  deadlineTime;
    }

    /**
     * Translates user input to a Deadline task.
     * @param input User input
     * @return Deadline task
     */
    public Task translateInput(String input) throws DukeException {
        return new Deadline(getDeadlineName(input), getDeadlineTime(input));
    }

    /**
     * Executes user input and adds Deadline task to current TaskList.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that Deadline task has been added.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws DukeException {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Task newTask = translateInput(userInput);
        tasks.addTask(newTask);
        Storage.saveTasksToTaskLog(tasks);
        return Ui.addTaskMessage(newTask, taskCount, taskWord);
    }
}
