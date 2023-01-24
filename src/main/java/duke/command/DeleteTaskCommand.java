package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command that deletes a task.
 *
 * @author wz2k
 */
public class DeleteTaskCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command that deletes a task.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public DeleteTaskCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Deletes a task from the chatbot's task list and storage and returns if the
     * conversation with the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split(" ", 2);
            int taskNumber = Integer.parseInt(commandMessageArr[1]);
            Task task = this.taskList.deleteTask(taskNumber);
            this.storage.restructure(this.taskList);
            this.ui.replyTaskDeleted(task);
        } catch (IOException | DukeException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
