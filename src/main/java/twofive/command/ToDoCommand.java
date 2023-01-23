package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.ToDo;
import twofive.ui.Ui;

public class ToDoCommand extends Command {
    private String taskDescription;

    public ToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newToDo = new ToDo(taskDescription);
        //Adds new task to list of tasks
        tasks.addTask(newToDo);
        ui.showMesssage("Got it. I've added this task:\n " + newToDo + "\n"
                + "Now you have " + tasks.getTasksNum() + " tasks in the list");
    }
}
