package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TagCommand;
import command.UnMarkCommand;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Todo;


/**
 * The Parser class is responsible for parsing the user's input and returning the appropriate command.
 *
 */
public class Parser {
    private String input;

    /**
     * Constructor for the Parser class.
     *
     * @param input The user's input to be parsed.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Parses the user input and returns the corresponding command object.
     * @return The command object corresponding to the user input.
     * @throws DukeException If the input is not recognized or is invalid.
     */
    public Command parseCommand() throws DukeException {
        input = input.trim();
        String[] splitString = input.split(" ");
        String keyword = splitString[0];

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (keyword.contains("unmark")) {
            int index = Integer.parseInt(splitString[1]) - 1;
            return new UnMarkCommand(index);
        } else if (keyword.contains("mark")) {
            int index = Integer.parseInt(splitString[1]) - 1;
            return new MarkCommand(index);
        } else if (keyword.contains("delete")) {
            int zeroIndex = Integer.parseInt(splitString[1]) - 1;
            return new DeleteCommand(zeroIndex);
        } else if (keyword.equals("todo")) {
            int startIndex = keyword.length();
            if (startIndex >= input.length()) {
                throw new DukeException("Fill in the description of a todo.");
            }
            String taskFullDetails = input.substring(startIndex);
            Todo todoTask = new Todo(taskFullDetails);
            return new AddCommand(todoTask);
        } else if (keyword.equals("deadline")) {
            int startIndex = keyword.length();
            if (startIndex >= input.length()) {
                throw new DukeException("Fill in the description of a deadline.");
            }
            int detailIndex = input.lastIndexOf("deadline");
            String taskFullDetails = input.substring(detailIndex);
            String[] splitDetails = taskFullDetails.split("/by ");
            if (splitDetails.length < 2) {
                throw new DukeException("Please specify the deadline.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(splitDetails[1].trim(), formatter);
            Deadline deadlineTask = new Deadline(splitDetails[0].trim(), dateTime);
            return new AddCommand(deadlineTask);
        } else if (keyword.equals("event")) {
            int startIndex = keyword.length();
            if (startIndex >= input.length()) {
                throw new DukeException("Fill in the description for this event .");
            }
            int detailIndex = input.lastIndexOf("event");
            String taskFullDetails = input.substring(detailIndex);
            String[] splitDescriptionAndDuration = taskFullDetails.split("/from");
            String[] splitStartAndEnd = splitDescriptionAndDuration[1].split("/to");
            if (splitDescriptionAndDuration.length < 2) {
                throw new DukeException("Please specify the start and end.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(splitStartAndEnd[0].trim(), formatter);
            LocalDate endDate = LocalDate.parse(splitStartAndEnd[1].trim(), formatter);
            Event eventTask = new Event(splitDescriptionAndDuration[0], startDate, endDate);
            return new AddCommand(eventTask);
        } else if (keyword.equals("find")) {
            return new FindCommand(splitString[1]);
        } else if (keyword.equals("tag")) {
            int zeroIndex = Integer.parseInt(splitString[1]) - 1;
            String tagName = splitString[2];
            return new TagCommand(zeroIndex, tagName);
        } else {
            throw new DukeException("What is this? Please try again!");
        }
    }

    /**
     * This method parses a date from a string.
     *
     * @param date The string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DukeException If the string cannot be parsed as a date.
     */

    public static LocalDate parseFile(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("An error occurred while parsing date: " + dtpe.getMessage());
        }
    }

}
