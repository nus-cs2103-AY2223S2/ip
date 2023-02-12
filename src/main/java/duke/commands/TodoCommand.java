package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    private String input;
    private TaskList listOfTasks;
    public TodoCommand(String input, TaskList listOfTasks) {
        this.input = input;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String handleCommand() {
        String[] inputArgs = input.split(" ", 2);
        Task currentTask= new Todo(inputArgs[1]);
        this.listOfTasks.add(currentTask);
        return Ui.showAdd(this.listOfTasks, currentTask.toString());
    }
}
