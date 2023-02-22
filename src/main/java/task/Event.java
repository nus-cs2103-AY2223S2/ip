package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import exception.NatDateParseException;
import exception.TaskParseException;

/**
 * Implementation of a <code>Task</code> with a chronological start-time and end-time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event with the specified objective,
     * and the specified starting and ending timings.
     *
     * @param objective description of this event's objective.
     * @param from      start-time of this event.
     * @param to        end-time of this event.
     */
    public Event(String objective, LocalDateTime from, LocalDateTime to) {
        super(objective);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses the supplied <code>String[]</code> command-line arguments to create an event.
     *
     * @param args arguments containing the event to be parsed.
     * @return an event represented by <code>args</code>.
     * @throws TaskParseException if <code>args</code> does not represent a valid event.
     */
    public static Event parseArgs(String[] args) throws TaskParseException {
        String objective = "";
        String from = "";
        String to = "";
        boolean isInTokenFrom = false; // true after scanning "/from" token - subsequent text is part of "from"-timing
        boolean isInTokenTo = false; // true after scanning "/to" token - subsequent text is part of "to"-timing
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) {
            throw new TaskParseException("This event has too many start-times!");
        }
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) {
            throw new TaskParseException("This event has too many end-times!");
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
            } else {
                objective += (objective.isEmpty() ? "" : " ") + args[i];
            }
        }
        if (objective.isEmpty()) {
            throw new TaskParseException("This event is missing its body text!");
        }
        if (from.isEmpty()) {
            throw new TaskParseException("This event is missing its start-time! Use /from [date]");
        }
        if (to.isEmpty()) {
            throw new TaskParseException("This event is missing its end-time! Use /to [date]");
        }

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = Parser.parseDate(from, false);
        } catch (NatDateParseException ex) {
            throw new TaskParseException("\"" + from + "\"... is... when??!? write " + DATE_IN_FMT_STR + "!!");
        }
        try {
            toDate = Parser.parseDate(to, true);
        } catch (NatDateParseException ex) {
            throw new TaskParseException("\"" + to + "\"... is... when??!? write" + DATE_IN_FMT_STR + "!!");
        }

        return new Event(objective, fromDate, toDate);
    }

    /**
     * Parses the supplied <code>String[]</code> save data to create an event.
     *
     * @param data data containing the event to be parsed.
     * @return an event represented by <code>data</code>.
     * @throws TaskParseException if <code>data</code> does not represent a valid event.
     */
    public static Event parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("E")) {
                throw new TaskParseException("Invalid event data format");
            }
            boolean isDone = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            int fromLines = Integer.parseInt(header[3]);
            int toLines = Integer.parseInt(header[4]);

            String objective = "";
            String from = "";
            String to = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) {
                objective += (i > 0 ? "\n" : "") + data[seek++];
            }
            for (int i = 0; i < fromLines; i++) {
                from += (i > 0 ? "\n" : "") + data[seek++];
            }
            for (int i = 0; i < toLines; i++) {
                to += (i > 0 ? "\n" : "") + data[seek++];
            }

            Event event = new Event(objective,
                    LocalDateTime.parse(from, DATE_IN_FMT),
                    LocalDateTime.parse(to, DATE_IN_FMT));
            event.isDone = isDone;
            return event;
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
            throw new TaskParseException("Event data is malformed:\n" + ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBeforeDate(LocalDateTime date) {
        return from.isBefore(date) || from.isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAfterDate(LocalDateTime date) {
        return to.isAfter(date) || to.isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "E " + isDone
                + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (from.format(DATE_IN_FMT).codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (to.format(DATE_IN_FMT).codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        Collections.addAll(repres, from.format(DATE_IN_FMT).split("\n"));
        Collections.addAll(repres, to.format(DATE_IN_FMT).split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from.format(DATE_OUT_FMT) + " - " + to.format(DATE_OUT_FMT) + ")";
    }
}
