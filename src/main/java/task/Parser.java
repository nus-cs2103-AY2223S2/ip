package task;

import static java.time.LocalDate.now;
import static task.Task.DATE_IN_FMT;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import command.AddTask;
import command.Command;
import command.Delete;
import command.ExceptionPrint;
import command.Exit;
import command.Find;
import command.ListTasks;
import command.Load;
import command.Mark;
import command.Save;
import command.Unmark;
import exception.MikiArgsException;
import exception.NatDateParseException;
import exception.TaskParseException;

/**
 * A parser for Miki interactive command-line inputs.
 */
public class Parser {
    private static final int NAT_NONE = -5719;
    private static final List<String> DAY_NAMES =
            Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun");
    private static final List<String> MONTH_NAMES =
            Arrays.asList("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec");

    /**
     * Parses a <code>ListTasks</code> command from a provided <code>String[]</code> of
     * space-separated tokens.
     *
     * @param args <code>String[]</code> denoting a <code>ListTasks</code> command.
     * @return a <code>ListTasks</code> command represented by <code>args</code>.
     * @throws MikiArgsException if <code>args</code> does not represent
     *                           a valid <code>ListTasks</code> command.
     */
    public static ListTasks parseList(String[] args) throws MikiArgsException {
        String from = "";
        String to = "";
        boolean isInTokenFrom = false; // true after scanning "/from" token - subsequent text is part of "from"-timing
        boolean isInTokenTo = false; // true after scanning "/to" token - subsequent text is part of "to"-timing
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) {
            throw new MikiArgsException("too many filter-froms...");
        }
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) {
            throw new MikiArgsException("too many filter-tos...");
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/from")) {
                isInTokenFrom = true;
                isInTokenTo = false;
                continue;
            }
            if (args[i].equals("/to")) {
                isInTokenFrom = false;
                isInTokenTo = true;
                continue;
            }
            if (isInTokenFrom) {
                from += (from.isEmpty() ? "" : " ") + args[i];
            } else if (isInTokenTo) {
                to += (to.isEmpty() ? "" : " ") + args[i];
            }
        }

        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        if (!from.isEmpty()) {
            try {
                fromDate = parseDate(from, false);
            } catch (NatDateParseException ex) {
                throw new MikiArgsException("\"" + from + "\"... is... when??!? write " + Task.DATE_IN_FMT_STR + "!!");
            }
        }
        if (!to.isEmpty()) {
            try {
                toDate = parseDate(to, true);
            } catch (NatDateParseException ex) {
                throw new MikiArgsException("\"" + to + "\"... is... when??!? write " + Task.DATE_IN_FMT_STR + "!!");
            }
        }

        return new ListTasks(fromDate, toDate);
    }

    /**
     * Parses a <code>TaskList</code> index from a <code>String[]</code> of space-separated tokens.
     *
     * @param args <code>String[]</code> containing a <code>TaskList</code> index.
     * @return the <code>TaskList</code> index represented by <code>args</code>.
     * @throws MikiArgsException if <code>args</code> does not represent an integer.
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

    private static int filterDayOfWeek(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 3) {
                continue;
            }
            String trunc = args.get(i).substring(0, 3).toLowerCase();
            if (DAY_NAMES.contains(trunc)) {
                res = DAY_NAMES.indexOf(trunc) + 1;
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterRelativeDay(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 3) {
                continue;
            }
            String trunc = args.get(i).substring(0, 3).toLowerCase();
            switch (trunc) {
            case "yst":
            case "yes":
                res = -1;
                matches++;
                args.remove(i);
                i--;
                break;
            case "tdy":
            case "tod":
                res = 0;
                matches++;
                args.remove(i);
                i--;
                break;
            case "tmr":
            case "tom":
                res = 1;
                matches++;
                args.remove(i);
                i--;
                break;
            default:
                // Do nothing
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterRelativeModifier(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 3) {
                continue;
            }
            String trunc = args.get(i).substring(0, 3).toLowerCase();
            switch (trunc) {
            case "lst":
            case "las":
            case "prv":
            case "pre":
                res = -1;
                matches++;
                args.remove(i);
                i--;
                break;
            case "ths":
            case "thi":
                res = 0;
                matches++;
                args.remove(i);
                i--;
                break;
            case "nxt":
            case "nex":
                res = 1;
                matches++;
                args.remove(i);
                i--;
                break;
            default:
                // Do nothing
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterMonth(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 3) {
                continue;
            }
            String trunc = args.get(i).substring(0, 3).toLowerCase();
            if (MONTH_NAMES.contains(trunc)) {
                res = MONTH_NAMES.indexOf(trunc) + 1;
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterMeridiem(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 2) {
                continue;
            }
            if (args.get(i).substring(0, 2).equalsIgnoreCase("am")) {
                res = 0;
                matches++;
                args.remove(i);
                i--;
            } else if (args.get(i).substring(0, 2).equalsIgnoreCase("pm")) {
                res = 1;
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static LocalTime filterTime(ArrayList<String> args) {
        LocalTime res = null;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).contains(":")
                    || args.get(i).toLowerCase().contains("am")
                    || args.get(i).toLowerCase().contains("pm")) {
                String cur = args.get(i).toLowerCase();
                int meridiem = NAT_NONE;
                if (cur.contains("am")) {
                    cur = cur.replace("am", "");
                    meridiem = 0;
                }
                if (cur.contains("pm")) {
                    cur = cur.replace("pm", "");
                    meridiem = 1;
                }
                String[] parts = cur.split(":");
                try {
                    res = LocalTime.of(Integer.parseInt(parts[0]), parts.length > 1 ? Integer.parseInt(parts[1]) : 0);
                    if (meridiem == 1) {
                        res = LocalTime.of(res.getHour() % 12 + 12, res.getMinute());
                    }
                } catch (NumberFormatException | DateTimeException ex) {
                    continue;
                }
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return null;
        }
        return res;
    }

    private static LocalDate filterDate(ArrayList<String> args) {
        LocalDate res = null;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (i >= 2 && args.get(i).length() == 4) {
                try {
                    res = LocalDate.of(
                            Integer.parseInt(args.get(i)),
                            Integer.parseInt(args.get(i - 1)),
                            Integer.parseInt(args.get(i - 2)));
                    matches++;
                    args.remove(i);
                    args.remove(i - 1);
                    args.remove(i - 2);
                    i -= 3;
                } catch (NumberFormatException | DateTimeException ex) {
                    // Do nothing
                }
            } else if (i < args.size() - 2 && args.get(i).length() == 4) {
                try {
                    res = LocalDate.of(
                            Integer.parseInt(args.get(i)),
                            Integer.parseInt(args.get(i + 1)),
                            Integer.parseInt(args.get(i + 2)));
                    matches++;
                    args.remove(i + 2);
                    args.remove(i + 1);
                    args.remove(i);
                    i--;
                } catch (NumberFormatException | DateTimeException ex) {
                    // Do nothing
                }
            }
        }
        if (matches != 1) {
            return null;
        }
        return res;
    }

    private static int filterYear(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() == 4) {
                try {
                    res = Integer.parseInt(args.get(i));
                } catch (NumberFormatException ex) {
                    continue;
                }
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterDayOfMonth(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).length() < 2) {
                continue;
            }
            String suf = args.get(i).substring(args.get(i).length() - 2).toLowerCase();
            if (suf.equals("st") || suf.equals("nd") || suf.equals("rd") || suf.equals("th")) {
                try {
                    res = Integer.parseInt(args.get(i).substring(0, args.get(i).length() - 2));
                } catch (NumberFormatException ex) {
                    continue;
                }
                matches++;
                args.remove(i);
                i--;
            }
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static int filterResidualDayOfMonth(ArrayList<String> args) {
        int res = NAT_NONE;
        int matches = 0;
        for (int i = 0; i < args.size(); i++) {
            try {
                res = Integer.parseInt(args.get(i));
            } catch (NumberFormatException ex) {
                continue;
            }
            matches++;
            args.remove(i);
            i--;
        }
        if (matches != 1) {
            return NAT_NONE;
        }
        return res;
    }

    private static LocalDateTime offsetDate(LocalDate date, ChronoUnit step, int amount, LocalTime time) {
        if (amount != NAT_NONE) {
            switch (step) {
            case DAYS:
                date = date.plusDays(amount);
                break;
            case WEEKS:
                date = date.plusWeeks(amount);
                break;
            case MONTHS:
                date = date.plusMonths(amount);
                break;
            case YEARS:
                date = date.plusYears(amount);
                break;
            case FOREVER:
            default:
                // Do nothing
            }
        }
        return LocalDateTime.of(date, time);
    }

    /**
     * Parses a <code>LocalDateTime</code> from a <code>String</code> natural language expression.
     *
     * @param dateStr                 <code>String</code> representing a natural-language date.
     * @param useLatestInterpretation whether to use the chronologically latest interpretation of <code>dateStr</code>,
     *                                instead of the chronologically earliest.
     * @return the <code>LocalDateTime</code> represented by <code>dateStr</code>
     * @throws NatDateParseException if <code>dateStr</code> could not be parsed as a natural-language date.
     */
    public static LocalDateTime parseDate(String dateStr, boolean useLatestInterpretation)
            throws NatDateParseException {
        try {
            return LocalDateTime.parse(dateStr, DATE_IN_FMT);
        } catch (DateTimeParseException ex) {
            // Do nothing
        }

        String cleanDateStr = dateStr.replace(",", " ")
                .replace("-", " ")
                .replace("/", " ")
                .replace("\\", " ")
                .replaceAll("\\s+", " ");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(cleanDateStr.split(" ")));

        // Parses natural date items out of supplied String.
        int dayOfWeek = filterDayOfWeek(tokens);
        int dayOfMonth = filterDayOfMonth(tokens);
        int month = filterMonth(tokens);
        int relativeDay = filterRelativeDay(tokens);
        int relativeModifier = filterRelativeModifier(tokens);
        LocalTime time = filterTime(tokens);
        int meridiem = filterMeridiem(tokens);
        LocalDate date = filterDate(tokens);
        int year = filterYear(tokens);
        int residualDayOfMonth = filterResidualDayOfMonth(tokens);

        LocalTime altTime = useLatestInterpretation ? LocalTime.MAX : LocalTime.MIN;
        if (time != null && meridiem != NAT_NONE) {
            if (meridiem == 1) {
                time = LocalTime.of(time.getHour() % 12 + 12, time.getMinute());
            }
        }

        // Constructs a LocalDateTime by trying combinations of natural date items, in increasing order of ambiguity.
        if (date != null) {
            return LocalDateTime.of(date, time != null ? time : altTime);
        }
        if (relativeDay != NAT_NONE) {
            date = now().plusDays(relativeDay);
            return LocalDateTime.of(date, time != null ? time : altTime);
        }
        if (dayOfMonth != NAT_NONE) {
            ChronoUnit relativeStep = ChronoUnit.MONTHS;
            if (year == NAT_NONE) {
                year = now().getYear();
            } else {
                relativeStep = ChronoUnit.FOREVER;
            }
            if (month == NAT_NONE) {
                month = now().getMonthValue();
            } else {
                relativeStep = ChronoUnit.YEARS;
            }
            try {
                date = LocalDate.of(year, month, dayOfMonth);
                return offsetDate(date, relativeStep, relativeModifier, time != null ? time : altTime);
            } catch (DateTimeException ex) {
                // Do nothing
            }
        }
        if (residualDayOfMonth != NAT_NONE && month != NAT_NONE) {
            ChronoUnit relativeStep = ChronoUnit.YEARS;
            if (year == NAT_NONE) {
                year = now().getYear();
            } else {
                relativeStep = ChronoUnit.FOREVER;
            }
            try {
                date = LocalDate.of(year, month, residualDayOfMonth);
                return offsetDate(date, relativeStep, relativeModifier, time != null ? time : altTime);
            } catch (DateTimeException ex) {
                // Do nothing
            }
        }
        if (dayOfWeek != NAT_NONE) {
            date = now().with(DayOfWeek.of(dayOfWeek));
            return offsetDate(date, ChronoUnit.WEEKS, relativeModifier, time != null ? time : altTime);
        }
        if (residualDayOfMonth != NAT_NONE) {
            ChronoUnit relativeStep = ChronoUnit.MONTHS;
            if (year == NAT_NONE) {
                year = now().getYear();
            } else {
                relativeStep = ChronoUnit.FOREVER;
            }
            month = now().getMonthValue();
            try {
                date = LocalDate.of(year, month, residualDayOfMonth);
                return offsetDate(date, relativeStep, relativeModifier, time != null ? time : altTime);
            } catch (DateTimeException ex) {
                // Do nothing
            }
        }
        if (month != NAT_NONE) {
            ChronoUnit relativeStep = ChronoUnit.YEARS;
            if (year == NAT_NONE) {
                year = now().getYear();
            } else {
                relativeStep = ChronoUnit.FOREVER;
            }
            try {
                date = useLatestInterpretation
                        ? YearMonth.of(year, month).atEndOfMonth()
                        : LocalDate.of(year, month, 1);
                return offsetDate(date, relativeStep, relativeModifier, time != null ? time : altTime);
            } catch (DateTimeException ex) {
                // Do nothing
            }
        }
        if (year != NAT_NONE) {
            try {
                date = useLatestInterpretation
                        ? LocalDate.of(year, 1, 1).with(TemporalAdjusters.lastDayOfYear())
                        : LocalDate.of(year, 1, 1);
                return LocalDateTime.of(date, time != null ? time : altTime);
            } catch (DateTimeException ex) {
                // Do nothing
            }
        }
        if (time != null) {
            return LocalDateTime.of(now(), time);
        }
        throw new NatDateParseException("could not parse natural date");
    }

    /**
     * Recombines a fragmented <code>String[]</code> of consecutive
     * space-separated tokens into a single <code>String</code>.
     *
     * @param args <code>String[]</code> to recombine.
     * @return the <code>String</code> produced by recombining <code>args</code>.
     */
    public static String recombine(String[] args) {
        String arg = "";
        for (int i = 0; i < args.length; i++) {
            arg += (i > 0 ? " " : "") + args[i];
        }
        return arg;
    }

    /**
     * Returns <code>true</code> if the supplied line of input is requesting the program to exit.
     *
     * @param cmdLine input to parse.
     * @return <code>true</code> if <code>cmdLine</code> represents an exit command.
     */
    public static boolean isExitString(String cmdLine) {
        return cmdLine.split(" ")[0].equalsIgnoreCase("bye");
    }

    /**
     * Returns <code>true</code> if the supplied command is a task-list display command.
     *
     * @param cmd command to check.
     * @return <code>true</code> if <code>cmd</code> represents a task-list display command.
     */
    public static boolean isListCommand(Command cmd) {
        return cmd instanceof ListTasks || cmd instanceof Find;
    }

    /**
     * Parses one supplied line of input String to create a <code>Command</code> action.
     *
     * @param cmdLine input denoting a <code>Command</code>.
     * @return a <code>Command</code> represented by <code>cmdLine</code>.
     */
    public static Command parse(String cmdLine) {
        String cmd = cmdLine.split(" ")[0].toLowerCase();
        String[] args = {};
        if (cmdLine.contains(" ")) {
            args = cmdLine.substring(cmd.length() + 1).split(" ", -1);
        }

        try {
            switch (cmd) {
            case "bye":
                return new Exit();
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
                return new Save(recombine(args));
            case "load":
                return new Load(recombine(args));
            case "find":
                return new Find(".*" + recombine(args) + ".*");
            default:
                throw new MikiArgsException("\"" + cmd + "\" isn't a real word!");
            }
        } catch (TaskParseException | MikiArgsException ex) {
            return new ExceptionPrint(ex);
        }
    }
}
