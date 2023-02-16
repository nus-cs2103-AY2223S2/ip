package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;
import jeno.parser.Parser;
import jeno.task.Event;
import jeno.ui.Ui;

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
    public String getEventName(String input) throws JenoException {
        String name = "";
        try {
            name = input.split(" /from ")[0].substring(6);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid event task format.");
        }
        return name;
    }

    /**
     * Getter method to extract event start time from user input.
     * @param input User input.
     * @return Event start time in LocalDateTime format.
     */
    public LocalDateTime getEventStartTime(String input) throws JenoException {
        LocalDateTime startTime = null;
        try {
            String time = input.split(" /from ")[1].split(" /to ")[0];
            startTime = Parser.dateFormatter(time);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid deadline task format.\n");
        } catch (DateTimeParseException e) {
            throw new JenoException("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
        }
        return startTime;
    }

    /**
     * Getter method to extract event end time from user input.
     * @param input User input.
     * @return Event end time in LocalDateTime format.
     */
    public LocalDateTime getEventEndTime(String input) throws JenoException {
        LocalDateTime endTime = null;
        try {
            String time = input.split(" /to ")[1];
            endTime = Parser.dateFormatter(time);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid event task format.\n");
        } catch (DateTimeParseException e) {
            throw new JenoException("Oops! Please enter start time and end time according to 'DD/MM/YYYY HH:mm' format.\n");
        }
        return endTime;
    }

    /**
     * Translates user input to Event task.
     * @param input User input.
     * @return Event task
     */
    public Event translateInput(String input) throws JenoException {
        return new Event(getEventName(input), getEventStartTime(input), getEventEndTime(input));
    }

    /**
     * Executes user input and adds Event task to current TaskList.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that Event task has been added.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws JenoException {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Event newTask = translateInput(userInput);
        tasks.addTask(newTask);
        Storage.saveTasksToTaskLog(tasks);
        return Ui.addTaskMessage(newTask, taskCount, taskWord);
    }
}
