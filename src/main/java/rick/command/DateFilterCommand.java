package rick.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import rick.RickUtils;
import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickException;
import rick.exceptions.RickInvalidDateException;


/**
 * Represents the Command that lists all tasks that occur on the given date.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class DateFilterCommand extends Command {
    private final String date;

    /**
     * Constructs the command instance with the given date, in this format:
     * "/on d/m/yy"
     *
     * @param date The provided date
     */
    public DateFilterCommand(String date) {
        this.date = date;
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output
     * @return The UI output by executing this command.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        if (!date.matches("/on (.*)$")) {
            return ui.error(
                    new RickException("Usage: tasks /on {day}/{month}/{year}")
            );
        }
        String cleanedDate = date.replace("/on ", "");
        try {
            LocalDate dt = RickUtils.parseDate(cleanedDate);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");
            return ui.section(
                    "Searching for a list of tasks occurring on " + dt.format(df) + ":",
                    ts.occurOnDate(dt)
            );
        } catch (DateTimeParseException e) {
            return ui.error(
                    new RickInvalidDateException()
            );
        }
    }
}
