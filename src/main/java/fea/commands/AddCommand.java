package fea.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fea.exceptions.FeaException;
import fea.exceptions.InvalidInputException;
import fea.exceptions.ListException;
import fea.storage.Storage;
import fea.task.Deadline;
import fea.task.Event;
import fea.task.Todo;
import fea.tasklist.TaskList;
import fea.ui.Ui;

/**
 * TaskCommand class that implements the Command interface.
 */
public class AddCommand implements Command {

    private static final String INVALID_DEADLINE_FORMAT = "The deadline must be in the format:"
        + " deadline <task> /by <date>, <task> and <date> cannot be empty.";
    private static final String INVALID_EVENT_FORMAT = "The event must be in the format:"
        + " event <task> /from <date> /to <date>, <task> and <date> cannot be empty.";
    private static final String INVALID_DATE_FORMAT =
            "Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)";
    private static final String END_BEFORE_START = "The start date must be before the end date.";
    private Character taskType;
    private String fullCommand;

    /**
     * Constructor method to initialise AddCommand.
     * @param taskType The type of task.
     * @param fullCommand The string of the full command.
     */
    public AddCommand(Character taskType, String fullCommand) {
        this.taskType = taskType;
        this.fullCommand = fullCommand;
    }

    /**
     * Adds a task to the task list depending on what type of task it is.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string of the task to add.
     * @throws FeaException If there is an exception due to invalid input or the task list is full.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        if (tasks.size() >= 100) {
            throw new ListException("List is full!");
        }
        switch (taskType) {
        case 'T':
            Todo todo = new Todo(this.fullCommand.substring(5));
            return tasks.addTask(todo, storage, ui);
        case 'D':
            try {
                String[] deadline = this.fullCommand.substring(9).split(" /by ");
                if ("".equals(deadline[0].trim())) {
                    throw new InvalidInputException(INVALID_DEADLINE_FORMAT);
                }
                Deadline newDeadline = new Deadline(deadline[0], storeDateTime(deadline[1]));
                return tasks.addTask(newDeadline, storage, ui);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException(INVALID_DEADLINE_FORMAT);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(INVALID_DATE_FORMAT);
            }
        case 'E':
            try {
                String[] event = this.fullCommand.substring(6).split(" /from ");
                if ("".equals(event[0].trim())) {
                    throw new InvalidInputException(INVALID_EVENT_FORMAT);
                }
                String[] eventTime = event[1].split(" /to ");
                LocalDateTime fromDateTime = storeDateTime(eventTime[0]);
                LocalDateTime toDateTime = storeDateTime(eventTime[1]);
                if (fromDateTime.isAfter(toDateTime)) {
                    throw new InvalidInputException(END_BEFORE_START);
                }
                Event newEvent = new Event(event[0], fromDateTime, toDateTime);
                return tasks.addTask(newEvent, storage, ui);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException(INVALID_EVENT_FORMAT);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(INVALID_DATE_FORMAT);
            }
        default:
            throw new InvalidInputException("I'm sorry, but I don't know what that means");
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
