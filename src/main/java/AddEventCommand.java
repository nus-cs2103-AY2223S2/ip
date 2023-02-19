import java.util.Date;
public class AddEventCommand extends Command {
    private String description;
    private String eventAt;
    private Date eventDate;

    AddEventCommand(String description, String deadlineBy) {
        this.description = description;
        this.eventAt = deadlineBy;
    }

    AddEventCommand(String description, Date deadlineDate) {
        this.description = description;
        this.eventDate = deadlineDate;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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