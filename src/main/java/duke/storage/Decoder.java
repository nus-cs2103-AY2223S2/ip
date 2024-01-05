package duke.storage;

import java.time.Duration;

import duke.exception.InvalidInputException;
import duke.parser.TimeHandler;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.FixedDurationTask;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * The Decoder class is responsible for decoding the information provided in the storage file and creating the
 * corresponding task objects. It contains methods to decode TodoTask, DeadlineTask, and EventTask objects and
 * add them to the task list.
 */
public class Decoder {
    /**
     * Decodes a TodoTask and adds it to a TaskList.
     *
     * @param list the TaskList to add the TodoTask to
     * @param description the description of the TodoTask
     * @param isDone whether the TodoTask is done or not
     */
    public static void decodeTodo(TaskList list, String description, boolean isDone) {
        // Create a new TodoTask with the given description
        TodoTask todo = new TodoTask(description.strip());

        // If the TodoTask is done, mark it as done
        if (isDone) {
            todo.markAsDone();
        }

        // Add the TodoTask to the given TaskList
        list.addTask(todo);
    }

    /**
     * Decodes a DeadlineTask and adds it to a TaskList.
     *
     * @param list the TaskList to add the DeadlineTask to
     * @param description the description of the DeadlineTask
     * @param isDone whether the DeadlineTask is done or not
     * @param date the deadline date of the task as a string in the format "yyyy-MM-dd"
     */
    public static void decodeDeadline(TaskList list, String description, boolean isDone, String date)
            throws InvalidInputException {
        // Create a new DeadlineTask with the given description and deadline date
        DeadlineTask deadline = new DeadlineTask(description.trim(),
                TimeHandler.parseToLocalDateTime(date));

        // If the DeadlineTask is done, mark it as done
        if (isDone) {
            deadline.markAsDone();
        }

        // Add the DeadlineTask to the given TaskList
        list.addTask(deadline);
    }

    /**
     * Decodes an EventTask and adds it to a TaskList.
     *
     * @param list the TaskList to add the EventTask to
     * @param description the description of the EventTask
     * @param isDone whether the EventTask is done or not
     * @param from the start date of the event as a string in the format "yyyy-MM-dd"
     * @param to the end date of the event as a string in the format "yyyy-MM-dd"
     * @throws InvalidInputException if the input is invalid
     */
    public static void decodeEvent(TaskList list, String description, boolean isDone, String from, String to)
            throws InvalidInputException {
        // Create a new EventTask with the given description and event dates
        EventTask event = new EventTask(description.strip(), TimeHandler.parseToLocalDateTime(from),
                TimeHandler.parseToLocalDateTime(to));

        // If the EventTask is done, mark it as done
        if (isDone) {
            event.markAsDone();
        }

        // Add the EventTask to the given TaskList
        list.addTask(event);
    }

    /**
     * Decodes the given description, done status, and duration into a FixedDurationTask
     * and adds it to the given TaskList.
     *
     * @param list the TaskList to add the FixedDurationTask to
     * @param description the description of the FixedDurationTask
     * @param isDone whether the FixedDurationTask is done or not
     * @param duration the duration of the FixedDurationTask in the format "PT1H30M"
     */
    public static void decodeFixedDuration(TaskList list, String description, boolean isDone, String duration) {
        // Create a new FixedDurationTask with the given description and duration
        FixedDurationTask fixedDuration = new FixedDurationTask(description.trim(),
                Duration.parse(duration));

        // If the FixedDurationTask is done, mark it as done
        if (isDone) {
            fixedDuration.markAsDone();
        }

        // Add the FixedDurationTask to the given TaskList
        list.addTask(fixedDuration);
    }
}
