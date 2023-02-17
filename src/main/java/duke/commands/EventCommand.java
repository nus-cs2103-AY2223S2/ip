package duke.commands;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.task.Event;
import duke.task.Task;

/**
 * Encapsulates event command and its arguments.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND = "event";
    private String[] tokens;

    /**
     * Constructs a new Event Command.
     *
     * @param tokens Arguments of the command.
     */
    public EventCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = tokens[1];
        LocalDateTime start = Parser.parseDateTime(tokens[2]);
        LocalDateTime end = Parser.parseDateTime(tokens[3]);
        Task newTask = new Event(description, start, end);
        tasks.addTask(newTask, storage);
        ui.addToResponseMessage("Got it. I've added this task:");
        ui.addToResponseMessage("  " + newTask);
        ui.addToResponseMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
