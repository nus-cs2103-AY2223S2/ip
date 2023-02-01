package duke;

import java.io.IOException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event event = new Event(description, from, to);
        tasks.add(event);
        storage.save(tasks.getTasks());
        ui.showAddTask(event, tasks.getTasks());
    }
}
