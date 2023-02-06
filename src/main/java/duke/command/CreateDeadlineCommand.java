package duke.command;

import java.io.IOException;

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

        return new Deadline(commandMessageArr[0].substring(9), false,
                commandMessageArr[1].substring(3));
    }
}
