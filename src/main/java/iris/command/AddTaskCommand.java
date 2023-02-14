package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
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
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        tasks.add(this.task);
        taskStore.addTask(this.task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        String reaction = "";
        if (tasks.size() > 10) {
            reaction = "What?!! You're going to dieee!\n";
        } else if (tasks.size() > 4) {
            reaction = "Another task? Phew >:(.\n";
        }
        return reaction + "Added your task: " + this.task + "\n"
                + "You have " + tasks.size() + " tasks.";
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
