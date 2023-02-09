package cbot.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    BYE(Command::doBye,
            false, false,
            "bye", "close", "end", "exit"),

    /** Displays the current list of tasks. */
    LIST(Command::doList,
            false, false,
            "list"),

    /** Marks the stipulated task as done. */
    MARK(Command::doMark,
            true, true,
            "mark "),

    /** Catches MARK calls with no input. */
    MARK_BAD(Command::doNoInput,
            false, false,
            "mark"),

    /** Marks the stipulated task as not done. */
    UNMARK(Command::doUnmark,
            true, true,
            "unmark "),

    /** Catches UNMARK calls with no input. */
    UNMARK_BAD(Command::doNoInput,
            false, false,
            "unmark"),

    /** Deletes the stipulated task from the list. */
    DELETE(Command::doRemove,
            true, true,
            "del ", "delete ", "rem ", "remove ", "- "),

    /** Catches DELETE calls with no input. */
    DELETE_BAD(Command::doNoInput,
            false, false,
            "del", "delete", "rem", "remove", "-"),

    /** Adds a To-Do task to the list. */
    TODO(Command::doTodo,
            true, true,
            "td ", "todo ", "+ "),

    /** Catches TODO calls with no input. */
    TODO_BAD(Command::doNoInput,
            false, false,
            "td", "todo", "+"),

    /** Adds a Deadline task to the list. */
    DEADLINE(Command::doDeadline,
            true, true,
            "deadline ", "dl "),

    /** Catches DEADLINE calls with no input. */
    DEADLINE_BAD(Command::doNoInput,
            false, false,
            "deadline", "dl"),

    /** Adds an Event task to the list. */
    EVENT(Command::doEvent,
            true, true,
            "e ", "ev ", "event "),

    /** Catches EVENT calls with no input. */
    EVENT_BAD(Command::doNoInput,
            false, false,
            "e", "ev", "event"),

    /** Sorts the list by date and description. */
    SORT(Command::doSort,
            false, true,
            "sort"),

    /** Displays the tasks that fall before the given date. */
    BEFORE(Command::doBefore,
            true, false,
            "before "),

    /** Catches BEFORE calls with no input. */
    BEFORE_BAD(Command::doNoInput,
            false, false,
            "before"),

    /** Displays the tasks that fall after the given date. */
    AFTER(Command::doAfter,
            true, false,
            "after "),

    /** Catches AFTER calls with no input. */
    AFTER_BAD(Command::doNoInput,
            false, false,
            "after"),

    /** Displays the tasks that match the given filter. */
    FILTER(Command::doFilter,
            true, false,
            "filter "),

    /** Catches FILTER calls with no input. */
    FILTER_BAD(Command::doNoInput,
            false, false,
            "filter"),

    /** Displays the tasks that contain the input. */
    FIND(Command::doFind,
            true, false,
            "find "),

    /** Catches FIND calls with no input. */
    FIND_BAD(Command::doNoInput,
            false, false,
            "find"),

    /** Changes the description of the selected task. */
    EDIT(Command::doEdit,
            true, true,
            "edit "),

    /** Catches EDIT calls with no input. */
    EDIT_BAD(Command::doNoInput,
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
    boolean needSave() {
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
    String getMatch(String input) {
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

    private static int checkIndex(TaskList tl, String text) throws PoorInputException {
        try {
            int num = Integer.parseInt(text);

            if (tl.notInRange(num)) {
                throw new PoorInputException(tl.getRangeErrorMsg(num));
            }

            return num;

        } catch (NumberFormatException e) {
            throw new BadInputException("Invalid index \"" + text + "\"!");
        }
    }

    private static ArrayList<Integer> splitNums(TaskList tl, String text) throws PoorInputException {
        ArrayList<Integer> nums = new ArrayList<>();

        String[] strings = text.split(" ");
        for (String s : strings) {
            int num = checkIndex(tl, s);
            if (!nums.contains(num)) {
                nums.add(num);
            }
        }

        return nums;
    }

    private static int checkKeyword(String text, String keyword)
            throws PoorInputException {
        if (!text.contains(keyword)) {
            throw new PoorInputException("Command is missing \"" + keyword.trim() + "\" keyword");
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
    private static String doNoInput(TaskList tl, String text)
            throws PoorInputException {
        throw new PoorInputException("That command needs an input");
    }

    private static String doBye(TaskList tl, String text) {
        assert (!BYE.getMatch(text).isEmpty()) : "This should only be accessed by BYE";

        return Talker.sayBye();
    }

    private static String doList(TaskList tl, String text) {
        assert (!LIST.getMatch(text).isEmpty()) : "This should only be accessed by LIST";

        return (tl.getCount() == 0)
                ? Talker.say("Freedom! You have no tasks :D")
                : Talker.say("Here's what you have:\n") + Talker.printMany(tl.listTasks());
    }

    private static String doMark(TaskList tl, String text)
            throws PoorInputException {
        assert !text.isEmpty() : "MARK should have input text";

        ArrayList<Integer> nums = splitNums(tl, text);

        if (nums.size() == 1) {
            int num = nums.get(0);
            return Talker.say(tl.mark(num));
        }

        String markedTasks = nums.stream()
                .map(i -> {
                    tl.mark(i);
                    return "\n" + TaskList.GAP + tl.getTask(i).toString();
                })
                .collect(Collectors.joining());
        return Talker.say("That's great! I've marked:" + markedTasks);
    }

    private static String doUnmark(TaskList tl, String text)
            throws PoorInputException {
        assert !text.isEmpty() : "UNMARK should have input text";

        ArrayList<Integer> nums = splitNums(tl, text);

        if (nums.size() == 1) {
            int num = nums.get(0);
            return Talker.say(tl.unmark(num));
        }

        String unmarkedTasks = nums.stream()
                .map(i -> {
                    tl.unmark(i);
                    return "\n" + TaskList.GAP + tl.getTask(i).toString();
                })
                .collect(Collectors.joining());
        return Talker.say("Shucks D: I've unmarked:" + unmarkedTasks);
    }

    private static String doRemove(TaskList tl, String text)
            throws PoorInputException {
        assert !text.isEmpty() : "DELETE should have input text";

        ArrayList<Integer> nums = splitNums(tl, text);

        if (nums.size() == 1) {
            int num = nums.get(0);
            return Talker.say(tl.delTask(num));
        }

        String deletedTasks = nums.stream()
                .map(i -> "\n" + TaskList.GAP + tl.getTask(i).toString())
                .collect(Collectors.joining());

        nums.stream()
                .sorted(Comparator.reverseOrder())
                .forEachOrdered(tl::delTask);

        return Talker.say("Gotcha! I've deleted:" + deletedTasks);
    }

    private static String doTodo(TaskList tl, String text) {
        assert !text.isEmpty() : "TODO should have input text";

        return Talker.say(tl.addTask(new Task(text)));
    }

    private static String doDeadline(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        assert !text.isEmpty() : "DEADLINE should have input text";

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
        assert !text.isEmpty() : "EVENT should have input text";

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
        assert (!SORT.getMatch(text).isEmpty()) : "This should only be accessed by SORT";

        if (tl.getCount() == 0) {
            throw new PoorInputException("You have no tasks to sort :P");
        }

        tl.sort();
        return Talker.say("Okay! I've sorted your tasks by date:\n")
                + Talker.printMany(tl.listTasks());
    }

    private static String doBefore(TaskList tl, String text)
            throws PoorInputException, DateTimeParseException {
        assert !text.isEmpty() : "BEFORE should have input text";
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
        assert !text.isEmpty() : "AFTER should have input text";
        checkFilterCount(tl);

        LocalDateTime aft = TimeStuff.textToDT(text);
        ArrayList<String> arrAft = tl.listFilter(t ->
                t.hasTime() && t.compareTo(new Deadline("", aft)) > 0);

        return (arrAft.isEmpty())
                ? Talker.say("You don't have any tasks after " + text.trim())
                : Talker.say("Here are your tasks after " + text.trim() + ":\n") + Talker.printMany(arrAft);
    }

    private static String doFilter(TaskList tl, String text) throws PoorInputException {
        assert !text.isEmpty() : "FILTER should have input text";
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
            arrFilter = tl.listFilter(t -> t.getStatus().equals(Task.DONE_FALSE));
            break;

        default:
            throw new PoorInputException("I'm not sure what Task type that is :(");
        }

        return (arrFilter.isEmpty())
                ? Talker.say("You don't have any of those :/")
                : Talker.say(msg) + Talker.printMany(arrFilter);
    }

    private static String doFind(TaskList tl, String text) throws PoorInputException {
        assert !text.isEmpty() : "FIND should have input text";
        checkFilterCount(tl);

        String lowText = text.toLowerCase();
        ArrayList<String> arrFind = tl.listFilter(t ->
                t.getDesc().toLowerCase().contains(lowText));

        return (arrFind.isEmpty())
                ? Talker.say("Nope, nothing matches your search!")
                : Talker.say("Here! I found these:\n") + Talker.printMany(arrFind);
    }

    private static String doEdit(TaskList tl, String text) throws PoorInputException {
        assert !text.isEmpty() : "EDIT should have input text";

        int spaceIndex = checkKeyword(text, " ");

        String editIndexStr = text.substring(0, spaceIndex);
        int editIndex = checkIndex(tl, editIndexStr);
        String editDesc = text.substring(spaceIndex + 1);

        return Talker.say(tl.editTaskDesc(editIndex, editDesc));
    }
}
