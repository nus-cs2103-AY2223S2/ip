package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String content;
    public FindCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = tasks.find(content);
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
