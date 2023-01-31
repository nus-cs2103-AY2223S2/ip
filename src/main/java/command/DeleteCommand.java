package command;

import duke.*;

public class DeleteCommand extends Command{
    private final String details;

    public DeleteCommand(String details) {
        if (details.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a new task cannot be empty.");
        }
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(details);
            Task removedTask = tasks.remove(i - 1);
            storage.update(tasks);
            ui.show("Noted. I've removed this task:");
            ui.show(String.valueOf(removedTask));
            ui.show("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException err) {
            throw new DukeException("☹ OOPS!!! " + details + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("☹ OOPS!!! There are less than " + details + " tasks.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
