package pix.commands;

import java.time.LocalDate;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * ListDate class which displays list of tasks that occurs on / due by a date.
 */
public class ListDate extends Command {
    /** Date to search for. */
    private final LocalDate date;

    /**
     * Constructs a new ListDate command.
     *
     * @param date Date to search for.
     */
    public ListDate(LocalDate date) {
        this.date = date;
    }

    public void execute(MyData data, Ui ui) {
        ui.listDate(data, this.date);
    }
}
