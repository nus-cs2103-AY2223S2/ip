package eevee.command;

import eevee.Parser;
import eevee.Storage;
import eevee.TaskList;
import eevee.Ui;
import eevee.exception.NoTaskToDeleteException;
import eevee.exception.TaskNoContentException;
import eevee.exception.TaskNoNameException;
import eevee.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        Task newDeadline = Parser.makeDeadlineFromCommand(command);
        tasks.addTask(newDeadline, storage);
        return ui.showAddingNewTask(newDeadline, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
