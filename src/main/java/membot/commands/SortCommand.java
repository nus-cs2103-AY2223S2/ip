package membot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;

import membot.model.Task;
import membot.utils.DateTimeParser;
import membot.utils.InputValidator;
import membot.view.Printable;

/**
 * Represents a command which sorts the <code>Task</code> list based on the sort
 * option specified.
 */
public class SortCommand extends Command {
    protected SortCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        if (!InputValidator.isSingleInputValid(this.input, false, false)) {
            this.ui.printlnError(
                    String.format("Invalid Syntax: \"sort [%s]\"", getSortOptions("|")),
                    "",
                    "Example: \"sort date\""
            );
            return;
        }

        try {
            switch (SortOption.valueOf(this.input.split(" ")[1].toUpperCase())) {
            case TITLE:
                Task.sort(Comparator.comparing(Task::getTitle));
                break;
            case DATE:
                Task.sort(this::sortByDate);
                break;
            case STATUS:
                Task.sort(Comparator.comparing(Task::printStatus));
                break;
            case TYPE:
                Task.sort(Comparator.comparing(Task::getTaskType));
                break;
            default:
                assert false : String.format("Available options are: %s", getSortOptions(", "));
            }
        } catch (IllegalArgumentException e) {
            this.ui.printlnError(
                    "Invalid sort option!",
                    String.format("Available options are: %s", getSortOptions(", "))
            );
            this.ui.printSeparator();
            return;
        }

        new ListCommand(this.ui).execute();
        this.ui.printSeparator();
    }

    private String getSortOptions(String delimiter) {
        return String.join(delimiter, Arrays.stream(SortOption.class.getEnumConstants())
                .map(x -> x.toString().toLowerCase()).toArray(String[]::new));
    }

    private int sortByDate(Task t1, Task t2) {
        LocalDateTime t1DT;
        LocalDateTime t2DT;

        try {
            t1DT = DateTimeParser.parse(t1.getDeadline());
        } catch (DateTimeParseException e) {
            return 1;
        }

        try {
            t2DT = DateTimeParser.parse(t2.getDeadline());
        } catch (DateTimeParseException e) {
            return -1;
        }

        return t1DT.compareTo(t2DT);
    }
}

enum SortOption {
    DATE,
    STATUS,
    TITLE,
    TYPE
}
