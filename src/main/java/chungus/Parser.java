package chungus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the logic needed to parse user entered commands and
 * dispatch them to appropriate handlers.
 */
class Parser {
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");

    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses a command, and selects a suitable handler. This is a pure function.
     * The handler returned is not safe to be ran multiple times.
     * 
     * @param cmd The raw command to parse.
     * @return An appropriate handler for the command.
     * @throws ChungusException When the expected command format is wrong.
     */
    public static Handler parse(String cmd) {
        String[] args = cmd.split("\\s+");
        switch (args[0]) {
            case "bye": {
                return Handlers.bye();
            }
            case "list": {
                return Handlers.list();
            }
            case "todo": {
                String[] pair = cmd.split("\\s+", 2);
                if (pair.length < 2) {
                    throw new ChungusException("Description of todo cannot be empty.");
                }
                return Handlers.todo(pair[1]);
            }
            case "deadline": {
                Matcher matcher = DEADLINE_PATTERN.matcher(cmd);
                if (!matcher.find()) {
                    throw new ChungusException(
                            "Bad format for creating deadline task. Must be of the form deadline <task> /by <datetime>.");
                }

                String desc = matcher.group(1);
                LocalDateTime deadline = parseDateTimeInput(matcher.group(2));

                return Handlers.deadline(desc, deadline);
            }
            case "event": {
                Matcher matcher = EVENT_PATTERN.matcher(cmd);
                if (!matcher.find()) {
                    throw new ChungusException(
                            "Bad format for creating event. Must be of the form event <name> /from <datetime> /to <datetime>.");
                }

                String desc = matcher.group(1);
                LocalDateTime from = parseDateTimeInput(matcher.group(2));
                LocalDateTime to = parseDateTimeInput(matcher.group(3));

                return Handlers.event(desc, from, to);
            }
            case "mark": {
                int idx = getTaskNumberArg(args[1]) - 1;
                return Handlers.mark(idx);
            }
            case "unmark": {
                int idx = getTaskNumberArg(args[1]) - 1;
                return Handlers.unmark(idx);
            }
            case "find": {
                String[] pair = cmd.split("\\s+", 2);
                String searchTerm = "";
                if (pair.length == 2) {
                    searchTerm = pair[1];
                }
                return Handlers.find(searchTerm);
            }
            default: {
                return Handlers.unknown(args[0]);
            }
        }
    }

    private static LocalDateTime parseDateTimeInput(String s) {
        try {
            return LocalDateTime.parse(s, DATETIME_FMT);
        } catch (DateTimeParseException e) {
            throw new ChungusException(String.format("Bad datetime format \"%s\": expected dd/MM/yyyy HHmm", s), e);
        }
    }

    private static int getTaskNumberArg(String s) {
        String[] xs = s.split("\\s+");
        int num = Integer.parseInt(xs[xs.length - 1]);
        return num;
    }
}
