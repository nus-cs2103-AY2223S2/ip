package iris.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;
import iris.exception.DateTimeException;
import iris.exception.IrisException;

/**
 * filters deadlines and events that fall in a certain period or on a certain date
 */
public class FilterCommand extends Command {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor to filter events and deadlines on a certain date
     * @param str date to filter events and deadlines on in the following format: {dd-MM-yyyy}
     * @throws DateTimeException when the input is wrong
     */
    public FilterCommand(String str) throws DateTimeException {
        try {
            start = LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay().minusNanos(1);
            end = LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy")).plusDays(1).atStartOfDay();
        } catch (DateTimeParseException e) {
            Ui.output(e.getMessage());
            throw new DateTimeException();
        }
    }

    /**
     * Constructor to filter events and deadlines in a certain period of time
     * @param str1 start date of period in the following format: {dd-MM-yyyy}
     * @param str2 end date of period in the following format: {dd-MM-yyyy}
     * @throws DateTimeException when the input is wrong
     */
    public FilterCommand(String str1, String str2) throws DateTimeException {
        try {
            start = LocalDateTime.parse(str1, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).minusNanos(1);
            end = LocalDateTime.parse(str2, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).plusNanos(1);
        } catch (DateTimeParseException e) {
            Ui.output(e.getMessage());
            throw new DateTimeException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        Ui.output("In this period: " + tasks.dateFilter(start, end).toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FilterCommand) {
            FilterCommand c = (FilterCommand) o;
            return this.start.equals(c.start) && this.end.equals(c.end);
        } else {
            return false;
        }
    }
}
