package cbot.command;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import cbot.io.Parser;
import cbot.task.TaskList;

/**
 * A command type accepted by Cbot.
 */
public enum Command {
    /** Ends the Cbot session. */
    BYE(CommandFunction::doBye,
            false, false,
            "bye", "close", "end", "exit"),

    /** Displays the current list of tasks. */
    LIST(CommandFunction::doList,
            false, false,
            "list"),

    /** Marks the stipulated task as done. */
    MARK(CommandFunction::doMark,
            true, true,
            "mark "),

    /** Catches MARK calls with no input. */
    MARK_BAD(CommandFunction::doNoInput,
            false, false,
            "mark"),

    /** Marks the stipulated task as not done. */
    UNMARK(CommandFunction::doUnmark,
            true, true,
            "unmark "),

    /** Catches UNMARK calls with no input. */
    UNMARK_BAD(CommandFunction::doNoInput,
            false, false,
            "unmark"),

    /** Deletes the stipulated task from the list. */
    DELETE(CommandFunction::doRemove,
            true, true,
            "del ", "delete ", "rem ", "remove ", "- "),

    /** Catches DELETE calls with no input. */
    DELETE_BAD(CommandFunction::doNoInput,
            false, false,
            "del", "delete", "rem", "remove", "-"),

    /** Adds a To-Do task to the list. */
    TODO(CommandFunction::doTodo,
            true, true,
            "td ", "todo ", "+ "),

    /** Catches TODO calls with no input. */
    TODO_BAD(CommandFunction::doNoInput,
            false, false,
            "td", "todo", "+"),

    /** Adds a Deadline task to the list. */
    DEADLINE(CommandFunction::doDeadline,
            true, true,
            "deadline ", "dl "),

    /** Catches DEADLINE calls with no input. */
    DEADLINE_BAD(CommandFunction::doNoInput,
            false, false,
            "deadline", "dl"),

    /** Adds an Event task to the list. */
    EVENT(CommandFunction::doEvent,
            true, true,
            "e ", "ev ", "event "),

    /** Catches EVENT calls with no input. */
    EVENT_BAD(CommandFunction::doNoInput,
            false, false,
            "e", "ev", "event"),

    /** Sorts the list by date and description. */
    SORT(CommandFunction::doSort,
            false, true,
            "sort"),

    /** Displays the tasks that fall before the given date. */
    BEFORE(CommandFunction::doBefore,
            true, false,
            "before "),

    /** Catches BEFORE calls with no input. */
    BEFORE_BAD(CommandFunction::doNoInput,
            false, false,
            "before"),

    /** Displays the tasks that fall after the given date. */
    AFTER(CommandFunction::doAfter,
            true, false,
            "after "),

    /** Catches AFTER calls with no input. */
    AFTER_BAD(CommandFunction::doNoInput,
            false, false,
            "after"),

    /** Displays the tasks that match the given filter. */
    FILTER(CommandFunction::doFilter,
            true, false,
            "filter "),

    /** Catches FILTER calls with no input. */
    FILTER_BAD(CommandFunction::doNoInput,
            false, false,
            "filter"),

    /** Displays the tasks that contain the input. */
    FIND(CommandFunction::doFind,
            true, false,
            "find "),

    /** Catches FIND calls with no input. */
    FIND_BAD(CommandFunction::doNoInput,
            false, false,
            "find"),

    /** Changes the description of the selected task. */
    EDIT(CommandFunction::doEdit,
            true, true,
            "edit "),

    /** Catches EDIT calls with no input. */
    EDIT_BAD(CommandFunction::doNoInput,
            false, false,
            "edit");

    private final ThrowingBiFunction<TaskList, String, String> f;
    private final boolean hasText;
    private final boolean needSave;
    private final List<String> names;

    /**
     * Constructs a Command type with the given behaviour and properties.
     *
     * @param f The behaviour of this Command.
     * @param hasText Whether the Command expects additional input after its name.
     * @param needSave Whether the file needs to be saved after the Command is run.
     * @param names The acceptable callable names for the Command.
     */
    Command(ThrowingBiFunction<TaskList, String, String> f, boolean hasText, boolean needSave, String ... names) {
        this.f = f;
        this.hasText = hasText;
        this.needSave = needSave;
        this.names = Arrays.asList(names);
    }

    /**
     * Returns true if the Command updates the list of tasks, thus calling for it to be saved.
     *
     * @return Whether the Command warrants a file save.
     */
    public boolean needSave() {
        return this.needSave;
    }

    /**
     * Returns the relevant text if the start of the given String matches the Command keyword.
     * Else, the empty String "" is returned.
     * e.g. "mark 1" matches the keyword "mark " in MARK, so "1" is returned.
     *
     * @param input The text to check.
     * @return The extracted text, if any.
     * @see Parser
     */
    public String getMatch(String input) {
        String lowText = input.toLowerCase();

        if (!this.hasText) {
            // should match exactly
            return (names.contains(lowText))
                    ? lowText
                    : "";
        }

        for (String name : names) {
            int nameLen = name.length();
            boolean longEnough = (lowText.length() > nameLen);
            if (longEnough
                    && lowText.substring(0, nameLen).equals(name)) {
                return input.substring(nameLen).trim();
            }
        }

        return "";
    }

    /**
     * Runs the functionality of the respective command with the given list of tasks and text.
     *
     * @param tl The list of tasks to process.
     * @param text The instruction details for the command.
     * @return The output of running the command.
     * @throws PoorInputException If the input text is improper or erroneous.
     * @throws DateTimeParseException If some provided datetime is not in a recognized format.
     */
    public String runCommand(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        return this.f.apply(tl, text);
    }
}
