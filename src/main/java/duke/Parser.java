package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Parser {
    private static final Set<String> DELIMITERS = new HashSet<>(Arrays.asList("/from", "/to", "/by"));

    public static Command parse(String fullCommand) throws DukeException {
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
                if (!args.hasNext()) {
                    throw new DukeException("WAKANDEYO!!! >:(");
                }

                int idx;
                try {
                    idx = Integer.parseInt(args.next());
                } catch (NumberFormatException e) {
                    throw new DukeException("WAKANDEYO!!! >:(");
                }

                return new MarkCommand(cmdWord.equals("mark"), idx);
            case "todo":
                String name = copyUntilDelimiter(args);
                return new AddCommand(new Todo(name));
            case "deadline":
                name = copyUntilDelimiter(args, "/by");
                Date by;
                try {
                    by = parseDate(copyUntilDelimiter(args));
                } catch (IllegalArgumentException e) {
                    throw new DukeException("WAKANDEYO!!!! >:(");
                }

                return new AddCommand(new Deadline(name, by));
            case "event":
                name = copyUntilDelimiter(args, "/from");
                Date from;
                Date to;

                try {
                    from = parseDate(copyUntilDelimiter(args, "/to"));
                    to = parseDate(copyUntilDelimiter(args));
                } catch (IllegalArgumentException e) {
                    throw new DukeException();
                }

                return new AddCommand(new Event(name, from, to));
            case "delete":
                if (!args.hasNext()) {
                    throw new DukeException("WAKANDEYO!!! >:(");
                }

                try {
                    idx = Integer.parseInt(args.next());
                } catch (NumberFormatException e) {
                    throw new DukeException("WAKANDEYO!!! >:(");
                }
                return new DeleteCommand(idx);
            default:
                throw new DukeException();
                // printErrorMessage(ErrorEnum.UNKNOWN_INPUT);
        }
    }

    private static String copyUntilDelimiter(Iterator<String> args, String delimiter) throws DukeException {
        if (!DELIMITERS.contains(delimiter)) {
            throw new DukeException();
        }

        List<String> words = new ArrayList<>();
        String curWord = "";
        while (args.hasNext()) {
            curWord = args.next();
            if (curWord.equals(delimiter)) {
                break;
            }
            words.add(curWord);
        }
        return String.join(" ", words);
    }

    private static String copyUntilDelimiter(Iterator<String> args) {
        List<String> words = new ArrayList<>();
        String curWord = "";
        while (args.hasNext()) {
            curWord = args.next();
            words.add(curWord);
        }
        return String.join(" ", words);
    }

    private static boolean isDateTime(String args) {
        try {
            LocalDateTime.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isDate(String args) {
        try {
            LocalDate.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Date parseDate(String arg) throws IllegalArgumentException {
        if (isDateTime(arg)) {
            return new Date(LocalDateTime.parse(arg, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else if (isDate(arg)) {
            return new Date(LocalDate.parse(arg, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
