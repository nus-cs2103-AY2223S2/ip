package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class MarkCommand extends Command {

    private final String NAME = "mark";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.markTaskAsDone(this.index);
    }
}
