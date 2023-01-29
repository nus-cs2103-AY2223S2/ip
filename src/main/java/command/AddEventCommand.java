package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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
        Task event = tasks.addEvent(description, from, to);
        ui.formResponse("New event task added: " + event);
        storage.save(tasks.getList());
    }
}
