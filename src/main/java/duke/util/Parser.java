package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import duke.io.input.exception.DukeException;
import duke.io.input.exception.UserInputException;
import duke.io.input.ui.UserInterface;
import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;
import duke.util.service.ToDo;


/**
 * A class used to parse the input of users
 * into specified events
 */

public class Parser {
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Parse the input of users
     * into specified tasks
     *
     * @param userInput the user input
     * @return a parsed {@code Task} as specified by
     *          the user input
     */

    public static Task parseTask(String userInput) {
        String[] userInputArray = userInput.split(" ");
        List<String> userInputSplit = Arrays.asList(userInputArray);
        String mainCommand = userInputSplit.get(0);

        if (mainCommand.equals("TODO")) {
            return parseTodo(userInput);
        } else if (mainCommand.equals("DEADLINE")) {
            return parseDeadline(userInput);
        } else {
            return parseEvent(userInput);
        }
    }

    private static Task parseTodo(String userInput) {
        String[] toDoTask = userInput.split("TODO ");
        List<String> toDoAction = Arrays.asList(toDoTask);
        ToDo newTask = new ToDo(toDoAction.get(1));
        return newTask;
    }

    private static Task parseDeadline(String userInput) {
        String[] deadlineTask = userInput.split(" /BY ");
        List<String> deadlineTaskAsList = Arrays.asList(deadlineTask);

        String commandAndAction = deadlineTaskAsList.get(0);
        String[] commandAndActionSplit = commandAndAction.split("DEADLINE ");
        List<String> commandAndActionList = Arrays.asList(commandAndActionSplit);
        Deadline newTask = new Deadline(
                LocalDateTime.parse(deadlineTaskAsList.get(1), DATETIME_FORMAT),
                commandAndActionList.get(1));
        return newTask;
    }

    private static Task parseEvent(String userInput) {
        String[] splitCommandFrom = userInput.split(" /FROM ");
        List<String> actionTimeRangeSplit = Arrays.asList(splitCommandFrom);

        String timeRangeString = actionTimeRangeSplit.get(1);
        String[] timeRangeAsArray = timeRangeString.split(" /TO ");
        List<String> timeRangeAsList = Arrays.asList(timeRangeAsArray);

        String eventAction = actionTimeRangeSplit.get(0);
        String[] eventPhraseArray = eventAction.split("EVENT ");
        List<String> eventPhraseList = Arrays.asList(eventPhraseArray);

        String eventBegin = timeRangeAsList.get(0);
        String eventEnd = timeRangeAsList.get(1);

        ScheduledEvent newTask = new ScheduledEvent(
                LocalDateTime.parse(eventBegin, DATETIME_FORMAT),
                LocalDateTime.parse(eventEnd, DATETIME_FORMAT),
                eventPhraseList.get(1));

        return newTask;
    }

    /**
     * Parse the input of users
     * into specified date
     *
     * @param userInput the user input
     * @return a parsed {@code LocalDate} as specified by
     *          the user input in order to search for scheduled tasks
     *          on a specified date
     */

    public static LocalDate parseDate(String userInput) {
        String[] searchDateArray = userInput.split("SCHEDULE ");
        List<String> searchDateList = Arrays.asList(searchDateArray);
        String searchDate = searchDateList.get(1);

        LocalDate scheduleDate = LocalDate.parse(searchDate, DATE_FORMAT);
        return scheduleDate;
    }

    /**
     * Check the validity of user input
     *
     * @param userCommand the user input
     * @param tasklistSize the current number of tasks
     *
     * @return a boolean that is true when the user input is valid,
     *          and false when it isn't. Print a warning when the
     *          user input is invalid
     */

    public static boolean checkInputValidity(String userCommand, int tasklistSize) {
        try {
            UserInputException.checkUserInput(userCommand, tasklistSize);
        } catch (DukeException exception) {
            UserInterface.printInputWarning(exception);
            return false;
        } catch (Exception exception) {
            UserInterface.printInputWarning("ERRRR ERROR ERRR. SYSTEM FAILURE. UNKNOWN EXCEPTION. ERR ERR");
            return false;
        }
        return true;
    }
}
