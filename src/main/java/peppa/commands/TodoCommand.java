package peppa.commands;

import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Todo;
import peppa.Ui;

/**
 * Represents a todo command that adds the todo to the tasklist.
 */
public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    public static final String ABBREVIATION = "T";
    public static final int DESC_INDEX = 5;
    private String taskDescription;

    /**
     * Constructs a todo command with the specified task description.
     *
     * @param taskDescription Details about the todo to be added.
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Todo(taskDescription);
        taskList.addTask(task);
        storage.saveChanges(taskList);
        return Ui.getAddTaskMessage(task) + Ui.getTaskSummary(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand) {
            return this.taskDescription.equals(((TodoCommand) obj).taskDescription);
        } else {
            return false;
        }
    }
}
