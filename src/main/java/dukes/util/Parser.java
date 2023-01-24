package dukes.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;
import dukes.task.*;
import dukes.command.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    // also mainly use static method
    // but need to deal with a lot of exceptions

    public Command parse(String command) throws DukeException {
        // make parse not static to avoid "throw exception from static method" issues
        if (command.equals("bye")) {
            return new ExitCommand(true, true, "bye", "");
        } else {
            String[] splited = command.split(" ");
            if (splited.length == 0) {
                throw new DukeException("Sorry, I cannot recognise your input.");
            } else if (splited[0].equals("mark")) {
                // maybe make MarkCommand.execute handle exception?
                return validateMark(command, 0);
            } else if (splited[0].equals("unmark")) {
                return validateMark(command, 1);
            } else if (splited[0].equals("delete")) {
                return validateDelete(command);
            } else if (splited[0].equals("todo")) {
                // these 3 needs a AddCommand
                return validateToDo(command);
            } else if (splited[0].equals("deadline")) {
                return validateDeadLine(command);
            } else if (splited[0].equals("event")) {
                return validateEvent(command);
            } else if (splited[0].equals("list")) {
                return validateList(command, 0);
            } else if (splited[0].equals("search")) {
                return validateList(command, 1);
            } else if (splited[0].equals("find")) {
                return validateFind(command);
            } else {
                throw new DukeException("Sorry, I cannot recognise your input.");
            }
        }
    }

    LocalDate validateTime(String timeString) throws DateTimeParseException {
        // Set the time input as dd/mm/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate theDate = LocalDate.parse(timeString, formatter);
        return theDate;
    }

    public Command validateToDo(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
            throw new DukeException("OOPS!!! Description of todo not found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < splited.length; i++) {
                sb.append(splited[i]).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            String taskName = sb.toString();
            return new AddCommand(
                    false, true, "add",
                    taskName, "T");
        }
    }

    public Command validateDeadLine(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
            throw new DukeException("OOPS!!! Description of deadline not found.");
        } else if (!Arrays.asList(splited).contains("/by")) {
            throw new DukeException("Deadline of the task not specified.");
        } else if (splited[splited.length - 1].equals("/by")) {
            throw new DukeException("No valid deadline specified.");
        } else {
            // Maybe move the part in handleDeadLine up
            StringBuilder sb = new StringBuilder();
            StringBuilder time = new StringBuilder();
            boolean isTime = false;
            for (int i = 1; i < splited.length; i++) {
                if (splited[i].equals("/by")) {
                    isTime = true;
                } else if (!isTime) {
                    sb.append(splited[i]).append(" ");
                } else {
                    time.append(splited[i]).append(" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            time.deleteCharAt(time.length()-1);
            String taskName = sb.toString();
            String deadline = time.toString();

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

    public Command validateEvent(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
            throw new DukeException("OOPS!!! Description of event not found.");
        } else if (!Arrays.asList(splited).contains("/from") ||
                !Arrays.asList(splited).contains("/to")) {
            throw new DukeException("Event time not specified completely.");
        } else if (splited[splited.length - 1].equals("/to") ||
                Arrays.asList(splited).indexOf("/from") ==
                        Arrays.asList(splited).indexOf("/to") - 1) {
            throw new DukeException("No valid event time specified.");
        } else {
            StringBuilder sb = new StringBuilder();
            StringBuilder start = new StringBuilder();
            StringBuilder end = new StringBuilder();
            boolean isStart = false;
            boolean isEnd = false;
            for (int i = 1; i < splited.length; i++) {
                if (splited[i].equals("/from")) {
                    isStart = true;
                } else if (splited[i].equals("/to")) {
                    isStart = false;
                    isEnd = true;
                } else if (!isStart && !isEnd) {
                    sb.append(splited[i]).append(" ");
                } else if (isStart) {
                    start.append(splited[i]).append(" ");
                } else {
                    end.append(splited[i]).append(" ");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            start.deleteCharAt(start.length() - 1);
            end.deleteCharAt(end.length() - 1);
            String taskName = sb.toString();
            String startTime = start.toString();
            String endTime = end.toString();

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

    public Command validateMark(String command, int action) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
            throw new DukeException("You have not specified the index of the marking.");
        } else {
            int index = Integer.parseInt(splited[1]);
            return new MarkCommand(false, true, "mark",
                    Integer.toString(index), action);
        }
    }

    public Command validateDelete(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
            throw new DukeException("You have not specified the index of the marking.");
        } else {
            int index = Integer.parseInt(splited[1]);
            return new DeleteCommand(false, true, "mark",
                    Integer.toString(index));
        }
    }

    public Command validateList(String command, int action) throws DukeException {
        if (action == 0) {
            // just list, do nothing
            return new ListCommand(false, true,
                    "list","");
        } else { // search
            String[] splited = command.split(" ");
            if (splited.length < 2) {
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

    public Command validateFind(String command) throws DukeException {
        String[] splited = command.split(" ");
        if (splited.length < 2) {
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
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("en"));
            LocalDate deadline = LocalDate.parse(temp[3], formatter);
            newTask = new DeadLine(temp[2], isDone, deadline);
        } else {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("en"));
            LocalDate start = LocalDate.parse(temp[3], formatter);
            LocalDate end = LocalDate.parse(temp[4], formatter);
            newTask = new Event(temp[2], isDone, start, end);
        }
        return newTask;
    }
}
