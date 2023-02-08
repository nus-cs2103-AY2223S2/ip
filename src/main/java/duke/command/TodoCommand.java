package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class TodoCommand extends Command {
    private String content;

    public TodoCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        content = content.trim();
        String res = tasks.add(content);
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
