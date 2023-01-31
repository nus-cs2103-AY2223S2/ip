package command;

import duke.*;

public class MarkCommand extends Command {
    private final String details;

    public MarkCommand(String details) {
        if (details.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a new task cannot be empty.");
        }
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(this.details);
            Task taskToMark = tasks.get(i-1);
            taskToMark.markUnmark(true);
            storage.update(tasks);
            ui.show("Nice! I've marked this task as done:");
            ui.show(String.valueOf(taskToMark));
        } catch (NumberFormatException err) {
            throw new DukeException("☹ OOPS!!! " + this.details + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("☹ OOPS!!! There are less than " + this.details + " tasks.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
