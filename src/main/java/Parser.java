import command.*;
import exception.MikiArgsException;
import exception.TaskParseException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

/**
 * A parser for Miki interactive command-line inputs.
 */
public class Parser {
    /**
     * Parses a <code>ListTasks</code> command from a provided <code>String[]</code> of
     * space-separated tokens.
     *
     * @param args <code>String[]</code> denoting a <code>ListTasks</code> command
     * @return a <code>ListTasks</code> command represented by <code>args</code>
     * @throws MikiArgsException if <code>args</code> does not represent
     * a valid <code>ListTasks</code> command
     */
    public static ListTasks parseList(String[] args) throws MikiArgsException {
        String from = "";
        String to = "";
        boolean token_from = false;
        boolean token_to = false;
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) throw new MikiArgsException("too many filter-froms...");
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) throw new MikiArgsException("too many filter-tos...");
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/from")) {
                token_from = true;
                token_to = false;
                continue;
            }
            if (args[i].equals("/to")) {
                token_from = false;
                token_to = true;
                continue;
            }
            if (token_from) {
                from += (from.isEmpty() ? "" : " ") + args[i];
            } else if (token_to) {
                to += (to.isEmpty() ? "" : " ") + args[i];
            }
        }
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        if (!from.isEmpty()) {
            try {
                fromDate = LocalDateTime.parse(from, Task.DATE_IN_FMT);
            } catch (DateTimeParseException ex) {
                throw new MikiArgsException(from + " needs to be formatted as " + Task.DATE_IN_FMT_STR + "!");
            }
        }
        if (!to.isEmpty()) {
            try {
                toDate = LocalDateTime.parse(to, Task.DATE_IN_FMT);
            } catch (DateTimeParseException ex) {
                throw new MikiArgsException(to + " needs to be formatted as " + Task.DATE_IN_FMT_STR + "!");
            }
        }
        return new ListTasks(fromDate, toDate);
    }

    /**
     * Parses a <code>TaskList</code> index from a <code>String[]</code> of space-separated tokens.
     *
     * @param args <code>String[]</code> containing a <code>TaskList</code> index
     * @return the <code>TaskList</code> index represented by <code>args</code>
     * @throws MikiArgsException if <code>args</code> does not represent an integer
     */
    public static int parseTaskIndex(String[] args) throws MikiArgsException {
        int idx;
        if (args.length == 0) {
            throw new MikiArgsException("you didn't specify which one?!");
        }
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException ex) {
            throw new MikiArgsException("\"" + args[0] + "\" isn't a real integer! There's no task #" + args[0] + "!");
        }
        return idx;
    }

    /**
     * Forms a single <code>String</code> representing a file path by combining
     * consecutive fragmented <code>String</code>s in a <code>String[]</code> of space-separated tokens.
     *
     * @param args fragmented <code>String[]</code> to combine
     * @return the file path represented by <code>args</code>
     */
    public static String parsePath(String[] args) {
        String path = "";
        for (int i = 0; i < args.length; i++) {
            path += (i > 0 ? " " : "") + args[i];
        }
        return path;
    }

    /**
     * Returns <code>true</code> if the supplied line of input is requesting the program to exit.
     *
     * @param cmdLine the input to parse
     * @return <code>true</code> if <code>cmdLine</code> represents an exit command
     */
    public static boolean parseExit(String cmdLine) {
        return cmdLine.split(" ")[0].toLowerCase().equals("bye");
    }

    /**
     * Parses one supplied line of input String to create a <code>Command</code> action.
     *
     * @param cmdLine the input denoting a <code>Command</code>
     * @return a <code>Command</code> represented by <code>cmdLine</code>
     */
    public static Command parse(String cmdLine) {
        String cmd = cmdLine.split(" ")[0].toLowerCase();
        String[] args = {};
        if (cmdLine.contains(" ")) {
            args = cmdLine.substring(cmd.length() + 1).split(" ");
        }
        try {
            switch (cmd) {
                case "bye":
                    return new ExitPrint();
                case "list":
                    return parseList(args);
                case "mark":
                    return new Mark(parseTaskIndex(args));
                case "unmark":
                    return new Unmark(parseTaskIndex(args));
                case "todo":
                    return new AddTask(Todo.parseArgs(args));
                case "deadline":
                    return new AddTask(Deadline.parseArgs(args));
                case "event":
                    return new AddTask(Event.parseArgs(args));
                case "delete":
                    return new Delete(parseTaskIndex(args));
                case "save":
                    return new Save(parsePath(args));
                case "load":
                    return new Load(parsePath(args));
                default:
                    throw new MikiArgsException("\"" + cmd + "\" isn't a real word!");
            }
        } catch (TaskParseException | MikiArgsException ex) {
            return new ExceptionPrint(ex);
        }
    }
}
