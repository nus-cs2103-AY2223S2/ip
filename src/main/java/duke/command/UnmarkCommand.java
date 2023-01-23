package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command {

    private final String NAME = "unmark";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.markTaskAsUndone(this.index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        UnmarkCommand cmd = (UnmarkCommand) obj;
        return this.index == cmd.index;
    }

}
