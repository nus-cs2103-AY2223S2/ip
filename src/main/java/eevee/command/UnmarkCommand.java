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

public class UnmarkCommand extends Command {

    public UnmarkCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        Task unmarkedTask = tasks.markTaskAsUndone(Parser.getIndexFromCommand(command), storage);
        assert !unmarkedTask.getTaskStatus() : "Task is wrongly marked.";
        return ui.showMarkingTaskUndone(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
