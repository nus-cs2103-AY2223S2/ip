package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingIndexException;
import duke.exceptions.MissingKeywordException;
import duke.exceptions.UnknownCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class containing methods to parse a Duke command.
 */
public class Parser {
    private static final Set<String> DELIMITERS = new HashSet<>(Arrays.asList("/from", "/to", "/by"));

    /**
     * Parses a String representing a Duke command.
     * @param fullCommand String representing a Duke command
     * @return Command object if it was successfully parsed
     * @throws DukeException If user has the file delimiter | in the input or if an invalid command was supplied
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.contains("|")) {
            throw new DukeException("Neeeee fuzakenjaNEYOO\nDon't use the | character da yo!!");
        }

        Iterator<String> args = Arrays.stream(fullCommand.split(" ")).iterator();
        if (!args.hasNext()) {
            return Command.none();
        }

        String cmdWord = args.next();
        switch (cmdWord) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
                return parseMark(args, cmdWord);
            case "todo":
                return parseTodo(args);
            case "deadline":
                return parseDeadline(args);
            case "event":
                return parseEvent(args);
            case "delete":
                return parseDelete(args);
            case "find":
                return parseFind(args);
            default:
                throw new UnknownCommandException();
        }
    }

    /**
     * Parses the "mark" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @param cmdWord The name of the command; determines if it is "mark" or "unmark"
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseMark(Iterator<String> args, String cmdWord) throws DukeException {
        if (!args.hasNext()) {
            throw new MissingIndexException();
        }

        int idx;
        try {
            idx = Integer.parseInt(args.next());
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        return new MarkCommand(cmdWord.equals("mark"), idx);
    }

    /**
     * Parses the "todo" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseTodo(Iterator<String> args) throws DukeException {
        String name = copyUntilDelimiter(args);
        if (name.length() == 0) {
            throw new EmptyBodyException();
        }
        return new AddCommand(new Todo(name));
    }

    /**
     * Parses the "deadline" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseDeadline(Iterator<String> args) throws DukeException {
        String name = copyUntilDelimiter(args, "/by");
        if (name.length() == 0) {
            throw new EmptyBodyException();
        }
        Date by = parseDate(copyUntilDelimiter(args));;
        return new AddCommand(new Deadline(name, by));
    }

    /**
     * Parses the "event" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseEvent(Iterator<String> args) throws DukeException {
        String name = copyUntilDelimiter(args, "/from");
        if (name.length() == 0) {
            throw new EmptyBodyException();
        }
        Date from = parseDate(copyUntilDelimiter(args, "/to"));
        Date to = parseDate(copyUntilDelimiter(args));

        return new AddCommand(new Event(name, from, to));
    }

    /**
     * Parses the "delete" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseDelete(Iterator<String> args) throws DukeException {
        if (!args.hasNext()) {
            throw new MissingIndexException();
        }

        int idx;
        try {
            idx = Integer.parseInt(args.next());
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
        return new DeleteCommand(idx);
    }

    /**
     * Parses the "find" command and returns the corresponding Command object.
     *
     * @param args Tokens of the command as strings
     * @return The parsed Command object
     * @throws DukeException if the command is unsuccessfully parsed.
     */
    private static Command parseFind(Iterator<String> args) throws DukeException {
        if (!args.hasNext()) {
            throw new MissingKeywordException();
        }

        String keyword = args.next();
        return new FindCommand(keyword);
    }

    /**
     * Creates a space-separated String from an Iterator of Strings of all elements that come before a delimiter.
     * e.g. For the Iterator {"1", "2", "3", "4"} and delimiter "3", the function returns "1 2"
     *
     * @param args Iterator on a List of Strings
     * @param delimiter Delimiter to stop copying before
     * @return String object containing space-separated values
     * @throws DukeException If supplied delimiter isn't in the available list of delimiters
     */
    private static String copyUntilDelimiter(Iterator<String> args, String delimiter) throws DukeException {
        if (!DELIMITERS.contains(delimiter)) {
            throw new DukeException();
        }

        List<String> words = new ArrayList<>();
        String curWord;
        while (args.hasNext()) {
            curWord = args.next();
            if (curWord.equals(delimiter)) {
                break;
            }
            words.add(curWord);
        }
        return String.join(" ", words);
    }

    /**
     * Creates a space-separated String from an Iterator of Strings which contains all
     * Strings after the Iterators current position.
     *
     * @param args Iterator on a list of Strings
     * @return String object containing space-separated values
     */
    private static String copyUntilDelimiter(Iterator<String> args) {
        List<String> words = new ArrayList<>();
        String curWord;
        while (args.hasNext()) {
            curWord = args.next();
            words.add(curWord);
        }
        return String.join(" ", words);
    }

    /**
     * Determines if a String is parsable as a LocalDateTime object.
     *
     * @param args String maybe containing a LocalDateTime
     * @return True if the String is parsable as a LocalDateTime object and false otherwise.
     */
    private static boolean isDateTime(String args) {
        try {
            LocalDateTime.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Determines if a String is parsable as a LocalDate object.
     *
     * @param args String maybe containing a LocalDate
     * @return True if the String is parsable as a LocalDateTime object and false otherwise.
     */
    private static boolean isDate(String args) {
        try {
            LocalDate.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses a String maybe containing a LocalDate or LocalDateTime.
     * The string is parsable as a LocalDate if it is of the format yyyy-MM-dd.
     * The string is parsable as a LocalDateTime if it is of the format yyyy-MM-dd HH:mm.
     *
     * @param arg String maybe containing a LocalDate or LocalDateTime.
     * @return Date object if successfully parsed.
     * @throws InvalidDateException If supplied String cannot be parsed according to the specifications above.
     */
    public static Date parseDate(String arg) throws InvalidDateException {
        if (isDateTime(arg)) {
            return new Date(LocalDateTime.parse(arg, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else if (isDate(arg)) {
            return new Date(LocalDate.parse(arg, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } else {
            throw new InvalidDateException();
        }
    }
}
