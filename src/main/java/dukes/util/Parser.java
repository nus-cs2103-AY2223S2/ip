package dukes.util;

import dukes.task.Task;
import dukes.task.ToDo;
import dukes.task.DeadLine;
import dukes.task.Event;

import dukes.command.Command;
import dukes.command.AddCommand;
import dukes.command.DeleteCommand;
import dukes.command.ListCommand;
import dukes.command.ExitCommand;
import dukes.command.MarkCommand;
import dukes.command.FindCommand;

import java.util.Locale;
import java.util.Arrays;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The util class for interpretation of the user command.
 */
public class Parser {

    /**
     * The main util method to interpret the user command.
     *
     * @param command the command to be interpreted.
     * @return the type of command containing the interpreted information.
     * @throws DukeException if undesirable runtime issue happens.
     */
    public Command parse(String command) throws DukeException {
        // make parse not static to avoid "throw exception from static method" issues
        if (command.equals("bye")) {
            return new ExitCommand(true, true, "bye", "");
        } else {
            String[] splits = command.split(" ");
            if (splits.length == 0) {
                throw new DukeException("Sorry, I cannot recognise your input.");
            } else if (splits[0].equals("mark")) {
                // maybe make MarkCommand.execute handle exception?
                return validateMark(command, 0);
            } else if (splits[0].equals("unmark")) {
                return validateMark(command, 1);
            } else if (splits[0].equals("delete")) {
                return validateDelete(command);
            } else if (splits[0].equals("todo")) {
                // these 3 needs a AddCommand
                return validateToDo(command);
            } else if (splits[0].equals("deadline")) {
                return validateDeadLine(command);
            } else if (splits[0].equals("event")) {
                return validateEvent(command);
            } else if (splits[0].equals("list")) {
                return validateList(command, 0);
            } else if (splits[0].equals("search")) {
                return validateList(command, 1);
            } else if (splits[0].equals("find")) {
                return validateFind(command);
            } else {
                throw new DukeException("Sorry, I cannot recognise your input.");
            }
        }
    }

    /**
     * Validate if the time string can be parsed into a valid date.
     *
     * @param timeString the time string to be interpreted.
     * @return the date being parsed.
     * @throws DateTimeParseException if the string is not a valid date, or is not of dd/mm/yyyy format.
     */
    LocalDate validateTime(String timeString) throws DateTimeParseException {
        // Set the time input as dd/mm/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate theDate = LocalDate.parse(timeString, formatter);
        return theDate;
    }

    /**
     * Validate if the command labelled as "todo" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @return a ToDo command containing the interpreted information.
     * @throws DukeException if no content detected after "todo".
     */
    public Command validateToDo(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("OOPS!!! Description of todo not found.");
        } else {
            String taskName = parseHelper(splited, 0, splited.length);
            return new AddCommand(
                    false, true, "add",
                    taskName, "T");
        }
    }

    /**
     * Validate if the command labelled as "deadline" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @return a DeadLine command containing the interpreted information.
     * @throws DukeException if no content detected after "deadline",
     * if no date for deadline is specified,
     * or id the date detected is invalid.
     */
    public Command validateDeadLine(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("OOPS!!! Description of deadline not found.");
        } else if (!hasAllRequiredWord(splited, "/by")) {
            throw new DukeException("Deadline of the task not specified.");
        } else if (splited[splited.length - 1].equals("/by")) {
            throw new DukeException("No valid deadline specified.");
        } else {
            int deadlineSplit = findSplitter(splited, "/by");
            String taskName = parseHelper(splited, 0, deadlineSplit);
            String deadline = parseHelper(splited, deadlineSplit + 1, splited.length);

            try {
                LocalDate theDate = validateTime(deadline);
                return new AddCommand(
                        false, true, "add",
                        taskName, "D", theDate);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter date in the format dd/mm/yyyy");
            }

        }
    }

    /**
     * Validate if the command labelled as "event" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @return an Event command containing the interpreted information.
     * @throws DukeException if no content detected after "event",
     * if no date for start date or end date is specified,
     * or id the date detected is invalid.
     */
    public Command validateEvent(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("OOPS!!! Description of event not found.");
        } else if (!hasAllRequiredWord(splited, "/from", "/to")) {
            throw new DukeException("Event time not specified completely.");
        } else if (splited[splited.length - 1].equals("/to") ||
                Arrays.asList(splited).indexOf("/from") ==
                        Arrays.asList(splited).indexOf("/to") - 1) {
            throw new DukeException("No valid event time specified.");
        } else {
            int fromIndex = findSplitter(splited, "/from");
            int toIndex = findSplitter(splited, "/to");
            String taskName = parseHelper(splited, 0, fromIndex);
            String startTime = parseHelper(splited, fromIndex + 1, toIndex);
            String endTime = parseHelper(splited, toIndex + 1, splited.length);

            try {
                LocalDate startDate = validateTime(startTime);
                LocalDate endDate = validateTime(endTime);
                return new AddCommand(
                        false, true, "add",
                        taskName, "E", startDate, endDate
                );
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter date in the format dd/mm/yyyy");
            }
        }
    }

    /**
     * Validate if the command labelled as "mark" or "unmark" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @param action 0 for mark, 1 for unmark.
     * @return an Mark command containing the interpreted information.
     * @throws DukeException if no content detected after "mark"/"unmark".
     */
    public Command validateMark(String command, int action) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("You have not specified the index of the marking.");
        } else {
            int index = Integer.parseInt(splited[1]);
            return new MarkCommand(false, true, "mark",
                    Integer.toString(index), action);
        }
    }

    /**
     * Validate if the command labelled as "delete" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @return a Delete command containing the interpreted information.
     * @throws DukeException if no content detected after "mark"/"unmark".
     */
    public Command validateDelete(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("You have not specified the index of the marking.");
        } else {
            int index = Integer.parseInt(splited[1]);
            return new DeleteCommand(false, true, "mark",
                    Integer.toString(index));
        }
    }

    /**
     * Validate if the command labelled as "list" or "search" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @param action 0 for list, 1 for search.
     * @return a List command containing the interpreted information.
     * @throws DukeException if no content detected after "list"/"search".
     */
    public Command validateList(String command, int action) throws DukeException {
        if (action == 0) {
            // just list, do nothing
            return new ListCommand(false, true,
                    "list","");
        } else { // search
            String[] splited = command.split(" ");
            if (hasNoContent(splited)) {
                throw new DukeException("OOPS! Target date not found.");
            }
            try {
                LocalDate theDate = validateTime(splited[1]);
                return new ListCommand(false, true,
                        "list", "", theDate);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter date in the format dd/mm/yyyy");
            }
        }
    }

    /**
     * Validate if the command labelled as "find" indeed contains a valid command.
     *
     * @param command the command string to be interpreted.
     * @return a Find command containing the interpreted information.
     * @throws DukeException if no content detected after "find".
     */
    public Command validateFind(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (hasNoContent(splited)) {
            throw new DukeException("You have not specified the search keyword.");
        } else {
            // allows for searching a pattern
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < splited.length; i++) {
                sb.append(splited[i]).append(" ");
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length()-1);
            }
            return new FindCommand(false, true,
                    "find", sb.toString());
        }
    }

    /**
     * Static method that interpret hard disk line into task object.
     *
     * @param fileLine the line read from the hard disk file.
     * @return a task interpreted from the file line.
     */
    public static Task fetchTask(String fileLine) {
        // System.out.println(fileLine);
        // Still get issues -- must be able to parse the date time!!
        Task newTask;
        // DO NOT USE | as separator!! You need \\| for escape. Better use @
        String[] temp = fileLine.split("@");
        // System.out.println(Arrays.toString(temp));
        boolean isDone = (temp[1].equals("0")) ? false : true;
        if (temp[0].equals("T")) {
            newTask = new ToDo(temp[2], isDone);
        } else if (temp[0].equals("D")) {
            LocalDate deadline = getLocalDate(temp, 3);
            newTask = new DeadLine(temp[2], isDone, deadline);
        } else {
            LocalDate start = getLocalDate(temp, 3);
            LocalDate end = getLocalDate(temp, 4);
            newTask = new Event(temp[2], isDone, start, end);
        }
        return newTask;
    }

    /**
     * Private util method to check if command has no content
     *
     * @param splited the command
     * @return if the command is invalid
     */
    private static boolean hasNoContent(String[] splited) {
        return splited.length < 2;
    }

    /**
     * Private util method to ensure the command has all the required words
     * @param splited the command
     * @param values all the required words
     *
     * @return
     */
    private static boolean hasAllRequiredWord(String[] splited, String... values) {
        boolean hasAll = true;
        for (String s : values) {
            if (!Arrays.asList((splited)).contains(s)) {
                hasAll = false;
                break;
            }
        }
        return hasAll;
    }

    /**
     * Helper method to extract the string content form the splitted string array
     *
     * @param splited the splitted array of strings
     * @param start the start index
     * @param end the end index
     * @return the string that supposed to be extracted
     */
    private static String parseHelper(String[] splited, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splited.length; i++) {
            if (i >= start && i < end) {
                sb.append(splited[i]).append(" ");
                break;
            }
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    /**
     * Find the splitting index in the array by the given pattern
     *
     * @param splitted the splitted array of strings
     * @param identifier the pattern to be matched
     * @return the split index between different sections of command
     */
    private static int findSplitter(String[] splitted, String identifier) {
        int split = -1;
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals(identifier)) {
                split = i;
            }
        }
        return split;
    }

    /**
     * Util method to extract local date
     *
     * @param temp the string array containing the date string
     * @param index the index of the string to be parsed
     * @return the localdate represented by the string
     */
    private static LocalDate getLocalDate(String[] temp, int index) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("en"));
        LocalDate deadline = LocalDate.parse(temp[index], formatter);
        return deadline;
    }
}
