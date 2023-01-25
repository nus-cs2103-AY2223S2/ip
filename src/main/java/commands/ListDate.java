package commands;

import data.MyData;
import tasks.Deadline;
import tasks.Task;
import tasks.Event;
import ui.Ui;

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
