package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;
import cluck.tasks.Deadline;
import cluck.tasks.Task;

import java.time.format.DateTimeParseException;

/**
 * Deadline command when executed successfully creates a deadline and adds it to the task list.
 * The command returns a success message, the added deadline,
 * and the new number of tasks in the task list.
 * If execution is unsuccessful, the command returns the error message as to why execution failed.
 */
public class DeadlineCommand implements Command {
    private final String description;
    private final String dueDate;

    /**
     * Instantiates a new Deadline command.
     *
     * @param description the description
     * @param dueDate     the due date
     */
    public DeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task deadline = new Deadline(description, dueDate);
            taskList.addTask(deadline);
            return Messages.MESSAGE_DEADLINE_ADDED + "\n" + deadline +
                    "\n" + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_INVALID;
        }
    }

}
