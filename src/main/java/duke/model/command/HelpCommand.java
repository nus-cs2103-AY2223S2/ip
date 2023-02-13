package duke.model.command;

import duke.model.task.TaskList;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList list) {
        // TODO: Write help message
        return "HELP_MESSAGE";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
}
