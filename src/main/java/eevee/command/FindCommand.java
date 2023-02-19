package eevee.command;

import eevee.Parser;
import eevee.Storage;
import eevee.TaskList;
import eevee.Ui;
import eevee.exception.NoTaskToDeleteException;
import eevee.exception.TaskNoContentException;
import eevee.exception.TaskNoNameException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class FindCommand extends Command {

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        String searchWord = Parser.getSearchWord(command);
        TaskList tasksFound = tasks.makeTaskFinder(searchWord);
        return ui.showFindingTask(tasksFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
