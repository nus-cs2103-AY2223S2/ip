package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command that creates an event task.
 *
 * @author wz2k
 */
public class CreateEventCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating events.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateEventCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new event task and returns if the
     * conversation with the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split("/", 3);
            Task task = new Event(commandMessageArr[0].substring(6), false,
                    commandMessageArr[1].substring(5).trim(),
                    commandMessageArr[2].substring(3));
            this.taskList.addTask(task);
            this.storage.storeTask(task);
            this.ui.replyTaskAdded(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
