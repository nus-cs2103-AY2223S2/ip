package duke.parsing;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.exceptions.DeadlineByNotSpecified;
import duke.exceptions.EventFromToNotSpecified;
import duke.exceptions.FindKeywordMissing;
import duke.exceptions.ListIndexMissing;
import duke.exceptions.TaskNameNotSpecified;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Helper class to parse user inputs into usable information.
 */
public class Parser {

    /**
     * Uses 'CommandInput' class to get CommandInput type from command input.
     *
     * @param lineInput Command line input that user entered
     * @return CommandInput type
     */
    public static String parseCommandInput(String lineInput) {
        return lineInput.split(" ")[0];
    }

    /**
     * Used by 'Deadline' & 'Event' class to get string dates as LocalDate objects.
     *
     * @param timeInput Command line input that user entered
     * @return CommandInput type
     */
    public static Optional<LocalDate> parseDate(String timeInput) {
        try {
            timeInput = timeInput.replaceAll("/", "-");
            return Optional.of(LocalDate.parse(timeInput));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Used by 'MarkCmd', 'UnmarkCmd' & 'DeleteCmd' class to get the index of the task to execute on.
     *
     * @param lineInput Command line input that the user entered
     * @return Integer index of the target task
     */
    public static int parseMarkUnmarkDeleteIndex(String lineInput) throws ListIndexMissing {
        try {
            return Integer.parseInt(lineInput.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ListIndexMissing();
        }
    }

    /**
     * Used by 'ToDo' class to initialise a ToDo task.
     * Parses command line input into relevant information needed to initilize a ToDo task.
     *
     * @param commandInput Command line input that the user entered
     * @return Task name, wrapped in an array.
     */
    public static String[] parseToDoCmd(String commandInput) {
        String[] parseInfo = {commandInput.substring(5)};
        return parseInfo;
    }

    /**
     * Used by 'Deadline' class to initialise a Deadline task.
     * Parses command line input into relevant information needed to initilize a Deadline task.
     *
     * @param commandInput Command line input that the user entered
     * @return Task name & due date, wrapped in an array.
     */
    public static String[] parseDeadlineCmd(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String taskName;
        String dueDate;

        int indexOfBy = commandInput.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified();
        }

        taskName = commandInput.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified();
        }

        try {
            dueDate = commandInput.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified();
        }

        String[] parseInfo = {taskName, dueDate};
        return parseInfo;
    }

    /**
     * Used by 'Event' class to initialise an Event task.
     * Parses command line input into relevant information needed to initilize a Event task.
     *
     * @param commandInput Command line input that the user entered
     * @return Task name, start date & end date, wrapped in an array.
     */
    public static String[] parseEventCmd(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String taskName;
        String fromDate;
        String toDate;

        commandInput = commandInput.substring(6);
        int indexOfFrom = commandInput.indexOf("/from");
        int indexOfTo = commandInput.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new EventFromToNotSpecified();
        }

        taskName = commandInput.substring(0, indexOfFrom - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified();
        }

        fromDate = commandInput.substring(indexOfFrom + 6, indexOfTo - 1);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified();
        }

        try {
            toDate = commandInput.substring(indexOfTo + 4, commandInput.length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified();
        }

        String[] parseInfo = {taskName, fromDate, toDate};
        return parseInfo;
    }

    public static String[] parseFindKeyword(String commandInput) throws FindKeywordMissing {
        try {
            return commandInput.substring(6).split(" ");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FindKeywordMissing();
        }
    }

    public static LocalDate parseViewScheduleDate(String commandInput) throws DateTimeParseException {
        String timeInput = commandInput.split(" ")[1];
        timeInput = timeInput.replaceAll("/", "-");
        return LocalDate.parse(timeInput);
    }

    public static Task parseLoadedTask(String strTask) {
        char taskType;
        String taskName;
        boolean isDone;

        String[] info1 = strTask.split("\\[");
        taskType = info1[1].charAt(0);
        isDone = info1[2].charAt(0) == 'x';
        String[] info2 = info1[2].split(" \\(");
        taskName = info2[0].substring(3);

        switch (taskType) {
        case('T'):
            return new ToDo(taskName, isDone);
        case('D'):
            String dueDate = info2[1].substring(4, info2[1].indexOf(")"));
            return new Deadline(taskName, dueDate, isDone);
        case('E'):
            String[] info3 = info2[1].split(" to: ");
            String fromDate = info3[0].substring(6);
            String toDate = info3[1].substring(0, info3[1].indexOf(")"));
            return new Event(taskName, fromDate, toDate, isDone);
        default:
            return null;
        }
    }
}
