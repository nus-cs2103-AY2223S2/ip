package classes;

import exceptions.DukeException;
import exceptions.IncorrectNoOfArgumentException;
import exceptions.InvalidCommandException;

import java.util.ArrayList;

public class Parser {

    protected static ArrayList<String> parse(String taskInfo) throws IncorrectNoOfArgumentException {
        ArrayList<String> parseInfo = new ArrayList<>();    // stores in the format "command" followed by "arguments"
        boolean isFnAvailable = false;  // indicates whether user is calling a supported function provided by Duke
        String tempCmd;    // stores function call by user (eg todo, mark, etc)
        String[] tempTaskInfo = taskInfo.split("] ");

        if (tempTaskInfo.length != 1) {   // retrieving info from file
            switch (tempTaskInfo[0]) {
            case "[D][ ":
                tempCmd = "deadline";
                break;
            case "[D][X":
                tempCmd = "deadline";
                parseInfo.add("done");
                break;
            case "[T][ ":
                tempCmd = "todo";
                break;
            case "[T][X":
                tempCmd = "todo";
                parseInfo.add("done");
                break;
            case "[E][ ":
                tempCmd = "event";
                break;
            case "[E][X":
                tempCmd = "event";
                parseInfo.add("done");
                break;
            default:
                tempCmd = "";
                break;
            }
        } else {    // adding new task that is not from file
            tempTaskInfo = taskInfo.split(" ", 2);
            tempCmd = tempTaskInfo[0].toLowerCase();
        }

        if ( (tempCmd.equals("mark")) || (tempCmd.equals("unmark")) || (tempCmd.equals("delete")) ||
                (tempCmd.equals("todo")) || (tempCmd.equals("deadline")) || (tempCmd.equals("event")) ) {
            isFnAvailable = true;
        }

        try {   // determine function called by the user has required arguments and if its valid
            DukeException.validate(isFnAvailable, tempCmd, tempTaskInfo);
            parseInfo.add(tempCmd); // save function call (command) into parseInfo
        } catch (IncorrectNoOfArgumentException ex) {
            System.out.println(ex);
            parseInfo = new ArrayList<>();
            return parseInfo;
        }

        switch (tempCmd) {  // retrieving arguments required by the commands
        case "bye":     // format: bye
            break;
        case "list":    // format: list
            break;
        case "mark":    // format: mark | index
            parseInfo.add(tempTaskInfo[1]);
            break;
        case "unmark":  // format: unmark | index
            parseInfo.add(tempTaskInfo[1]); // index
            break;
        case "delete":  // format: delete | index
            parseInfo.add(tempTaskInfo[1]); // index
            break;
        case "todo":    // format: done (optional) | String.toString(to+do) | taskInfo
            parseInfo.add(tempTaskInfo[1]); // taskInfo
            break;
        case "deadline":    // format: done (optional) | deadline | taskInfo | date | time
            String[] tempTaskInfo2 = tempTaskInfo[1].split("/by", 2);
            try {   // checking the element "cccc" in ".../by cccc"
                DukeException.validate(true, tempCmd, tempTaskInfo2);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfo = new ArrayList<>();
                break;
            }

            String time;
            String[] tempDateTime = tempTaskInfo2[1].split(" ");
            if (tempDateTime.length != 3) {
                time = "";
            } else {
                time = tempDateTime[2];
            }

            parseInfo.add(tempTaskInfo2[0]);    // taskInfo
            parseInfo.add(tempDateTime[1]); // date
            parseInfo.add(time);    // time
            break;
        case "event":   // format: done (optional) | event | taskInfo | startDate | startTime | endDate | endTime
            String[] tempTaskInfo3 = tempTaskInfo[1].split("/from", 2);
            try {   // checking the element "bbbb" in ".../from bbbb /to aaaa"
                DukeException.validate(true, tempCmd, tempTaskInfo3);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfo = new ArrayList<>();
                break;
            }
            String startTime;
            String[] tempStartDateTime = tempTaskInfo3[1].split(" ");
            if (tempStartDateTime.length > 2) { // check if given a startDate
                if (tempStartDateTime[2].equals("/to")) {
                    startTime = "";
                } else {
                    startTime = tempStartDateTime[2];
                }
            } else {
                DukeException.validate("", "event");
                break;
            }

            String[] tempText4 = tempTaskInfo3[1].split("/to", 2);
            try {   // checking the element "aaaa" in ".../to aaaa"
                DukeException.validate(true, tempCmd, tempText4);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                parseInfo = new ArrayList<>();
                break;
            }

            String endTime;
            String[] tempEndDateTime = tempText4[1].split(" ");
            if (tempEndDateTime.length > 1) {   // check if given a endDate
                if (tempEndDateTime.length != 3) {
                    endTime = "";
                } else {
                    endTime = tempEndDateTime[2];
                }
            } else {
                DukeException.validate("", "event");
                break;
            }

            parseInfo.add(tempTaskInfo3[0]);    // taskInfo
            parseInfo.add(tempStartDateTime[1]);    // startDate
            parseInfo.add(startTime);   // startTime
            parseInfo.add(tempEndDateTime[1]);  // endDate
            parseInfo.add(endTime); // endTime
            break;
        default:    // throw an error as the user is trying to call a function that does not exist
            try {
                DukeException.validate2();
            } catch (InvalidCommandException ex) {
                System.out.println(ex);
                parseInfo.set(0, "error");
                break;
            }
        }
        return parseInfo;
    }

}
