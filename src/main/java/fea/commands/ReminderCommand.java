package fea.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;

/**
 * ReminderCommand class that implements the Command interface.
 */
public class ReminderCommand implements Command {

    private static final String INVALID_TASK_NUMBER = "Please enter a valid task number!";
    private static final String INVALID_REMINDER_FORMAT = "The reminder must be in the format:"
            + " reminder <task number> /at <time>";
    private static final String INVALID_DATE_FORMAT =
        "Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)";
    private String fullCommand;

    /**
     * Constructor class to initialise ReminderCommand.
     * @param fullCommand The string of the full command.
     */
    public ReminderCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Sets a reminder for a task.
     *
     * @param tasks   The current task list.
     * @param ui      The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string of the task to be marked.
     * @throws FeaException If there is an exception saving to the data file or
     *                      invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        try {
            String[] reminder = fullCommand.substring(9).split(" /at ");
            int taskNum = Integer.parseInt(reminder[0]);
            LocalDateTime reminderTime = storeDateTime(reminder[1]);
            if (taskNum > tasks.size() || taskNum < 1) {
                throw new FeaException(INVALID_TASK_NUMBER);
            }
            return tasks.setReminder(taskNum, storage, ui, reminderTime);
        } catch (DateTimeParseException e) {
            throw new FeaException(INVALID_DATE_FORMAT);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new FeaException(INVALID_REMINDER_FORMAT);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static LocalDateTime storeDateTime(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}
