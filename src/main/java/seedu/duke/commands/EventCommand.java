package seedu.duke.commands;

import java.time.LocalDateTime;

import seedu.duke.Event;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class EventCommand extends AddCommand {
    public static final String COMMAND = "event";
    private String[] tokens;

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
        ui.indentedPrintln("Got it. I've added this task:");
        ui.indentedPrintln("  " + newTask);
        ui.indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
