package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

import java.time.LocalDateTime;

public class List implements Command {
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    public List() {

    }

    public List(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.print("caught in 4k:");
        for (int i = 0; i < tasks.size(); i++) {
            if ((from == null || tasks.get(i).isAfterDate(from))
                    && (to == null || tasks.get(i).isBeforeDate(to))) {
                ui.print(Integer.toString(i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
