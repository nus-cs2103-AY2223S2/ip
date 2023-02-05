package parsers;

import exceptions.UnknownCommandException;
import formatters.StringUtils;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 * Parser which takes in commands or string responses and returns a task.
 */
public class TaskInfoParser extends Parser {

    /**
     * Extracts information from the task to decide which object it is referring
     * to in the command input and returns it accordingly.
     * @param input command input from stdin
     * @return Task object specific to the command input string
     */
    public static Task parse(String input) {
        String[] commands = input.split(" ");
        switch(commands[0]) {
        case "todo":
            return ToDo.create(commands);
        case "deadline":
            return Deadline.create(input);
        case "event":
            return Event.create(input);
        default:
            break;
        }
        throw new UnknownCommandException(String.format("Fall to the Dark Side You must Not,"
                + " for not know what %s means!!", commands[0]), null);
    }

    /**
     * Extracts information rom the task to decide which object it is referring to
     * based on data from the file and returns it accordingly.
     * @param stringArray the data split into individual words into an array
     * @return Task object specific to the data representation from file
     */
    public static Task parse(String[] stringArray) {
        int length = stringArray.length;
        stringArray = StringUtils.removeWhiteSpace(stringArray);
        switch (stringArray[0]) {
        case "T":
            return ToDo.create(StringUtils.joinString(stringArray, 2, length - 1), stringArray[1]);
        case "D":
            int byIndex = StringUtils.searchString(stringArray, "by");
            String description = StringUtils.joinString(stringArray, 2, byIndex - 1);
            String deadline = stringArray[length - 2];
            String time = stringArray[length - 1];
            assert(byIndex + 2 == length - 1);
            return Deadline.create(description, deadline, time, stringArray[1]);
        case "E":
            int fromIndex = StringUtils.searchString(stringArray, "from");
            int toIndex = StringUtils.searchString(stringArray, "to");
            assert(fromIndex + 3 == toIndex);
            assert(toIndex + 2 == length - 1);
            String description1 = StringUtils.joinString(stringArray, 2, fromIndex - 1);
            String startDate = stringArray[fromIndex + 1];
            String startTime = stringArray[fromIndex + 2];
            String endDate = stringArray[toIndex + 1];
            String endTime = stringArray[toIndex + 2];
            return Event.create(description1, startDate, endDate, startTime, endTime, stringArray[1]);
        default:
            break;
        }
        return null;
    }
}
