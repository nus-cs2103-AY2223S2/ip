package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.task.Task;
import iris.Ui;

public class AddTaskCommand extends Command {
    private Task task;

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
                : "Another task? Phew >:(.\n";
        Ui.output(out + "Added your task: " + this.task);
        Ui.output("You have " + tasks.size() + " tasks.");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddTaskCommand) {
            AddTaskCommand atc = (AddTaskCommand) o;
            return this.task.equals(atc.task);
        } else {
            return false;
        }
    }
}
