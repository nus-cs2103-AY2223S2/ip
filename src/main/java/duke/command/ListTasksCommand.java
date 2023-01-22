package duke.command;

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
