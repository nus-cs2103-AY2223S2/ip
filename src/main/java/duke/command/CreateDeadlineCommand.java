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
            String[] commandMessageArr = commandMessage.split("/", 2);
            assert commandMessageArr.length == 2 : "deadline command should split into 2";
            Task task = new Deadline(commandMessageArr[0].substring(9), false,
                    commandMessageArr[1].substring(3));

            taskList.addTask(task);
            storage.storeTask(task);
            return "The following task has been added:\n" + "  " + task;
        } catch (IOException exception) {
            return "An error has occurred!\n" + exception.getMessage();
        }
    }
}
