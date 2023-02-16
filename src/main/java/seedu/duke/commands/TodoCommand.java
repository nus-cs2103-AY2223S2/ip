package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Todo;
import seedu.duke.Ui;

public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";
    private String[] tokens;

    public TodoCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = tokens[1];
        Task newTask = new Todo(description);
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
