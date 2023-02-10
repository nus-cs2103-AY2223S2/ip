package duke.command;

import duke.util.container.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

}
