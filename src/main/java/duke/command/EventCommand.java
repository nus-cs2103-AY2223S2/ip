package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command {

    private final String NAME = "event";
    private String title;
    private String from;
    private String to;

    public EventCommand(String title, String from, String to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new Event(this.title, this.from, this.to);
        taskList.addTask(task);
    }
}
