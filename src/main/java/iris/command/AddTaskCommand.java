package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.task.*;
import iris.TaskStore;

public class AddTaskCommand extends Command {
    Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        tasks.add(this.task);
        taskStore.addTask(this.task);

        String out = tasks.size() < 4
                ? ""
                : tasks.size() > 10
                ? "What?!! You're going to dieee!\n"
                : "Another task? Phew >:(.\n" ;

        Ui.output(out + "Added your task: "  + this.task);
        Ui.output("You have " + tasks.size() + " tasks.");
    }
}
