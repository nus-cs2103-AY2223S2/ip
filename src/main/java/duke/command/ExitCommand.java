package duke.command;

import duke.TaskList;

public class ExitCommand extends Command {

    @Override
    String tryExecute(TaskList list) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

}
