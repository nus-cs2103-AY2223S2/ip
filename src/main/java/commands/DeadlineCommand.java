package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

import tasks.Deadline;

import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IllegalArgumentException {
        assert !input.isEmpty() : "Input to Deadline Command can't be empty";
        try {
            Deadline deadline = new Deadline(getDescription(), getDateTime());
            taskList.getTaskList().add(deadline);
            storage.store(taskList);
            return ui.newDeadlineMessage(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the description of the deadline.
     *
     * @return The description of the deadline.
     */
    public String getDescription() throws IllegalArgumentException {
        return input.split(" ", 2)[1].split(" /by ", 2)[0];
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return The date and time of the deadline.
     */
    public String getDateTime() {
        return input.split(" /by ", 2)[1];
    }
}
