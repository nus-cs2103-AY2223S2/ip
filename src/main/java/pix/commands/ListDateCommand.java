package pix.commands;

import java.time.LocalDate;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * ListDateCommand class which displays list of tasks that occurs on or due by a date.
 */
public class ListDateCommand extends Command {
    /** Date to search for. */
    private final LocalDate dateToSearch;

    /**
     * Constructs a new ListDateCommand.
     *
     * @param dateToSearch Date to search for.
     */
    public ListDateCommand(LocalDate dateToSearch) {
        this.dateToSearch = dateToSearch;
    }

    public String execute(MyData data, Ui ui) {
        return ui.listDate(data, this.dateToSearch);
    }
}
