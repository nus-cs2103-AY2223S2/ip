package controllers;

import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

/**
 * Represents the DateCommand.
 * The date command can be used to determine active tasks on a certain date.
 */
public class DateCommand extends Command {
    private final String args;
    private final Pattern DATE_FORMAT =
            Pattern.compile("(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");

    /**
     * Initializes the Date Command.
     *
     * @param args The parsed arguments.
     */
    public DateCommand(String args) {
        super(CommandType.DATE);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * This method parses the command to verify and filter tasks with the date specified.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher matcher = VALID_DATE.matcher(args.strip());
        if (matcher.find()) {
            store.filter(task -> task.activeOn(LocalDate.parse(args.split(" ")[1])),
                    "There are no active tasks on this date!");
        } else {
            String DATE_FORMAT_ERROR = INVALID_FORMAT_ERROR + " " + "Please follow: date [yyyy-mm-dd].";
            throw new DukeException(DATE_FORMAT_ERROR);
        }
    }
}
