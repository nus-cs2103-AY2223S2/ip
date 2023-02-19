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

public class MarkCommand extends Command {

    public MarkCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException,
            DateTimeParseException, TaskNoContentException, NoTaskToDeleteException, TaskNoNameException {
        Task markedTask = tasks.markTaskAsDone(Parser.getIndexFromCommand(command), storage);
        assert markedTask.getTaskStatus() : "Task is wrongly marked.";
        return ui.showMarkingTaskDone(markedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
