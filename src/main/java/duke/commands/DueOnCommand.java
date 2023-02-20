package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.exceptions.DukeInvalidDueOnCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code dueon} command.
 */
public class DueOnCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code DueOnCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public DueOnCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidDueOnCommandException If the {@code dueon} command is invalid.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
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
            return "There are no deadline tasks due on " + dueOnDate;
        }

        List<String> taskStrings = dueOnTasks.stream().map(task -> task.toString()).collect(Collectors.toList());
        return String.join("\n", taskStrings);
    }

    public boolean isByeCommand() {
        return false;
    }
}
