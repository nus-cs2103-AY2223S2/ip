package duke.command;

import duke.task.Event;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {

    private final Event task;

    public EventCommand(String description, LocalDateTime fromTime, LocalDateTime toTime) {
        this.task = new Event(description, fromTime, toTime);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.add(task);
        String toShow = "Meowww, I've added this task:\nAdded: " + task + "\n";
        toShow += ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);

        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in Event");
        }
    }
}