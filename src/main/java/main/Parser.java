package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.Bye;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventsCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnMarkCommand;
import exception.DukeException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

/**
 * Parsers the user input
 */
public class Parser {
    private static final int INPUT_LENGTH_VALIDATE = 2;
    private static final int INPUT_LENGTH_WITH_DATE = 3;
    private static final int INPUT_LENGTH_KEYWORD = 2;

    public Parser() {
    }

    /**
     * Parses the user input
     * 
     * @param input the user's input from the command line
     * @return A command corresponding to the user's input i.e if user inputs bye it
     *         will return a bye command.
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        input = input.trim();
        String[] splitInput = input.split(" ");
        String command = splitInput[0];

        if (command.equals("bye")) {
            return new Bye();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("mark")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to mark",
                    INPUT_LENGTH_VALIDATE);
            if (!isNumeric(splitInput[1])) {
                throw new DukeException("input must int");
            }
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new MarkCommand(inputIndex);
        } else if (command.equals("unmark")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to unmark",
                    INPUT_LENGTH_VALIDATE);
            if (!isNumeric(splitInput[1])) {
                throw new DukeException("input must int");
            }
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new UnMarkCommand(inputIndex);
        } else if (command.equals("delete")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to delete",
                    INPUT_LENGTH_VALIDATE);
            if (!isNumeric(splitInput[1])) {
                throw new DukeException("input must int");
            }
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new DeleteCommand(inputIndex);
        } else if (command.equals("todo")) {
            int startIndex = command.length();
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a todo cannot be empty.",
                    INPUT_LENGTH_VALIDATE);
            String taskName = input.substring(startIndex);
            Task newTask = new ToDos(taskName);
            return new TodoCommand(newTask);
        } else if (command.equals("deadline")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a deadline cannot be empty.",
                    INPUT_LENGTH_WITH_DATE);
            int locationOfBy = 0;
            for (int i = 0; i < splitInput.length; i++) {
                if (splitInput[i].equals("/by")) {
                    locationOfBy = i;
                }
            }
            StringBuilder inputAfterBy = new StringBuilder();
            for (int i = locationOfBy + 1; i < splitInput.length; i++) {
                inputAfterBy.append(splitInput[i] + " ");
            }
            String finalInput = inputAfterBy.toString().trim();
            this.checkValidDateFormat(finalInput, "☹ OOPS!!! The deadline cannot be empty.");
            LocalDateTime date = parseDate(finalInput);
            int startIndex = command.length();
            String taskName = input.substring(startIndex).split("/")[0];
            Task newTask = new Deadlines(taskName, date);
            return new DeadlineCommand(newTask);
        } else if (command.equals("event")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a event cannot be empty.",
                    INPUT_LENGTH_WITH_DATE);
            int locationOfFrom = 0;
            int locationOfTo = 0;
            for (int i = 0; i < splitInput.length; i++) {
                if (splitInput[i].equals("/from")) {
                    locationOfFrom = i;
                }
                if (splitInput[i].equals("/to")) {
                    locationOfTo = i;
                }
            }
            StringBuilder inputAfterFrom = new StringBuilder();
            StringBuilder inputAfterTo = new StringBuilder();
            for (int i = locationOfFrom + 1; i < locationOfTo; i++) {
                inputAfterFrom.append(splitInput[i] + " ");
            }
            for (int i = locationOfTo + 1; i < splitInput.length; i++) {
                inputAfterTo.append(splitInput[i] + " ");
            }
            String finalInput = inputAfterFrom.toString().trim();
            this.checkValidDateFormat(finalInput, "☹ OOPS!!! The even time cannot be empty.");
            this.checkValidDateFormat(finalInput, "☹ OOPS!!! The deadline cannot be empty.");
            LocalDateTime dateFrom = parseDate(finalInput);
            LocalDateTime dateTo = parseDate(finalInput);
            int startIndex = command.length();
            String taskName = input.substring(startIndex).split("/")[0];
            Task newTask = new Events(taskName, dateFrom, dateTo);
            return new EventsCommand(newTask);
        } else if (command.equals("find")) {
            checkKeywordFormat(splitInput);
            return new FindCommand(splitInput[1]);
        } else if (command.equals("help")) {
            if (splitInput.length > 1) {
                return new HelpCommand(splitInput[1]);
            }
            return new HelpCommand();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void validateInputNotEmpty(String[] splitInput, String message, int noOfInputs) throws DukeException {
        if (splitInput.length < INPUT_LENGTH_VALIDATE) {
            throw new DukeException(message);
        }
    }

    private void checkValidDateFormat(String input, String message) throws DukeException {
        String[] splitInput = input.split("/");
        if (splitInput.length != 3 || !isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            throw new DukeException("Not valid date format. Format must be dd/mm/yyyy HHmm");
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (!(yearAndTime.length == 2 && isNumeric(yearAndTime[0]) && isNumeric(yearAndTime[1]))) {
            throw new DukeException("Not a valid date format Format must be dd/mm/yyyy HHmm");
        }
    }

    private void checkKeywordFormat(String[] splitInput) throws DukeException {
        if (splitInput.length > INPUT_LENGTH_KEYWORD) {
            throw new DukeException("Cannot have multiple keywords sorry :(.");
        }
    }

    /**
     * Check whether the string is a real number.
     * 
     * @param string any string
     * @return true if it's a number false otherwise
     */
    public static boolean isNumeric(String string) {
        try {
            int value = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    /**
     * Parses the string date into an actual LocalDateTime object
     * 
     * @param date the date in String format (d/M/yyyy HHmm)
     * @return LocalDateTime corresponding to the string format
     * @throws DukeException
     */
    public static LocalDateTime parseDate(String date) throws DukeException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(date, dateFormatter);
        } catch (Exception e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    /**
     * Parses the string date from the storage file into an actual LocalDateTime
     * object
     * 
     * @param date the date from the storage
     * @return LocalDateTime corresponding to the string format
     * @throws DukeException
     */
    public static LocalDateTime parseDateStorage(String date) throws DukeException {
        try {
            return LocalDateTime.parse(date);
        } catch (Exception e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }
}
