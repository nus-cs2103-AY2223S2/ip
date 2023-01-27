package command;

import shigure.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

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
            if ((from == null || tasks.get(i).afterDate(from))
                    && (to == null || tasks.get(i).beforeDate(to))) {
                ui.print(Integer.toString(i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
