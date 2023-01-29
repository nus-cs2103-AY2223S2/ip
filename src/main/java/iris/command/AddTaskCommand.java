package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;
import iris.task.Task;

/**
 * Command to add a task to the task list
 */
public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
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
    /**
     * {@inheritDoc}
     */
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
