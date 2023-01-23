package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command that creates a deadline task.
 *
 * @author wz2k
 */
public class CreateDeadlineCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating deadlines.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateDeadlineCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new deadline task and returns if the
     * conversation with the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split("/", 2);
            Task task = new Deadline(commandMessageArr[0].substring(9), false,
                    commandMessageArr[1].substring(3));
            this.taskList.addTask(task);
            this.storage.storeTask(task);
            this.ui.replyTaskAdded(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
