package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class AddEventCommand extends Command {
    private String description;
    private LocalDate from;
    private LocalDate to;
    public AddEventCommand(String eventDescription, LocalDate from, LocalDate to) {
        this.description = eventDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        ui.formResponse("New event task added: " + event);
        storage.save(tasks.getList());
    }
}
