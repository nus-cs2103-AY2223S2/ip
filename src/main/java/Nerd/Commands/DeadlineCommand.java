package Nerd.Commands;

import Nerd.entities.Deadline;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.DeadlineCommand of the Chat bot.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructor for a deadline command.
     *
     * @param description The description of the task.
     * @param by          The date of the deadline.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a deadline Task.
     *
     * @param list The TaskList object that stores tasks.
     * @param ui   User interface of the Chat bot.
     * @return A string output for adding a deadline task.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Deadline deadline = new Deadline(description, by);
        list.addTask(deadline);
        String response = ui.printDeadlineResponse(deadline.toString(), list.getSize());
        return response;
    }
}
