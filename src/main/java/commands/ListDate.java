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

    public void execute(MyData data) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            Task task = data.getData(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getDate().equals(this.date)) {
                    System.out.printf("    %d. %s%n", i + 1, data.getData(i));
                }
            }
            if (task instanceof Event) {
                Event taskEvent = (Event) task;
                if (this.date.isAfter(taskEvent.getFromDate()) && this.date.isBefore(taskEvent.getToDate())) {
                    System.out.printf("    %d. %s%n", i + 1, data.getData(i));
                }
            }
        }
        System.out.print(Ui.line());
    }
}
