package duke.command;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.UI.UI;
import duke.UI.Parser;

/**
 * The event command.
 * Extends from AddCommand.
 * Handles the adding of an event task.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND = "event";
    private String[] index;

    /**
     * The constructor for the event command.
     * @param index The arguments of the command.
     */
    public EventCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        String taskDescription = index[1];
        LocalDateTime from = Parser.parseDateTime(index[2]);
        LocalDateTime to = Parser.parseDateTime(index[3]);
        Task taskToAdd = new Event(taskDescription, from, to);
        taskList.addTask(taskToAdd, storage);
        ui.printResponse("Alright. I have added this task: \n" + taskToAdd);
        ui.printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
