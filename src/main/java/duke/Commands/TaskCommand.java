package duke.Commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Exceptions.DukeException;
import duke.Exceptions.InvalidInputException;
import duke.Exceptions.ListException;
import duke.Storage.Storage;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Todo;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
/**
 * TaskCommand class that implements the Command interface.
 */
public class TaskCommand implements Command {

    private Character taskType;
    private String fullCommand;

    public TaskCommand(Character taskType, String fullCommand) {
        this.taskType = taskType;
        this.fullCommand = fullCommand;
    }

    
    /** 
     * Adds a task to the task list depending on what type of task it is.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException If there is an exception due to invalid input or the task list is full.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() >= 100)
            throw new ListException("List is full!");
        switch (taskType) {
            case 'T':
                Todo todo = new Todo(this.fullCommand.substring(5));
                tasks.addTask(todo, storage, ui);
                break;
            case 'D':
                try {
                    String[] deadline = this.fullCommand.substring(9).split(" /by ");
                    if ("".equals(deadline[0].trim()))
                        throw new InvalidInputException(
                                "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
                    Deadline newDeadline = new Deadline(deadline[0], storeDateTime(deadline[1]));
                    tasks.addTask(newDeadline, storage, ui);
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException(
                            "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException(
                            "OOPS!!! Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)");
                }
                break;
            case 'E':
                try {
                    String[] event = this.fullCommand.substring(6).split(" /from ");
                    if ("".equals(event[0].trim())) {
                        throw new InvalidInputException(
                                "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
                    }
                    String[] eventTime = event[1].split(" /to ");
                    LocalDateTime fromDateTime = storeDateTime(eventTime[0]);
                    LocalDateTime toDateTime = storeDateTime(eventTime[1]);
                    if (fromDateTime.isAfter(toDateTime)) {
                        throw new InvalidInputException("OOPS!!! The start date must be before the end date.");
                    }
                    Event newEvent = new Event(event[0], fromDateTime, toDateTime);
                    tasks.addTask(newEvent, storage, ui);
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException(
                            "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException(
                            "OOPS!!! Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)");
                }
                break;
            default:
                throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private LocalDateTime storeDateTime(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}
