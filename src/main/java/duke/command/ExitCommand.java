package duke.command;

import duke.storage.TaskList;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Bye";
    }
}
