package cbot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import cbot.io.Talker;
import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;
import cbot.task.TaskList;
import cbot.util.TimeStuff;

class CommandFunction {
    private static int checkIndex(TaskList tl, String text)
            throws PoorInputException {
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

    private static ArrayList<Integer> splitNums(TaskList tl, String text)
            throws PoorInputException {
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
    static String doNoInput(TaskList tl, String input)
            throws PoorInputException {
        throw new PoorInputException(input.toUpperCase() + " needs an input");
    }

    static String doBye(TaskList tl, String input) {
        assert (Command.BYE.matches(input)) : "This should only be accessed by BYE";

        return Talker.sayBye();
    }

    static String doList(TaskList tl, String input) {
        assert (Command.LIST.matches(input)) : "This should only be accessed by LIST";

        return (tl.getCount() == 0)
                ? Talker.say("Freedom! You have no tasks :D")
                : Talker.say("Here's what you have:\n") + Talker.printMany(tl.listTasks());
    }

    static String doMark(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.MARK.matches(input)) : "This should only be accessed by MARK";
        String text = Command.MARK.extractText(input);
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

    static String doUnmark(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.UNMARK.matches(input)) : "This should only be accessed by UNMARK";
        String text = Command.UNMARK.extractText(input);
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

    static String doDelete(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.DELETE.matches(input)) : "This should only be accessed by DELETE";
        String text = Command.DELETE.extractText(input);
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

    static String doTodo(TaskList tl, String input) {
        assert (Command.TODO.matches(input)) : "This should only be accessed by TODO";
        String text = Command.TODO.extractText(input);
        return Talker.say(tl.addTask(new Task(text)));
    }

    static String doDeadline(TaskList tl, String input)
            throws PoorInputException, DateTimeParseException {
        assert (Command.DEADLINE.matches(input)) : "This should only be accessed by DEADLINE";
        String text = Command.DEADLINE.extractText(input);

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

        String dlDesc = text.substring(0, byIndex).trim();
        String dlDueStr = text.substring(byIndex + byLength);

        LocalDateTime dlDue = TimeStuff.textToDT(dlDueStr);
        return Talker.say(tl.addTask(new Deadline(dlDesc, dlDue)));
    }

    static String doEvent(TaskList tl, String input)
            throws PoorInputException, DateTimeParseException {
        assert (Command.EVENT.matches(input)) : "This should only be accessed by EVENT";
        String text = Command.EVENT.extractText(input);

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

        String eDesc = text.substring(0, fromIndex).trim();
        String eStartStr = text.substring(fromIndex + fromLength, toIndex);
        String eEndStr = text.substring(toIndex + toLength);

        LocalDateTime eStart = TimeStuff.textToDT(eStartStr);
        LocalDateTime eEnd = TimeStuff.textToDT(eEndStr);

        if (eStart.isAfter(eEnd)) {
            throw new PoorInputException("Hey! You have to start *before* you end...");
        }

        return Talker.say(tl.addTask(new Event(eDesc, eStart, eEnd)));
    }

    static String doSort(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.SORT.matches(input)) : "This should only be accessed by SORT";

        if (tl.getCount() == 0) {
            throw new PoorInputException("You have no tasks to sort :P");
        }

        tl.sort();
        return Talker.say("Okay! I've sorted your tasks by date:\n")
                + Talker.printMany(tl.listTasks());
    }

    static String doBefore(TaskList tl, String input)
            throws PoorInputException, DateTimeParseException {
        assert (Command.BEFORE.matches(input)) : "This should only be accessed by BEFORE";
        String text = Command.BEFORE.extractText(input);
        checkFilterCount(tl);

        LocalDateTime bef = TimeStuff.textToDT(text);
        ArrayList<String> arrBef = tl.listFilter(t ->
                t.hasTime() && t.compareTo(new Deadline("", bef)) < 0);

        return (arrBef.isEmpty())
                ? Talker.say("You don't have any tasks before " + text.trim())
                : Talker.say("Here are your tasks before " + text.trim() + ":\n") + Talker.printMany(arrBef);
    }

    static String doAfter(TaskList tl, String input)
            throws PoorInputException, DateTimeParseException {
        assert (Command.AFTER.matches(input)) : "This should only be accessed by AFTER";
        String text = Command.AFTER.extractText(input);
        checkFilterCount(tl);

        LocalDateTime aft = TimeStuff.textToDT(text);
        ArrayList<String> arrAft = tl.listFilter(t ->
                t.hasTime() && t.compareTo(new Deadline("", aft)) > 0);

        return (arrAft.isEmpty())
                ? Talker.say("You don't have any tasks after " + text.trim())
                : Talker.say("Here are your tasks after " + text.trim() + ":\n") + Talker.printMany(arrAft);
    }

    static String doFilter(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.FILTER.matches(input)) : "This should only be accessed by FILTER";
        String text = Command.FILTER.extractText(input);
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

    static String doFind(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.FIND.matches(input)) : "This should only be accessed by FIND";
        String text = Command.FIND.extractText(input);
        checkFilterCount(tl);

        String lowText = text.toLowerCase();
        ArrayList<String> arrFind = tl.listFilter(t ->
                t.getDesc().toLowerCase().contains(lowText));

        return (arrFind.isEmpty())
                ? Talker.say("Nope, nothing matches your search!")
                : Talker.say("Here! I found these:\n") + Talker.printMany(arrFind);
    }

    static String doEdit(TaskList tl, String input)
            throws PoorInputException {
        assert (Command.EDIT.matches(input)) : "This should only be accessed by EDIT";
        String text = Command.EDIT.extractText(input);

        int spaceIndex = checkKeyword(text, " ");

        String editIndexStr = text.substring(0, spaceIndex);
        int editIndex = checkIndex(tl, editIndexStr);
        String editDesc = text.substring(spaceIndex + 1);

        return Talker.say(tl.editTaskDesc(editIndex, editDesc));
    }
}
