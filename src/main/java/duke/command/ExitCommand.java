package duke.command;

import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList list) {
        UI.echo("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

}
