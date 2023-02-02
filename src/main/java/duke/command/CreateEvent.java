package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class CreateEvent extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    public CreateEvent(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Event(description, start, end, false));
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
