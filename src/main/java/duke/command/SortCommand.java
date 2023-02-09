package duke.command;

import duke.model.TaskList;

public class SortCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return "Here are the deadlines in your list:\n" + list.listSortedDeadlineTasks();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortCommand;
    }
}
