package leo.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import leo.ui.Ui;

/**
 * Represents an addition command input by user.
 */
public class AddCommand extends Command {

    /**
     * Constructor to create an AddCommand object.
     *
     * @param s Storage to store the task.
     * @param task Description of the task.
     * @throws LeoException If the command is incomplete or cannot be comprehended.
     */
    public AddCommand(Storage s, String task) throws LeoException {
        super(s, task);
        try {
            if (task.contains("todo")) {
                s.addTask(new ToDoTask(task.substring(5)));
            } else if (task.contains("deadline")) {
                try {
                    String t = task.substring(9);
                    String[] taskAndDeadline = t.split("/");
                    String deadlineTask = taskAndDeadline[0].trim();
                    String time = taskAndDeadline[1];
                    LocalDateTime dateTime = convertString(time);
                    Task deadline = new DeadlineTask(deadlineTask, dateTime);
                    s.addTask(deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineException("Uh oh! There is no deadline indicated :-(");
                }
            } else if (task.contains("event")) {
                try {
                    String t = task.substring(6);
                    String[] eventAndDuration = t.split("/");
                    String eventTask = eventAndDuration[0].trim();
                    String from = eventAndDuration[1].trim();
                    String to = eventAndDuration[2].trim();
                    LocalDateTime dateFrom = convertString(from);
                    LocalDateTime dateTo = convertString(to);
                    Task event = new EventTask(eventTask, dateFrom, dateTo);
                    s.addTask(event);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteDurationException("Uh oh! The duration of the event is incomplete :-(");
                }
            } else if (task.contains("hello") || task.contains("hi") || task.contains("hey")) {
                Ui.displayMessage(Ui.leoResponse("Well hello to you too! :-D"));
            } else {
                throw new InvalidInputException("Ohno! I do not know what you mean...");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("Uh oh! Description of task is empty :-(");
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
