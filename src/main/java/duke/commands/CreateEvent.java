package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class CreateEvent extends Command {
    private String description;
    private String from;
    private String to;

    public CreateEvent(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        Event event = new Event(list.nextId(), description, from, to);
        list.add(event);
        ui.showMessage("Got it. I've added this task: " + event);
    }
}
