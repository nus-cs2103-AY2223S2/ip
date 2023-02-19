package duke.command;

/**
 * Lists all tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    @Override
    public String execute() {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        return ui.printTaskList(taskList);
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
