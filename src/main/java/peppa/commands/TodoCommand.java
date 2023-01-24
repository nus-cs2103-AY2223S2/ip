package peppa.commands;

import peppa.*;

public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    public static final String ABBREVIATION = "T";
    public static final int DESC_INDEX = 5;
    private String taskDescription;

    public TodoCommand() {}

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Todo(taskDescription);
        taskList.addTask(task);
        Ui.displayAddTaskMessage(task);
        Ui.displayTaskSummary(taskList);
        storage.saveChanges(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
