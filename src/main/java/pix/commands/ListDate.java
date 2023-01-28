package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

import java.time.LocalDate;

public class ListDate extends Command {
    /** Date to search for. */
    LocalDate date;

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
