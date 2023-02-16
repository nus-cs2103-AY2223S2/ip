package duke.command;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.exception.EmptyTaskListException;
import duke.exception.IndexNotNumberException;
import duke.exception.InvalidEventDateTimeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingContentException;
import duke.task.Event;

/**
 * Makes sense of what users say
 */
public class Parser {
    private static final String inSecond = "00";
    private static final int secondToEndIndex = 2;
    private static final int minSize = 0;
    private static String lastCommand = "";
    private static String lastCommandDetail = "";
    private static TaskList lastTaskList = new TaskList();
    private static final Ui ui = new Ui();

    /**
     * Update last command
     * @param newCommand type of last command
     */
    public static void updateLastCommand(String newCommand) {
        lastCommand = newCommand;
    }

    /**
     * Update last command detail
     * @param newDetail detail of last command in String
     */
    public static void updateLastCommandDetail(String newDetail) {
        lastCommandDetail = newDetail;
    }

    /**
     * Update last task list (update to before most recent command is applied on Duke)
     * @param newTaskList what the task list was before the most recent command is applied
     */
    public static void updateLastTaskList(TaskList newTaskList) {
        lastTaskList = newTaskList;
    }

    /**
     * Return the last task list - the task list before last command is applied
     * @return last task list
     */
    private static TaskList getLastTaskList() {
        return lastTaskList;
    }

    public static String getLastCommand() {
        return lastCommand;
    }

    /**
     * Undo response to user input
     * And update task list correspondingly
     * @param taskList original task list (after the most recent command is done)
     * @return Duke's response
     */
    public static String undo(TaskList taskList) {
        String commandType = Parser.parseLastCommandType(lastCommand);
        if (commandType.equals("clear") || commandType.equals("undo")) {
            TaskList originalTaskList = new TaskList(Arrays.copyOf(taskList.readTaskList(), 100));
            taskList.overwrite(lastTaskList);
            Parser.updateLastTaskList(originalTaskList);
            Parser.updateLastCommand("undo");
            return ui.saysUnDo(commandType);
        } else if (!commandType.equals("last command is unable to undo")) {
            TaskList originalTaskList = new TaskList(Arrays.copyOf(taskList.readTaskList(), 100));
            taskList.overwrite(lastTaskList);
            Parser.updateLastTaskList(originalTaskList);
            Parser.updateLastCommand("undo");
            return ui.saysUnDo(commandType, lastCommandDetail);
        }
        return ui.saysUnableToUndo();
    }

    /**
     * Returns command type of the last command if it is undo-able
     * @param recentCommand most recent valid command
     * @return type of recent command if it's undo-able
     */
    public static String parseLastCommandType(String recentCommand) {
        if (recentCommand.startsWith("unmark ") || recentCommand.startsWith("mark ")
            || recentCommand.startsWith("delete ")) {
            String[] commandInArray = recentCommand.split(" ");
            return commandInArray[0];
        } else if (recentCommand.startsWith("todo") || recentCommand.startsWith("deadline")
                || recentCommand.startsWith("event")) {
            return "add";
        } else if (recentCommand.startsWith("clear") || recentCommand.equals("undo")) {
            return recentCommand;
        }
        return "last command is unable to undo";
    }

    /**
     * Parse and do deleteAll command
     * @param listOfAction original task list
     * @param command input command from user
     * @return message from Duke
     */
    public static String deleteAll(TaskList listOfAction, String[] command) {
        try {
            if (command[1].equalsIgnoreCase("all")) {
                listOfAction.clear();
                Parser.updateLastCommand("clear");
                return (Ui.saysDeleteAllMessage());
            }
        } catch (EmptyTaskListException e) {
            return e.getMessage();
        }
        return new Ui().showUnknownError();
    }

    /**
     * Makes sense of adding to do command from users
     * @param arr array of original array
     * @return full string command for users
     */
    public static String parseToDo(String[] arr) throws MissingContentException {
        String remaining;
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
     * @return index of task list
     * @throws MissingContentException if command does not specify index
     * @throws InvalidIndexException if task list does not have such index
     */
    public static int getTaskIndex(TaskList listOfAction, String[] command) throws MissingContentException,
            InvalidIndexException, IndexNotNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new IndexNotNumberException();
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
    public String getDeadlineDetail(String[] arr) throws MissingContentException {
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
    private int getDeadlineTimeIndex(String[] arr) throws MissingContentException {
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
            int pointer = this.getDeadlineTimeIndex(command);
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
    private int getEventStartTimeIndex(String[] arr) throws MissingContentException {
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
    private String getEventDetail(String[] arr) throws MissingContentException {
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
    private int getEventEndTimeIndex(String[] arr, int startIndex) throws MissingContentException {
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
    private String[] getEventTime(String[] arr, int startIndex, int endIndex) {
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
        String date = (s.substring(0, tracker));
        String time = (s.substring(tracker + 1));
        String timeFormatted = "";
        for (int i = 0; i < time.length(); i += 2) {
            timeFormatted += (time.substring(i, i + 2));
            timeFormatted += ":";
        }
        timeFormatted += inSecond;
        return date + "T" + timeFormatted;
    }

    /**
     * Returns event in correct format based on user's input
     * @param command input from user
     * @return event in correct format
     * @throws MissingContentException if user did not provide event detail
     * @throws InvalidEventDateTimeException if event is in incorrect date time format
     */
    public static Event getEventFull(String[] command) throws MissingContentException, InvalidEventDateTimeException {
        int startIndex = new Parser()
                .getEventStartTimeIndex(command);
        int endIndex = new Parser()
                .getEventEndTimeIndex(command, startIndex);
        String detail = new Parser()
                .getEventDetail(command);
        String start = (new Parser()
                .getEventTime(command, startIndex, endIndex))[0];
        String end = (new Parser()
                .getEventTime(command, startIndex, endIndex))[1];
        return new Event("event", detail, start, end);
    }
}
