package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.UI.UI;

public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";
    private String[] index;

    public TodoCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        String taskDescription = index[1];
        Task taskToAdd = new Todo(taskDescription);
        taskList.addTask(taskToAdd, storage);
        ui.printResponse("Understood. I have added this task: \n" + taskToAdd);
        ui.printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
