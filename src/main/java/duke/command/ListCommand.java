package duke.command;

import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public CommandResult execute(TaskList list) {
        return new CommandResult("Here are the tasks in your list:\n" + list.listAllTasks());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
