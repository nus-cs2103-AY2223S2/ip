package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

import java.time.LocalDate;

public class ListDate extends Command {
    LocalDate date;

    public ListDate(LocalDate date ) {
        this.date = date;
    }

    public void execute(MyData data, Ui ui) {
        ui.listDate(data, this.date);
    }
}
