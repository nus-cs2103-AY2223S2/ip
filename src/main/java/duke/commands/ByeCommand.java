package duke.commands;

import duke.database.Database;
import duke.exception.databaseexceptions.DatabaseNotUpdatingException;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
        this.isActive = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws DukeException {
        try {
            database.update(taskList.getTasks());
            ui.response(FRAME +
                    "     Bye. Hope to see you again soon!\n" +
                    FRAME);
        } catch (IOException e) {
            throw new DatabaseNotUpdatingException();
        }
    }
}
