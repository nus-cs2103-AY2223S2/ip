import java.util.Arrays;

public class TaskInfoParser extends Parser {

    /**
     * This is a parser to extract information from the task to decide which object it is referring
     * to in the command input and returns it accordingly.
     * @param input command input from stdin
     * @return Task object specific to the command input string
     */
    public static Task parse(String input) {
        String[] commands = input.split(" ");
        switch(commands[0]) {
            case "todo" :
                return ToDo.create(commands);
            case "deadline" :
                return Deadline.create(input);
            case "event" :
                return Event.create(input);
            default:
                break;
        }
        throw new UnknownCommandException(String.format("Fall to the Dark Side You must Not," +
                " for not know what %s means!!", commands[0]), null);
    }

    public static Task parse(String[] stringArray) {
        int length = stringArray.length;
        stringArray = StringUtils.removeWhiteSpace(stringArray);
        switch(stringArray[0]) {
            case "T":
                return ToDo.create(StringUtils.joinString(stringArray, 2, length - 1), stringArray[1]);
            case "D":
                int byIndex = StringUtils.searchString(stringArray, "by");
                String description = StringUtils.joinString(stringArray, 2, byIndex - 1);
                String deadline = stringArray[length - 2];
                String time = stringArray[length - 1];
                return Deadline.create(description, deadline, time, stringArray[1]);
            case "E":
                int fromIndex = StringUtils.searchString(stringArray, "from");
                int toIndex = StringUtils.searchString(stringArray, "to");
                String description1 = StringUtils.joinString(stringArray, 2, fromIndex - 1);
                String startDate = stringArray[fromIndex + 1];
                String startTime = stringArray[fromIndex + 2];
                String endDate = stringArray[toIndex + 1];
                String endTime = stringArray[toIndex + 2];
                assert(toIndex + 3 == length - 1);
                return Event.create(description1, startDate, endDate, startTime, endTime, stringArray[1]);
        }
        return null;
    }
}
