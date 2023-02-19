package duke.command;

/**
 * Sorts the task list.
 */
public class SortCommand extends Command {
    @Override
    public String execute() {
        return ui.printSortedTasks(taskList.sort());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortCommand;
    }

    @Override
    public String toString() {
        return "SortCommand{}";
    }
}
