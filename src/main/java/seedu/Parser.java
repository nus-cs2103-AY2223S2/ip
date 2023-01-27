package seedu;

import java.time.format.DateTimeParseException;

public class Parser {
    private String parseTask(String input) {
        return input.split(" ", 2)[1];
    }

    private String parseDescription(String input) {
        return parseTask(input).split(" /by ")[0];
    }

    private String parseDate(String input) {
        return parseTask(input).split(" /by ")[1];
    }

    private ToDo parseToDoTask(String input) throws JamesException {
        if (input.split(" ").length == 1) {
            throw new JamesException("The description of a todo cannot be empty.");
        }
        String description = parseTask(input);
        return new ToDo(description);
    }

    private Deadline parseDeadlineTask(String input) throws JamesException {
        if (input.split(" ").length == 1) {
            throw new JamesException("The description of a deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new JamesException("Please use the format: deadline <description> /by <time>");
        }
        String description = parseDescription(input);
        String by = parseDate(input);
        try {
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new JamesException("Please use time format: d/MM/yyyy HHmm");
        }
    }

    private Event parseEventTask(String input) throws JamesException {
        if (input.split(" ").length == 1) {
            throw new JamesException("The description of an event cannot be empty.");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new JamesException("Please use the format: event <description> /from <time> /to <time>");
        }
        String description = parseDescription(input);
        String[] arrTimeRange = parseTask(input).split(" /from ")[1].split(" /to ");
        if (arrTimeRange.length != 2) {
            throw new JamesException("Please use the format: event <description> /from <time> /to <time>");
        }
        String from = arrTimeRange[0];
        String to = arrTimeRange[1];
        try {
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new JamesException("Please use time format: d/MM/yyyy HHmm");
        }
    }

    public Command parseCommand(String input) throws JamesException {
                if (input.equals("list")) {
                    return new addListCommand();
                } else if (input.startsWith("mark")) {
                    return new addMarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else if (input.startsWith("unmark")) {
                    return new addUnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else if (input.startsWith("todo")) {
                    return new addToDoCommand(parseToDoTask(input));
                } else if (input.startsWith("deadline")) {
                    return new addDeadlineCommand(parseDeadlineTask(input));
                } else if (input.startsWith("event")) {
                    return new addEventCommand(parseEventTask(input));
                } else if (input.startsWith("delete")) {
                    return new deleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                } else {
                    throw new JamesException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

    }
}
