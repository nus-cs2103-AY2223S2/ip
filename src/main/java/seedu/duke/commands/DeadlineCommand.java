package seedu.duke.commands;

import java.time.LocalDateTime;

import seedu.duke.Deadline;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND = "deadline";
    private String[] tokens;

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
