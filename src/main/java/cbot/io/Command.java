package cbot.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;
import cbot.task.TaskList;
import cbot.util.TimeStuff;

@FunctionalInterface
interface ThrowingBiFunction<T, U, R> {
    R apply(T t, U u) throws DateTimeParseException, PoorInputException;
}

/**
 * A command type accepted by Cbot.
 */
public enum Command {
    /** Ends the Cbot session. */
    BYE(
            "bye",
            Command::doBye),

    /** Displays the current list of tasks. */
    LIST(
            "list",
            Command::doList),

    /** Marks the stipulated task as done. */
    MARK(
            "mark ",
            Command::doMark,
            true, true),

    /** Catches MARK calls with no input. */
    MARK_BAD(
            "mark",
            Command::doNoInput),

    /** Marks the stipulated task as not done. */
    UNMARK(
            "unmark ",
            Command::doUnmark,
            true, true),

    /** Catches UNMARK calls with no input. */
    UNMARK_BAD(
            "unmark",
            Command::doNoInput),

    /** Deletes the stipulated task from the list. */
    DELETE(
            "delete ",
            Command::doRemove,
            true, true),

    /** Catches DELETE calls with no input. */
    DELETE_BAD(
            "delete",
            Command::doNoInput),

    /** Deletes the stipulated task from the list. Same as DELETE. */
    REMOVE(
            "remove ",
            Command::doRemove,
            true, true),

    /** Catches REMOVE calls with no input. */
    REMOVE_BAD(
            "remove",
            Command::doNoInput),

    /** Adds a To-Do task to the list. */
    TODO(
            "todo ",
            Command::doTodo,
            true, true),

    /** Catches TODO calls with no input. */
    TODO_BAD(
            "todo",
            Command::doNoInput),

    /** Adds a Deadline task to the list. */
    DEADLINE(
            "deadline ",
            Command::doDeadline,
            true, true),

    /** Catches DEADLINE calls with no input. */
    DEADLINE_BAD(
            "deadline",
            Command::doNoInput),

    /** Adds an Event task to the list. */
    EVENT(
            "event ",
            Command::doEvent,
            true, true),

    /** Catches EVENT calls with no input. */
    EVENT_BAD(
            "event",
            Command::doNoInput),

    /** Sorts the list by date and description. */
    SORT(
            "sort",
            Command::doSort,
            false, true),

    /** Displays the tasks that fall before the given date. */
    BEFORE(
            "before ",
            Command::doBefore,
            true),

    /** Catches BEFORE calls with no input. */
    BEFORE_BAD(
            "before",
            Command::doNoInput),

    /** Displays the tasks that fall after the given date. */
    AFTER(
            "after ",
            Command::doAfter,
            true),

    /** Catches AFTER calls with no input. */
    AFTER_BAD(
            "after",
            Command::doNoInput),

    /** Displays the tasks that match the given filter. */
    FILTER(
            "filter ",
            Command::doFilter,
            true),

    /** Catches FILTER calls with no input. */
    FILTER_BAD(
            "filter",
            Command::doNoInput),

    /** Displays the tasks that contain the input. */
    FIND(
            "find ",
            Command::doFind,
            true),

    /** Catches FIND calls with no input. */
    FIND_BAD(
            "find",
            Command::doNoInput);

    private final String str;
    private final ThrowingBiFunction<TaskList, String, String> f;
    private final boolean hasText;
    private final boolean needSave;

    /**
     * Constructs a Command type with the given keyword. The next two booleans are optional,
     * and default to false. Respectively, they specify whether the Command expects additional input
     * after the keyword, and whether the file needs to be saved after the Command is run.
     *
     * @param str The command keyword.
     * @param bools (Optional) Whether more text after the keyword is expected, and
     *        whether the file needs to be saved after the command is run.
     */
    Command(String str, ThrowingBiFunction<TaskList, String, String> f, boolean ... bools) {
        this.str = str;
        this.f = f;
        this.hasText = (bools.length >= 1) ? bools[0] : false;
        this.needSave = (bools.length >= 2) ? bools[1] : false;
    }

    /**
     * Returns true if the Command updates the list of tasks, thus calling for it to be saved.
     *
     * @return Whether the Command warrants a file save.
     */
    boolean needSave() {
        return this.needSave;
    }

    /**
     * Returns the length of the keyword, including any whitespace after.
     *
     * @return The length of the Command keyword.
     */
    int getLen() {
        return this.str.length();
    }

    /**
     * Returns true if the start of the given String matches the Command keyword.
     * e.g. "mark 1" matches the keyword "mark " in MARK.
     *
     * @param input The text to check.
     * @return Whether the text means to call this Command.
     * @see Parser
     */
    boolean matches(String input) {
        String lowText = input.toLowerCase();

        if (!this.hasText) {
            // should match exactly
            return lowText.equals(this.str);
        } else if (input.length() > getLen()) {
            // not too short to check
            return lowText.substring(0, getLen()).equals(this.str);
        } else {
            // too short to match
            return false;
        }
    }

    /**
     * Extracts the body of the input. That is, the text that comes after the Command keyword.
     *
     * @param input The full input.
     * @return The 'body' of the input.
     */
    String extractText(String input) {
        return (this.hasText)
                ? input.substring(getLen()).trim()
                : "";
    }

    /**
     * Returns true if the Command expects body text but receives none.
     * e.g. "mark  " with no task number specified.
     *
     * @param text The body text to check.
     * @return Whether there is missing body text, given the Command type.
     */
    boolean isMissingText(String text) {
        return (this.hasText)
                ? (text.length() > 0)
                : false;
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

    private static int checkKeyword(String text, String keyword)
            throws PoorInputException {
        if (!text.contains(keyword)) {
            throw new PoorInputException("Missing \"" + keyword.trim() + "\" keyword");
        }

        return text.indexOf(keyword);
    }

    private static void checkFilterCount(TaskList tl)
            throws PoorInputException {
        if (tl.getCount() == 0) {
            throw new PoorInputException("Eh? You have no tasks to filter");
        }
    }

    // Command static methods to be called by Parser, and run by runCommand
    private static String doNoInput(TaskList tl, String str)
            throws PoorInputException {
        throw new PoorInputException("That command needs an input");
    }

    private static String doBye(TaskList tl, String str) {
        return Talker.sayBye();
    }

    private static String doList(TaskList tl, String str) {
        return (tl.getCount() == 0)
                ? Talker.say("Freedom! You have no tasks :D")
                : Talker.say("Here's what you have:\n") + Talker.printMany(tl.listTasks());
    }

    private static String doMark(TaskList tl, String text)
            throws PoorInputException {
        try {
            int num = Integer.parseInt(text);

            if (tl.notInRange(num)) {
                throw new PoorInputException(tl.getRangeErrorMsg(num));
            }

            return Talker.say(tl.mark(num));

        } catch (NumberFormatException e) {
            throw new BadInputException("Invalid index!");
        }
    }

    private static String doUnmark(TaskList tl, String text)
            throws PoorInputException {
        try {
            int num = Integer.parseInt(text);

            if (tl.notInRange(num)) {
                throw new PoorInputException(tl.getRangeErrorMsg(num));
            }

            return Talker.say(tl.unmark(num));

        } catch (NumberFormatException e) {
            throw new BadInputException("Invalid index!");
        }
    }

    private static String doRemove(TaskList tl, String text)
            throws PoorInputException {
        try {
            int num = Integer.parseInt(text);

            if (tl.notInRange(num)) {
                throw new PoorInputException(tl.getRangeErrorMsg(num));
            }

            return Talker.say(tl.delTask(num));

        } catch (NumberFormatException e) {
            throw new BadInputException("Invalid index!");
        }
    }

    private static String doTodo(TaskList tl, String text) {
        return Talker.say(tl.addTask(new Task(text)));
    }

    private static String doDeadline(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        String byKeyword = "/by ";
        int byLength = byKeyword.length();

        int byIndex = checkKeyword(text, byKeyword);

        if (byIndex == 0) {
            // no desc
            throw new BadInputException("Missing deadline description");
        } else if (byIndex + byLength == text.length()) {
            // no due date
            throw new BadInputException("Missing due date");
        }

        String dlDesc = text.substring(0, byIndex);
        String dlDueStr = text.substring(byIndex + byLength);

        LocalDateTime dlDue = TimeStuff.textToDT(dlDueStr);
        return Talker.say(tl.addTask(new Deadline(dlDesc, dlDue)));
    }

    private static String doEvent(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        String fromKeyword = "/from ";
        String toKeyword = "/to ";
        int fromLength = fromKeyword.length();
        int toLength = toKeyword.length();

        int fromIndex = checkKeyword(text, fromKeyword);
        int toIndex = checkKeyword(text, toKeyword);

        if (toIndex < fromIndex) {
            // /to before /from
            throw new PoorInputException("\"/from\" before \"/to\", please!");
        } else if (fromIndex == 0) {
            // no desc
            throw new BadInputException("Missing event description");
        } else if (fromIndex + fromLength >= toIndex) {
            // no start
            throw new BadInputException("Missing start date");
        } else if (toIndex + toLength == text.length()) {
            // no end
            throw new BadInputException("Missing end date");
        }

        String eDesc = text.substring(0, fromIndex);
        String eStartStr = text.substring(fromIndex + fromLength, toIndex);
        String eEndStr = text.substring(toIndex + toLength);

        LocalDateTime eStart = TimeStuff.textToDT(eStartStr);
        LocalDateTime eEnd = TimeStuff.textToDT(eEndStr);

        if (eStart.isAfter(eEnd)) {
            throw new BadInputException("Hey! You have to start *before* you end...");
        }

        return Talker.say(tl.addTask(new Event(eDesc, eStart, eEnd)));
    }

    private static String doSort(TaskList tl, String text)
            throws PoorInputException {
        if (tl.getCount() == 0) {
            throw new PoorInputException("You have no tasks to sort :P");
        }

        tl.sort();
        return Talker.say("Okay! I've sorted your tasks by date:\n")
                + Talker.printMany(tl.listTasks());
    }

    private static String doBefore(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        checkFilterCount(tl);

        LocalDateTime bef = TimeStuff.textToDT(text);
        ArrayList<String> arrBef = tl.listFilter(t ->
                t.hasTime() && t.compareTo(new Deadline("", bef)) < 0);

        return (arrBef.isEmpty())
                ? Talker.say("You don't have any tasks before " + text.trim())
                : Talker.say("Here are your tasks before " + text.trim() + ":\n") + Talker.printMany(arrBef);
    }

    private static String doAfter(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        checkFilterCount(tl);

        LocalDateTime aft = TimeStuff.textToDT(text);
        ArrayList<String> arrAft = tl.listFilter(t ->
                t.hasTime() && t.compareTo(new Deadline("", aft)) > 0);

        return (arrAft.isEmpty())
                ? Talker.say("You don't have any tasks after " + text.trim())
                : Talker.say("Here are your tasks after " + text.trim() + ":\n") + Talker.printMany(arrAft);
    }

    private static String doFilter(TaskList tl, String text) throws PoorInputException {
        checkFilterCount(tl);

        String msg;
        ArrayList<String> arrFilter;

        switch (text.toLowerCase()) {
        case "todo":
            // Fallthrough
        case "td":
            // Fallthrough
        case "t":
            msg = "Ok! These are on your ToDo list:\n";
            arrFilter = tl.listFilter(t -> t.getSymbol().equals(Task.TODO_SYMBOL));
            break;

        case "deadline":
            // Fallthrough
        case "dl":
            // Fallthrough
        case "d":
            msg = "Ok! Here are your Deadlines:\n";
            arrFilter = tl.listFilter(t -> t.getSymbol().equals(Deadline.DEADLINE_SYMBOL));
            break;

        case "event":
            // Fallthrough
        case "ev":
            // Fallthrough
        case "e":
            msg = "Ok! Here are your Events:\n";
            arrFilter = tl.listFilter(t -> t.getSymbol().equals(Event.EVENT_SYMBOL));
            break;

        case "complete":
            // Fallthrough
        case "done":
            // Fallthrough
        case "completed":
            // Fallthrough
        case "x":
            msg = "Ok! Here are the Tasks you've completed:\n";
            arrFilter = tl.listFilter(t -> t.getStatus().equals(Task.DONE_TRUE));
            break;

        case "incomplete":
            // Fallthrough
        case "not done":
            // Fallthrough
        case "!done":
            // Fallthrough
        case "undone":
            msg = "Ok! Here are the Tasks you haven't completed yet:\n";
            arrFilter = tl.listFilter(t -> !t.getStatus().equals(Task.DONE_TRUE));
            break;

        default:
            throw new PoorInputException("I'm not sure what Task type that is :(");
        }

        return (arrFilter.isEmpty())
                ? Talker.say("You don't have any of those :/")
                : Talker.say(msg) + Talker.printMany(arrFilter);
    }

    private static String doFind(TaskList tl, String text) throws PoorInputException {
        checkFilterCount(tl);

        String lowText = text.toLowerCase();
        ArrayList<String> arrFind = tl.listFilter(t ->
                t.getDesc().toLowerCase().contains(lowText));

        return (arrFind.isEmpty())
                ? Talker.say("Nope, nothing matches your search!")
                : Talker.say("Here! I found these:\n") + Talker.printMany(arrFind);
    }
}
