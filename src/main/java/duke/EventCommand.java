package duke;

import java.io.IOException;

public class EventCommand extends Command {
    private Event event;

    /**
     * Constructor for an Event command.
     *
     * @param description Description of the event.
     * @param from Start date and time of the event in yyyy-MM-dd HH:mm format.
     * @param to End date and time of the event in yyyy-MM-dd HH:mm format.
     */
    public EventCommand(String description, String from, String to) {
        event = new Event(description, from, to);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        tasks.add(event);
        storage.save(tasks.getTasks());
        return response.showAddTask(event, tasks.getTasks());
    }
}
