package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;
import cluck.tasks.Deadline;
import cluck.tasks.Task;

import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private final String description;
    private final String dueDate;

    public DeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task deadline = new Deadline(description, dueDate);
            taskList.addTask(deadline);
            return Messages.MESSAGE_DEADLINE_ADDED + "\n" + deadline
                    + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_INVALID;
        }
    }

}
