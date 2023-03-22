package cluck.commands;

import java.time.format.DateTimeParseException;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.tasks.Deadline;
import cluck.tasks.Task;

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
     * @param description The description.
     * @param dueDate     The due date.
     */
    public DeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task deadline = new Deadline(description, dueDate);
            taskList.addTask(deadline);
            storage.writetoSave(taskList);
            return Messages.MESSAGE_DEADLINE_ADDED + "\n" + deadline
                    + "\n" + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_INVALID;
        }
    }

}
