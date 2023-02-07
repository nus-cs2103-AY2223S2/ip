package duke.command;

import duke.TaskList;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute(TaskList list) {
        return new CommandResult("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

}
