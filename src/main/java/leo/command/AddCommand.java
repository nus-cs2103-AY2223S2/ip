package leo.command;

import leo.leoexception.EmptyDeadlineException;
import leo.leoexception.EmptyDescriptionException;
import leo.leoexception.IncompleteDurationException;
import leo.leoexception.InvalidInputException;
import leo.leoexception.LeoException;
import leo.storage.DeadlineTask;
import leo.storage.EventTask;
import leo.storage.Storage;
import leo.storage.Task;
import leo.storage.ToDoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an addition command input by user.
 */
public class AddCommand extends Command {

    /**
     * Constructor to create an AddCommand object.
     *
     * @param s Storage to store the task.
     * @param task Description of the task.
     */
    public AddCommand(Storage s, String task) {
        super(s, task);
    }

    @Override
    public String execute() throws LeoException {
        try {
            if (command.contains("todo")) {
                return storage.addTask(new ToDoTask(command.substring(5)));
            } else if (command.contains("deadline")) {
                try {
                    String t = command.substring(9);
                    String[] taskAndDeadline = t.split("/");
                    String deadlineTask = taskAndDeadline[0].trim();
                    String time = taskAndDeadline[1];
                    LocalDateTime dateTime = convertString(time);
                    Task deadline = new DeadlineTask(deadlineTask, dateTime);
                    return storage.addTask(deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineException();
                }
            } else if (command.contains("event")) {
                try {
                    String t = command.substring(6);
                    String[] eventAndDuration = t.split("/");
                    String eventTask = eventAndDuration[0].trim();
                    String from = eventAndDuration[1].trim();
                    String to = eventAndDuration[2].trim();
                    LocalDateTime dateFrom = convertString(from);
                    LocalDateTime dateTo = convertString(to);
                    Task event = new EventTask(eventTask, dateFrom, dateTo);
                    return storage.addTask(event);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteDurationException();
                }
            } else if (command.contains("hello") || command.contains("hi")) {
                return "Well hello to you too! :-D";
            } else {
                throw new InvalidInputException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Converts String object to LocalDateTime.
     *
     * @param str String representation of date and time.
     * @return LocalDateTime object.
     */
    private LocalDateTime convertString(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }
}
