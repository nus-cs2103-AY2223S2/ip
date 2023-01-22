package controllers;

import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

/**
 * Represents the DateCommand.
 * The date command can be used to determine active tasks on a certain date.
 */
public class DateCommand extends Command {
    private final String args;

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
            String DATE_FORMAT_ERROR = "Invalid Date Format. Please follow: date [yyyy-mm-dd].";
            throw new DukeException(DATE_FORMAT_ERROR);
        }
    }
}
