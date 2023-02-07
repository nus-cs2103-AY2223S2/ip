package membot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
                    "Invalid Syntax: \"sort [option]\"",
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
                    Task.sort((t1, t2) -> {
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
                    });
                    break;
                case STATUS:
                    Task.sort(Comparator.comparing(Task::printStatus));
                    break;
                case TYPE:
                    Task.sort(Comparator.comparing(Task::getTaskType));
                    break;
                default:
                    assert false : "Available options are: date, status, title, type";
            }
        } catch (IllegalArgumentException e) {
            this.ui.printlnError(
                    "Invalid sort option!",
                    "Available options are: date, status, title, type"
            );
            this.ui.printSeparator();
            return;
        }

        new ListCommand(this.ui).execute();
        this.ui.printSeparator();
    }
}

enum SortOption {
    DATE,
    STATUS,
    TITLE,
    TYPE
}
