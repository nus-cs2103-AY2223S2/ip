package duke.commands;

import duke.exceptions.DukeInvalidDueOnCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DueOnCommand extends Command {

    private final String[] tokens;

    public DueOnCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidDueOnCommandException {

        if (tokens.length != 2) {
            throw new DukeInvalidDueOnCommandException();
        }

        LocalDate dueOnDate;

        try {
            dueOnDate = LocalDate.parse(tokens[1], DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDueOnCommandException();
        }

        ArrayList<Task> dueOnTasks = taskList.getAllTasksThatAreDueOn(dueOnDate);

        if (dueOnTasks.size() == 0) {
            ui.showMessage("There are no deadline tasks due on " + dueOnDate);
            return;
        }

        for (Task task : dueOnTasks) {
            ui.showMessage(task.toString());
        }
    }

    public boolean isByeCommand() {
        return false;
    }
}