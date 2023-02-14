package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that creates a deadline task.
 *
 * @author wz2k
 */
public class CreateDeadlineCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating deadlines.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateDeadlineCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new deadline task and returns the reply
     * for deadline creation.
     *
     * @return Taskbot reply to the deadline creation.
     */
    @Override
    public String execute() {
        try {
            String startOfReply = "The following task has been added:\n";

            Task task = createDeadline();
            taskList.addTask(task);
            storage.storeTask(task);

            return startOfReply + "  " + task;
        } catch (IOException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Creates a deadline.
     *
     * @return Deadline created.
     */
    public Deadline createDeadline() {
        String[] commandMessageArr = commandMessage.split("/", 2);
        assert commandMessageArr.length == 2 : "deadline command should split into 2";

        return new Deadline(commandMessageArr[0].substring(8).trim(), false,
                commandMessageArr[1].substring(2).trim());
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String args = commandMessage.substring(8).trim();
        if (args.length() == 0) {
            String emptyArgumentsMessage = "deadline arguments cannot be empty";
            throw new DukeException(emptyArgumentsMessage);
        }

        String[] argsArr = args.split("/by", 2);
        if (argsArr.length != 2 || argsArr[0].length() == 0 || argsArr[1].length() == 0) {
            String invalidFormatMessage = "deadline format is incorrect";
            throw new DukeException(invalidFormatMessage);
        }

        if (argsArr[0].trim().length() == 0) {
            String emptyDescriptionMessage = "deadline description cannot be empty";
            throw new DukeException(emptyDescriptionMessage);
        }

        try {
            LocalDate.parse(argsArr[1].trim());
        } catch (DateTimeParseException dtpe) {
            String wrongDateFormatMessage = "deadline date should be of the format YYYY-MM-DD";
            throw new DukeException(wrongDateFormatMessage);
        }
    }
}
