package eevee.command;

import eevee.Storage;
import eevee.TaskList;
import eevee.Ui;
import eevee.exception.NoTaskToDeleteException;
import eevee.exception.TaskNoContentException;
import eevee.exception.TaskNoNameException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        return ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
