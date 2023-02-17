package duke.model.command;

import java.util.Optional;

import duke.model.task.TaskList;

public class HelpCommand extends Command {

    private Optional<Keyword> keyword;

    public HelpCommand(Optional<Keyword> keyword) {
        this.keyword = keyword;
    }

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
