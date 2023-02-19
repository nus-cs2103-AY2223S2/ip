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

public class AddEventCommand extends Command {

    public AddEventCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        Task newEvent = Parser.makeEventFromCommand(command);
        boolean canAddEvent = tasks.canAddTask(newEvent, storage);
        return canAddEvent ? ui.showAddingNewTask(newEvent, tasks) : ui.showFailAddingNewTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
