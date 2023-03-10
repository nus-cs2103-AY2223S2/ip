package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates deadline command and its arguments.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND = "deadline";
    private String[] tokens;

    /**
     * Constructs a new Deadline Command.
     *
     * @param tokens Arguments of the command.
     */
    public DeadlineCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = tokens[1];
        LocalDateTime by = Parser.parseDateTime(tokens[2]);
        Task newTask = new Deadline(description, by);
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
