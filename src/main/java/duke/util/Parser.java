package duke.util;

import duke.io.input.exception.DukeException;
import duke.io.input.exception.UserInputException;
import duke.io.input.ui.UserInterface;
import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;
import duke.util.service.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Task parseTask(String userinput) {
        String[] userInputArray = userinput.split(" ");
        List<String> userInputSplit = Arrays.asList(userInputArray);
        String mainCommand = userInputSplit.get(0);

        if (mainCommand.equals("TODO")) {
            return parseTodo(userinput);
        } else if (mainCommand.equals("DEADLINE")) {
            return parseDeadline(userinput);
        } else {
            return parseEvent(userinput);
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        Deadline newTask = new Deadline(
                LocalDateTime.parse(deadlineTaskAsList.get(1), format),
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

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        ScheduledEvent newTask = new ScheduledEvent(
                LocalDateTime.parse(eventBegin, format),
                LocalDateTime.parse(eventEnd, format),
                eventPhraseList.get(1));

        return newTask;
    }

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