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
     * @throws IncorrectNoOfArgumentException When there is insufficient arguments provided by the user.
     * @see ArrayList
     */
    protected static ArrayList<String> parse(String taskInfo) throws IncorrectNoOfArgumentException {
        ArrayList<String> parseInfoList = new ArrayList<>(); // stores in the format "command" followed by "args"
        boolean isFnAvailable = false; // indicates whether user is calling a supported function provided by Duke
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
        if ((tempCmd.equals("mark")) || (tempCmd.equals("unmark")) || (tempCmd.equals("delete")) || (tempCmd
                .equals("todo")) || (tempCmd.equals("deadline")) || (tempCmd.equals("event")) || (tempCmd
                .equals("find"))) {
            isFnAvailable = true;
        }

        try { // determine function called by the user has required arguments and does not have blank spaces
            DukeException.validate(isFnAvailable, tempCmd, tempTaskInfo);
            parseInfoList.add(tempCmd); // save function call (command) into parseInfo
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            parseInfoList = new ArrayList<>();
            parseInfoList.add(ex.getMessage());
            return parseInfoList;
        }

        switch (tempCmd) { // retrieving arguments required by the commands
        case "bye": // format: bye
            break;
        case "list": // format: list
            break;
        case "find":
            parseInfoList.add(tempTaskInfo[1].toLowerCase());
            break;
        case "mark": // format: mark | index
            parseInfoList.add(tempTaskInfo[1]);
            break;
        case "unmark": // format: unmark | index
            parseInfoList.add(tempTaskInfo[1]); // index
            break;
        case "delete": // format: delete | index
            parseInfoList.add(tempTaskInfo[1]); // index
            break;
        case "todo": // format: done (optional) | String.toString(to+do) | taskInfo
            parseInfoList.add(tempTaskInfo[1]); // taskInfo
            break;
        case "deadline": // format: done (optional) | deadline | taskInfo | date | time
            String[] tempTaskInfo2 = tempTaskInfo[1].split("/by", 2);
            try { // checking the element "cccc" in ".../by cccc"
                DukeException.validate(true, tempCmd, tempTaskInfo2);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfoList = new ArrayList<>();
                parseInfoList.add(ex.getMessage());
                break;
            }

            String time;
            String[] dateTime = tempTaskInfo2[1].split(" ");
            if (dateTime.length != 3) {
                time = "";
            } else {
                time = dateTime[2];
            }

            parseInfoList.add(tempTaskInfo2[0]); // taskInfo
            parseInfoList.add(dateTime[1]); // date
            parseInfoList.add(time); // time
            break;
        case "event": // format: done (optional) | event | taskInfo | startDate | startTime | endDate | endTime
            String[] tempTaskInfo3 = tempTaskInfo[1].split("/from", 2);
            try { // checking the element "bbbb" in ".../from bbbb /to aaaa"
                DukeException.validate(true, tempCmd, tempTaskInfo3);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfoList = new ArrayList<>();
                parseInfoList.add(ex.getMessage());
                break;
            }
            String startTime;
            String[] startDateTime = tempTaskInfo3[1].split(" ");
            if (startDateTime.length > 2) { // check if given a startDate
                if (startDateTime[2].equals("/to")) {
                    startTime = "";
                } else {
                    startTime = startDateTime[2];
                }
            } else {
                DukeException.validate("", "event");
                break;
            }

            String[] testPortion = tempTaskInfo3[1].split("/to", 2);
            try { // checking the element "aaaa" in ".../to aaaa"
                DukeException.validate(true, tempCmd, testPortion);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfoList = new ArrayList<>();
                parseInfoList.add(ex.getMessage());
                break;
            }

            String endTime;
            String[] endDateTime = testPortion[1].split(" ");
            if (endDateTime.length > 1) { // check if given a endDate
                if (endDateTime.length != 3) {
                    endTime = "";
                } else {
                    endTime = endDateTime[2];
                }
            } else {
                DukeException.validate("", "event");
                break;
            }

            parseInfoList.add(tempTaskInfo3[0]); // taskInfo
            parseInfoList.add(startDateTime[1]); // startDate
            parseInfoList.add(startTime); // startTime
            parseInfoList.add(endDateTime[1]); // endDate
            parseInfoList.add(endTime); // endTime
            break;
        default: // throw an error as the user is trying to call a function that does not exist
            try {
                DukeException.validate2();
            } catch (InvalidCommandException ex) {
                System.out.println(ex);
                parseInfoList.set(0, "error");
                parseInfoList.add(ex.getMessage());
                break;
            }
        }
        return parseInfoList;
    }
}
