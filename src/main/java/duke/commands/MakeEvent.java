package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

public class MakeEvent extends Command {
    private String description;
    private String from;
    private String to;
    private TaskList tasklist;

    public MakeEvent(String description, String from, String to, TaskList tasklist) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = new Event(description, from, to);
        tasklist.add(t);
        return "Added this new Event: \n" + t;
    }
}
