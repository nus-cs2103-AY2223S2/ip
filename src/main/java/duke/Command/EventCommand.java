package duke.Command;

import duke.Exception.InvalidArgumentsException;

import duke.Task.Event;

import duke.TaskList;
import duke.UI;
import duke.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime until;

    public EventCommand(String name, String from, String until) throws InvalidArgumentsException {
        this.name = name;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(from.trim(), formatter);
            this.until = LocalDateTime.parse(until.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Event ev = new Event(name, from, until);
        ui.showConfirmation(tasks.addTask(ev));
        storage.saveToFile(tasks.getTasks());
    }
}
