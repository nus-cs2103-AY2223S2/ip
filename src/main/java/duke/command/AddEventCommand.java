package duke.command;

import duke.*;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Date;
public class AddEventCommand extends Command {
    private String description;
    private String eventAt;
    private Date eventDate;

    public AddEventCommand(String description, String deadlineBy) {
        this.description = description;
        this.eventAt = deadlineBy;
    }

    public AddEventCommand(String description, Date deadlineDate) {
        this.description = description;
        this.eventDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        if (eventDate == null) {
            event = new Event(description, eventAt);
        } else {
            event = new Event(description, eventDate);
        }
        tasks.add(event);
        Ui.ShowAddMessage(event, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}