package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingContentException;

/**
 * Makes sense of what users say
 */
public class Parser {
    static final String inSecond = "00";
    static final int secondToEndIndex = 2;
    static final int minSize = 0;
    static String lastCommand = "";
    static String lastCommandDetail = "";
    static TaskList lastTaskList = new TaskList();

    /**
     * Update last command
     * @param newCommand
     */
    public static void updateLastCommand(String newCommand) {
        lastCommand = newCommand;
    }

    public static void updateLastCommandDetail(String newDetail) {
        lastCommandDetail = newDetail;
    }

    public static void updateLastTaskList(TaskList newTaskList) {
        lastTaskList = newTaskList;
    }

    public static TaskList getLastTaskList() {
        return lastTaskList;
    }

    public static String undo(TaskList taskList) {
        if (lastCommand.startsWith("mark ")) {
            taskList.overwrite(lastTaskList);
            return ("WOOF! Got it! I will undo the last command!" + "\n"
                    + "The following task has been unmarked: " + "\n"
                    + lastCommandDetail);
        } else if (lastCommand.startsWith("unmark ")) {
            taskList.overwrite(lastTaskList);
            return ("WOOF! Got it! I will undo the last command!" + "\n"
                    + "The following task has been un-unmarked: " + "\n"
                    + lastCommandDetail);
        } else if (lastCommand.startsWith("delete ")) {
            taskList.overwrite(lastTaskList);
            return ("WOOF! Got it! I will undo the last command!" + "\n"
                    + "The following task has been un-deleted: " + "\n"
                    + lastCommandDetail);
        } else if (lastCommand.startsWith("todo ") || lastCommand.startsWith("event ") ||
                lastCommand.startsWith("deadline ")) {
            taskList.overwrite(lastTaskList);
            return ("WOOF! Got it! I will undo the last command!" + "\n"
                    + "The following task has been un-added: " + "\n"
                    + lastCommandDetail);
        } else {
            return ("WOOF!! The last command cannot be undone!");
        }
    }

    /**
     * Makes sense of adding to do command from users
     * @param arr array of original array
     * @return full string command for users
     */
    public static String toDo(String[] arr) throws MissingContentException {
        String remaining = "";
        try {
            remaining = arr[1];
            remaining += " ";
            for (int j = 2; j < arr.length; j++) {
                remaining += arr[j];
                remaining += " ";
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new MissingContentException();
        }
        assert (remaining.length() != minSize) : "Parser could not process todo task as description is empty";
        return remaining;
    }

    /**
     * Gets index at which task should perform on task list
     * @param listOfAction original task list
     * @param command user's command input
     * @return index of tasklist
     * @throws MissingContentException if command does not specify index
     * @throws InvalidIndexException if task list does not have such index
     */
    public static int getTaskIndex(TaskList listOfAction, String[] command) throws MissingContentException,
            InvalidIndexException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(command[1]);
        } catch (NullPointerException e) {
            throw new MissingContentException();
        }
        if (!listOfAction.checkValidIndex(taskIndex - 1)) {
            throw new InvalidIndexException();
        } else if (taskIndex < 1) {
            throw new InvalidIndexException();
        }
        return taskIndex;
    }

    /**
     * Returns the detail/content of deadline.
     * Given that the detail is not empty.
     *
     * @param arr an array of the input.
     * @return the String detail.
     * @throws MissingContentException if arr is empty.
     */
    public String deadlineDetail(String[] arr) throws MissingContentException {
        String detail = "";
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/by")) {
                break;
            }
            detail += arr[j];
            detail += " ";
        }
        return detail;
    }

    /**
     * Returns index of the input array where indicates time of the deadline.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return the index of deadline time.
     * @throws MissingContentException if arr is empty.
     */
    public int deadlineTimeIndex(String[] arr) throws MissingContentException {
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        int pointer = 0;
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/by")) {
                pointer = j + 1;
                break;
            }
        }
        if (pointer == minSize) {
            throw new MissingContentException();
        }
        return pointer;
    }

    /**
     * Gets full deadline detail and date from user command
     * @param command user input
     * @return deadline in full detail and date
     */
    public String getDeadlineFull(String[] command) {
        String remaining = "";
        try {
            String detail = this.deadlineDetail(command);
            int pointer = this.deadlineTimeIndex(command);
            for (int j = pointer; j < command.length; j++) {
                if (String.valueOf(command[j]).equals("/")) {
                    remaining += "-";
                } else {
                    remaining += command[j];
                }
                if (j != command.length - 1) {
                    remaining += " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return remaining;
    }

    /**
     * Returns index of the input array where indicates starting time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return the index of starting time.
     * @throws MissingContentException if arr is empty.
     */
    public int getEventStartTimeIndex(String[] arr) throws MissingContentException {
        int startIndex = minSize;
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/from")) {
                startIndex = j + 1;
                break;
            }
        }
        if (startIndex == minSize) {
            throw new MissingContentException();
        }
        return startIndex;
    }

    /**
     * Returns event detail from input array.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return event detail.
     * @throws MissingContentException if arr is empty.
     */
    public String getEventDetail(String[] arr) throws MissingContentException {
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        String detail = "";
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/from")) {
                break;
            }
            detail += arr[j];
            detail += " ";
        }
        assert (detail.length() != minSize) : "Parser could not process event task as its detail is empty";
        return detail;
    }

    /**
     * Returns index of the input array where indicates ending time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @param startIndex starting index where indicates starting time of event.
     * @return the index of ending time.
     */
    public int getEventEndTimeIndex(String[] arr, int startIndex) throws MissingContentException {
        int endIndex = minSize;
        for (int j = startIndex; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/to")) {
                endIndex = j + 1;
                break;
            }
        }
        if (endIndex == minSize) {
            throw new MissingContentException();
        }
        return endIndex;
    }

    /**
     * Returns index of the input array where indicates ending time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @param startIndex starting index where indicates starting time of event.
     * @param endIndex ending index where indicates ending time of event.
     * @return an array consists of event start time and event ent time specifically in String.
     */
    public String[] getEventTime(String[] arr, int startIndex, int endIndex) {
        String[] eventTime = new String[4];
        String start = "";
        String end = "";
        for (int j = startIndex; j < endIndex - 1; j++) {
            if (String.valueOf(arr[j]).equals("/")) {
                start += "-";
            } else {
                start += arr[j];
            }
            if (j != endIndex - secondToEndIndex) {
                start += " ";
            }
        }
        for (int k = endIndex; k < arr.length; k++) {
            if (String.valueOf(arr[k]).equals("/")) {
                end += "-";
            } else {
                end += arr[k];
            }
            if (k != arr.length - 1) {
                end += " ";
            }
        }
        eventTime[0] = Parser.getTime(start);
        eventTime[1] = Parser.getTime(end);
        return eventTime;
    }

    private static String getTime(String s) throws IndexOutOfBoundsException {
        String track = String.valueOf(s.charAt(minSize));
        int tracker = minSize;
        while (!track.equals(" ")) {
            tracker++;
            track = String.valueOf(s.charAt(tracker));
        }
        assert tracker != minSize : "Cannot get event time - missing input from user";
        String date = String.valueOf(s.substring(0, tracker));
        String time = String.valueOf(s.substring(tracker + 1));
        String timeFormatted = "";
        for (int i = 0; i < time.length(); i += 2) {
            timeFormatted += String.valueOf(time.substring(i, i + 2));
            timeFormatted += ":";
        }
        timeFormatted += inSecond;
        return date + "T" + timeFormatted;
    }
}