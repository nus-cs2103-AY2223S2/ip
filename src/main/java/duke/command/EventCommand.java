package duke.command;

import duke.taskstorage.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class for EventCommand.
 */
public class EventCommand extends Command{

    /**
     * Constructor for EventCommand.
     * @param userInput
     */
    public EventCommand(String userInput) {
        super(userInput);
    }

    /**
     * Extracts event name from user input.
     * @param input User input.
     * @return Event name in String format.
     */
    public String getEventName(String input) {
        String name = "";
        try {
            name = input.split(" /from ")[0].substring(6);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! Please enter a valid deadline task format.\n");
        }
        return name;
    }

    /**
     * Getter method to extract event start time from user input.
     * @param input User input.
     * @return Event start time in LocalDateTime format.
     */
    public LocalDateTime getEventStartTime(String input) {
        LocalDateTime startTime = null;
        try {
            String time = input.split(" /from ")[1].split(" /to ")[0];
            startTime = Parser.dateFormatter(time);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! Please enter a valid deadline task format.\n");
        } catch (DateTimeParseException e) {
            System.out.println("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
        }
        return startTime;
    }

    /**
     * Getter method to extract event end time from user input.
     * @param input User input.
     * @return Event end time in LocalDateTime format.
     */
    public LocalDateTime getEventEndTime(String input) {
        LocalDateTime endTime = null;
        try {
            String time = input.split(" /to ")[1];
            endTime = Parser.dateFormatter(time);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! Please enter a valid deadline task format.\n");
        } catch (DateTimeParseException e) {
            System.out.println("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
        }
        return endTime;
    }

    /**
     * Translates user input to Event task.
     * @param input User input.
     * @return Event task
     */
    public Task translateInput(String input) {
        return new Event(getEventName(input), getEventStartTime(input), getEventEndTime(input));
    }

    /**
     * Executes user input and adds Event task to current TaskList.
     * @param tasks Current TaskList.
     * @return Message to inform user that Event task has been added.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Task newTask = translateInput(userInput);
        tasks.addTask(newTask);
        return Ui.addTaskMessage(newTask, taskCount, taskWord);
    }
}
