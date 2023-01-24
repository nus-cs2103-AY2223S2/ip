package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command that marks a task.
 *
 * @author wz2k
 */
public class MarkTaskCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for marking a task.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public MarkTaskCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Marks a task and returns if the conversation with
     * the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = commandMessage.split(" ", 2);
            int taskNumber = Integer.parseInt(commandMessageArr[1]);
            Task task = taskList.markTask(taskNumber);

            storage.restructure(taskList);
            ui.replyTaskMarked(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
