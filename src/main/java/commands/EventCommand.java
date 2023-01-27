package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import views.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventCommand(String name, String startDate, String endDate) throws DukeException {
        this.name = name;
        try {
            this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            if (this.startDate.isAfter(this.endDate)) {
                throw new DukeException("The starting date of this event is after its ending date!");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date format in \"dd/mm/yyyy!\"");
        }
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        Event event = new Event(this.name, this.startDate, this.endDate);
        tasks.addTask(event);
        this.commandStatus = "Added event: " + event + "\n"
                + "You now have " + tasks.size() + "task(s) in your list";
        ui.printCommandOutput(this);
    }
}
