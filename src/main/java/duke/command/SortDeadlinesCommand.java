package duke.command;

import duke.util.container.TaskList;

public class SortDeadlinesCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return "Here are the deadlines in your list:\n" + list.listSortedDeadlineTasks();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortDeadlinesCommand;
    }
}
