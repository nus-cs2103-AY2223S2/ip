package duke.command;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.UI.UI;
import duke.UI.Parser;

/**
 * The deadline command.
 * Extends from AddCommand.
 * Handles the adding of a deadline task.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND = "deadline";
    private String[] index;

    /**
     * The constructor of the deadline command.
     * @param index The arguments of the command.
     */
    public DeadlineCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        String taskDescription = index[1];
        LocalDateTime by = Parser.parseDateTime(index[2]);
        Task taskToAdd = new Deadline(taskDescription, by);
        taskList.addTask(taskToAdd, storage);
        ui.printResponse("Got it. I have added this task: \n" + taskToAdd);
        ui.printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
