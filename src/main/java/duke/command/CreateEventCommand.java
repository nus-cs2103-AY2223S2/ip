package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that creates an event task.
 *
 * @author wz2k
 */
public class CreateEventCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating events.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateEventCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new event task and returns the reply
     * for event creation.
     *
     * @return Taskbot reply to the event creation.
     */
    @Override
    public String execute() {
        try {
            String startOfReply = "The following task has been added:\n";

            Task task = createEvent();
            taskList.addTask(task);
            storage.storeTask(task);

            return startOfReply + "  " + task;
        } catch (IOException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Creates an event.
     *
     * @return Event created.
     */
    public Event createEvent() {
        String[] commandMessageArr = commandMessage.split("/", 3);
        assert commandMessageArr.length == 3 : "event command should split into 3";
        return new Event(commandMessageArr[0].substring(6), false,
                commandMessageArr[1].substring(5).trim(),
                commandMessageArr[2].substring(3));
    }
}
