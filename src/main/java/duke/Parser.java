package duke;

import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.IncorrectNoOfArgumentException;
import exceptions.InvalidCommandException;

/**
 * Represents a Parser that takes in user input and process it into the necessary format for Duke application
 * to use.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Parser {

    /**
     * Returns an ArrayList of String type that contains the processed command information,
     * derived from the user input via the CLI.
     * <p></p>
     * This method aids in processing user input.
     *
     * @param taskInfo Command information obtained from user's input via the CLI.
     * @return ArrayList of String type, containing processed command information.
     * @see ArrayList
     */
    protected static ArrayList<String> parse(String taskInfo) {
        ArrayList<String> parseInfoList = new ArrayList<>(); // stores in the format "command" followed by "args"
        boolean hasArgs; // indicates whether user is calling a supported function that needs multiple arguments
        String tempCmd; // stores function call by user (eg todos, mark, etc)
        String[] tempTaskInfo = taskInfo.split("] ");

        if (tempTaskInfo.length != 1) { // retrieving info from file and processing it
            switch (tempTaskInfo[0]) {
            case "[D][ ":
                tempCmd = "deadline";
                break;
            case "[D][X":
                tempCmd = "deadline";
                parseInfoList.add("done");
                break;
            case "[T][ ":
                tempCmd = "todo";
                break;
            case "[T][X":
                tempCmd = "todo";
                parseInfoList.add("done");
                break;
            case "[E][ ":
                tempCmd = "event";
                break;
            case "[E][X":
                tempCmd = "event";
                parseInfoList.add("done");
                break;
            default:
                tempCmd = "";
                break;
            }
        } else { // adding new task that is not from file and processing it
            tempTaskInfo = taskInfo.split(" ", 2);
            tempCmd = tempTaskInfo[0].toLowerCase();
        }
        hasArgs = hasMoreArgs(tempCmd);
        parseInfoList = parse2(hasArgs, tempCmd, parseInfoList, tempTaskInfo);
        return parseInfoList;
    }

    /**
     * Returns an ArrayList of String type that contains the processed command information,
     * derived from the user input via the CLI.
     * <p></p>
     * This method acts as a helper method for parse() to parse the command information.
     *
     * @param hasMoreArgs Boolean value indicating whether the command getting called is supported.
     * @param cmd String representing the command.
     * @param partialCmd ArrayList of String type, containing command information, used to store parsed command.
     * @param tempTaskInfo String array containing the user input via CLI.
     * @return ArrayList of String type containing parse command information.
     * @see ArrayList
     */
    private static ArrayList<String> parse2(boolean hasMoreArgs, String cmd, ArrayList<String> partialCmd,
                                            String[] tempTaskInfo) {
        try {
            // determine if function called by the user is available, has required arguments and
            // does not have blank spaces
            if (!isAvailable(cmd)) {
                DukeException.validate2();
            }
            DukeException.validate(hasMoreArgs, cmd, tempTaskInfo);
            partialCmd.add(cmd); // save function call (command) into parseInfo
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            partialCmd = new ArrayList<>();
            partialCmd.add(ex.getMessage());
            return partialCmd;
        } catch (InvalidCommandException ex) {
            System.out.println(ex);
            partialCmd.add(0, "error");
            partialCmd.add(ex.getMessage());
            return partialCmd;
        }

        switch (cmd) { // retrieving arguments required by the commands
        case "bye": // format: bye
            break;
        case "list": // format: list
            break;
        case "reminder": // format: reminder
            break;
        case "find":
            partialCmd.add(tempTaskInfo[1].toLowerCase());
            break;
        case "mark": // format: mark | index
            partialCmd.add(tempTaskInfo[1]);
            break;
        case "unmark": // format: unmark | index
            partialCmd.add(tempTaskInfo[1]); // index
            break;
        case "delete": // format: delete | index
            partialCmd.add(tempTaskInfo[1]); // index
            break;
        case "todo": // format: done (optional) | String.toString(to+do) | taskInfo
            partialCmd.add(tempTaskInfo[1]); // taskInfo
            break;
        case "deadline": // format: done (optional) | deadline | taskInfo | date | time
            partialCmd = deadlineParser(cmd, partialCmd, tempTaskInfo);
            break;
        case "event": // format: done (optional) | event | taskInfo | startDate | startTime | endDate | endTime
            partialCmd = eventParser(cmd, partialCmd, tempTaskInfo);
            break;
        default: // throw an error as the user is trying to call a function that does not exist
            break;
        }
        assert partialCmd.size() > 0;
        return partialCmd;
    }

    /**
     * Returns ArrayList of String type containing the parsed command, used for parsing deadLine command.
     *
     * @param cmd String representing the command.
     * @param partialCmd ArrayList of String type, containing command information, used to store parsed command.
     * @param tempTaskInfo String array containing the user input via CLI.
     * @return ArrayList of String type containing the parsed command, used for parsing deadLine command.
     */
    private static ArrayList<String> deadlineParser(String cmd, ArrayList<String> partialCmd, String[] tempTaskInfo) {
        String[] tempTaskInfo2 = tempTaskInfo[1].split("/by", 2);
        try { // checking the element "cccc" in ".../by cccc"
            DukeException.validate(true, cmd, tempTaskInfo2);
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            partialCmd = new ArrayList<>();
            partialCmd.add(ex.getMessage());
            return partialCmd;
        }
        String time;
        String[] dateTime = tempTaskInfo2[1].split(" ");
        if (dateTime.length != 3) {
            time = "";
        } else {
            time = dateTime[2];
        }
        partialCmd.add(tempTaskInfo2[0]); // taskInfo
        partialCmd.add(dateTime[1]); // date
        partialCmd.add(time); // time
        return partialCmd;
    }

    /**
     * Returns ArrayList of String type containing the parsed command, used for parsing event command.
     *
     * @param cmd String representing the command.
     * @param partialCmd ArrayList of String type, containing command information, used to store parsed command.
     * @param tempTaskInfo String array containing the user input via CLI.
     * @return ArrayList of String type containing the parsed command, used for parsing event command.
     */
    private static ArrayList<String> eventParser(String cmd, ArrayList<String> partialCmd, String[] tempTaskInfo) {
        String[] tempTaskInfo3 = tempTaskInfo[1].split("/from", 2);
        partialCmd = eventStartParser(cmd, partialCmd, tempTaskInfo3);
        if (partialCmd.size() < 3) {
            return partialCmd;
        } else {
            partialCmd = eventEndParser(cmd, partialCmd, tempTaskInfo3);
            return partialCmd;
        }
    }

    /**
     * Returns ArrayList of String type containing the event name and event start details.
     * <p></p>
     * This method acts as a helper method for eventParser() to parse the event startDate and startTime.
     *
     * @param cmd String representing the command.
     * @param eventInformation ArrayList of String type, containing command information, used to store parsed command.
     * @param tempTaskInfo String array containing the user input via CLI.
     * @return ArrayList of String type containing the event name and event start details.
     */
    private static ArrayList<String> eventStartParser(String cmd, ArrayList<String> eventInformation,
                                                      String[] tempTaskInfo) {
        try { // checking the element "bbbb" in ".../from bbbb /to aaaa"
            DukeException.validate(true, cmd, tempTaskInfo);
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            eventInformation = new ArrayList<>();
            eventInformation.add(ex.getMessage());
            return eventInformation;
        }
        eventInformation.add(tempTaskInfo[0]); // adds "event" command in string into the eventInformation
        String[] startDateTime = tempTaskInfo[1].split(" ");
        if (startDateTime[1].equals("/to")) {
            try {
                DukeException.validate("", "event"); // throwing an error as "aaa" is blank in /from aaa
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                eventInformation = new ArrayList<>();
                eventInformation.add(ex.getMessage());
                return eventInformation;
            }
        } else {
            eventInformation.add(startDateTime[1]); // adds event startDate into the eventInformation
        }
        if (startDateTime.length > 2) { // check if given a startTime
            if (startDateTime[2].equals("/to")) {
                eventInformation.add(""); // adds event startTime into the eventInformation
            } else {
                eventInformation.add(startDateTime[2]); // adds event startTime into the eventInformation
            }
        } else {
            try {
                DukeException.validate("", "event");
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                eventInformation = new ArrayList<>();
                eventInformation.add(ex.getMessage());
            }
        }
        return eventInformation;
    }

    /**
     * Returns ArrayList of String type containing FULL details on the event, including its ending date and time.
     * <p></p>
     * This method acts as a helper method for eventParser() to parse the event endDate and endTime.
     *
     * @param cmd String representing the command.
     * @param eventInformation ArrayList of String type, containing command information, used to store parsed command.
     * @param tempTaskInfo String array containing the user input via CLI.
     * @return ArrayList of String type containing FULL details on the event, including its ending date and time.
     */
    private static ArrayList<String> eventEndParser(String cmd, ArrayList<String> eventInformation,
                                                    String[] tempTaskInfo) {
        String[] testPortion = tempTaskInfo[1].split("/to", 2);
        try { // checking the element "aaaa" in ".../to aaaa"
            DukeException.validate(true, cmd, testPortion);
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            eventInformation = new ArrayList<>();
            eventInformation.add(ex.getMessage());
            return eventInformation;
        }
        String[] endDateTime = testPortion[1].split(" ");
        eventInformation.add(endDateTime[1]);
        if (endDateTime.length > 1) { // check if given a endDate
            if (endDateTime.length != 3) {
                eventInformation.add("");
            } else {
                eventInformation.add(endDateTime[2]);
            }
        } else {
            try {
                DukeException.validate("", "event");
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                eventInformation = new ArrayList<>();
                eventInformation.add(ex.getMessage());
            }
        }
        return eventInformation;
    }

    /**
     * Returns a boolean value which indicates whether the command requires more arguments.
     *
     * @param cmd String indicating the command name.
     * @return Boolean value indicating whether the command requires more arguments.
     */
    private static boolean hasMoreArgs(String cmd) {
        if (cmd.equals("mark")) {
            return true;
        } else if (cmd.equals("unmark")) {
            return true;
        } else if (cmd.equals("delete")) {
            return true;
        } else if (cmd.equals("todo")) {
            return true;
        } else if (cmd.equals("deadline")) {
            return true;
        } else if (cmd.equals("event")) {
            return true;
        } else if (cmd.equals("find")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a boolean value which indicates whether the command is available.
     *
     * @param cmd String indicating the command name.
     * @return Boolean value indicating whether the command is available.
     */
    private static boolean isAvailable(String cmd) {
        if (cmd.equals("mark")) {
            return true;
        } else if (cmd.equals("unmark")) {
            return true;
        } else if (cmd.equals("delete")) {
            return true;
        } else if (cmd.equals("todo")) {
            return true;
        } else if (cmd.equals("event")) {
            return true;
        } else if (cmd.equals("deadline")) {
            return true;
        } else if (cmd.equals("find")) {
            return true;
        } else if (cmd.equals("reminder")) {
            return true;
        } else if (cmd.equals("bye")) {
            return true;
        } else if (cmd.equals("list")) {
            return true;
        } else {
            return false;
        }
    }
}
