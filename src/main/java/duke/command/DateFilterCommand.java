package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDateException;
import duke.DukeUtils;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The command to filter the task list by date.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DateFilterCommand extends Command {
    private final String date;
    public DateFilterCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList ts, Ui ui) {
        if (!date.matches("/on (.*)$")) {
            ui.error(
                    new DukeException("Usage: tasks /on {day}/{month}/{year}")
            );
            return;
        }
        String cleanedDate = date.replace("/on ", "");
        try {
            LocalDate dt = DukeUtils.parseDate(cleanedDate);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");
            ui.section(
                    "Searching for a list of tasks occurring on " + dt.format(df) + ":\n" +
                            ts.occurOnDate(dt)
            );
        } catch (DateTimeParseException e) {
            ui.error(
                    new DukeInvalidDateException()
            );
        }
    }
}
