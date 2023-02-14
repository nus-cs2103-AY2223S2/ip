package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a DeadlineCommand that implements the interface Command.
 */
public class DeadlineCommand implements Command {
    private String deadlineDescription;
    private String deadline;

    /**
     * A constructor for DeadlineCommand.
     * @param deadlineDescription The description of the Deadline task.
     * @param deadline The string representation of the deadline date.
     */
    public DeadlineCommand(String deadlineDescription, String deadline) {
        this.deadlineDescription = deadlineDescription;
        this.deadline = deadline;
    }
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task newTask = new Deadline(deadlineDescription, deadline);
        tasks.addTask(newTask);
        String toDisplay = String.format("Gotcha! I have added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, tasks.getSize());
        ui.displayMessage(toDisplay);
        return toDisplay;
    }
}
