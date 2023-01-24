package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.exceptions.DeadlineByNotSpecified;
import duke.exceptions.EventFromToNotSpecified;
import duke.exceptions.TaskNameNotSpecified;

/**
 * Helper class to parse user inputs into usable information.
 */
public class Parser {

    /**
     * Used by 'CommandInput' class to get CommandInput type from command input.
     * @param lineInput Command line input that user entered
     * @return CommandInput type
     */
    public static String parseCommandInput(String lineInput) {
        return lineInput.split(" ")[0];
    } 

    /**
     * Used by 'Deadline' & 'Event' class to get string dates as LocalDate objects.
     * @param lineInput Command line input that user entered
     * @return CommandInput type
     */
    public static Optional<LocalDate> parseDate(String timeInput) {
        try {
            timeInput = timeInput.replaceAll("/","-");
            return Optional.of(LocalDate.parse(timeInput));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Used by 'MarkCmd', 'UnmarkCmd' & 'DeleteCmd' class to get the index of the task to execute on.
     * @param lineInput Command line input that the user entered
     * @return Integer index of the target task
     */
    public static int parseMarkUnmarkDeleteIndex(String lineInput) {
        return Integer.parseInt(lineInput.split(" ")[1]) - 1;
    }

    /**
     * Used by 'ToDo' class to initialise a ToDo task.
     * Parses command line input into relevant information needed to initilize a ToDo task.
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
     * @param commandInput Command line input that the user entered
     * @return Task name & due date, wrapped in an array.
     */
    public static String[] parseDeadlineCmd(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String taskName;
        String dueDate;

        int indexOfBy = commandInput.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        taskName = commandInput.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            dueDate = commandInput.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

        String[] parseInfo = {taskName, dueDate};
        return parseInfo;
    }

    /**
     * Used by 'Event' class to initialise an Event task.
     * Parses command line input into relevant information needed to initilize a Event task.
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
            throw new EventFromToNotSpecified("Event task requires keywords '/from' and '/to'");
        }

        taskName = commandInput.substring(0, indexOfFrom - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("The description of an event cannot be empty.");
        }

        fromDate = commandInput.substring(indexOfFrom + 6, indexOfTo - 1);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        try {
            toDate = commandInput.substring(indexOfTo + 4, commandInput.length());
           
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        String[] parseInfo = {taskName, fromDate, toDate};
        return parseInfo;
    }

    public static String parseFindKeyword(String commandInput) {
        return commandInput.substring(5);
    }

    /**
     * Used by 'Storage' class to parse string task loaded from data file.
     * @param strTask String representation of task from data file
     * @return Task information wrapped in ParsedLoadedTask Object
     */
    public static ParsedLoadedTask parseLoadTask(String strTask) {
        char taskType;
        String taskName;
        boolean isDone;
        String dueDate = "";
        String fromDate = "";
        String toDate = "";

        String[] info1 = strTask.split("\\[");
        taskType = info1[1].charAt(0);
        isDone = info1[2].charAt(0) == 'x';
        String[] info2 = info1[2].split(" \\(");
        taskName = info2[0].substring(3);

        switch (taskType) {
        case('T'):
            break;
        case('D'):
            dueDate = info2[1].substring(4, info2[1].indexOf(")"));
            break;
        case('E'):
            String[] info3 = info2[1].split(" to: ");
            fromDate = info3[0].substring(6);
            toDate = info3[1].substring(0, info3[1].indexOf(")"));
            break;
        }
        return new ParsedLoadedTask(taskType, taskName, isDone, dueDate, fromDate, toDate);
    }
}

/**
 * Helper class to hold parsed information of a task loaded from a data file.
 */
class ParsedLoadedTask {
    char taskType;
    String taskName;
    boolean isDone;
    String dueDate;
    String fromDate;
    String toDate;

    public ParsedLoadedTask(char taskType, String taskName, boolean isDone, String dueDate, String fromDate, String toDate) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
