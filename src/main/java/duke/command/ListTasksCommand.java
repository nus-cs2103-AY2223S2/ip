package duke.command;

/**
 * The command to list all tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    @Override
    public void execute() {
        ui.printTaskList(taskList);
    }

    @Override
    public String toString() {
        return "ListTasksCommand{}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListTasksCommand;
    }

}
