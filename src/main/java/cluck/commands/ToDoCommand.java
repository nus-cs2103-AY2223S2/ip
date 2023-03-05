package cluck.commands;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

/**
 * To do command when executed creates a to-do task and adds it to the task list.
 * The command returns a success message, the added to do,
 * and the new number of tasks in the task list.
 * If execution is unsuccessful, the command returns the error message as to why execution failed.
 */
public class ToDoCommand implements Command {
    private final String description;

    /**
     * Instantiates a new To do command.
     *
     * @param description the description
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task task = new ToDo(description);
        taskList.addTask(task);
        storage.writetoSave(taskList);
        return Messages.MESSAGE_TODO_ADDED + "\n" + task
                + "\n" + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
    }
}
