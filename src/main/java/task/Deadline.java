package task;

import exception.TaskParseException;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Implementation of a <code>Task</code> with one chronological deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline with the specified objective,
     * and the specified deadline timing.
     *
     * @param objective the description of this deadline's objective
     * @param by the deadline time of this deadline
     */
    public Deadline(String objective, LocalDateTime by) {
        super(objective);
        this.by = by;
    }

    /**
     * Parses the supplied <code>String[]</code> command-line arguments to create a deadline.
     *
     * @param args the arguments containing the deadline to be parsed
     * @return a deadline represented by <code>args</code>
     * @throws TaskParseException if <code>args</code> does not represent a valid deadline
     */
    public static Deadline parseArgs(String args[]) throws TaskParseException {
        String objective = "";
        String by = "";
        boolean token_by = false;
        if (Collections.frequency(Arrays.asList(args), "/by") > 1) throw new TaskParseException("This deadline has too many timings!");
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/by")) {
                token_by = true;
                continue;
            }
            if (token_by) {
                by += (by.isEmpty() ? "" : " ") + args[i];
            } else {
                objective += (objective.isEmpty() ? "" : " ") + args[i];
            }
        }
        if (objective.isEmpty()) throw new TaskParseException("This deadline is missing its body text!");
        if (by.isEmpty()) throw new TaskParseException("This deadline is missing its deadline! Use /by [date]");
        try {
            return new Deadline(objective, LocalDateTime.parse(by, DATE_IN_FMT));
        } catch (DateTimeParseException ex) {
            throw new TaskParseException(by + " needs to be formatted as " + DATE_IN_FMT_STR + "!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean beforeDate(LocalDateTime date) {
        return by.isBefore(date) || by.isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean afterDate(LocalDateTime date) {
        return by.isAfter(date) || by.isEqual(date);
    }

    /**
     * Parses the supplied <code>String[]</code> save data to create a deadline.
     *
     * @param data the data containing the deadline to be parsed
     * @return a deadline represented by <code>data</code>
     * @throws TaskParseException if <code>data</code> does not represent a valid deadline
     */
    public static Deadline parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("D")) throw new TaskParseException("Invalid deadline data format");
            boolean done = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            int byLines = Integer.parseInt(header[3]);
            String objective = "";
            String by = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) objective += (i > 0 ? "\n" : "") + data[seek++];
            for (int i = 0; i < byLines; i++) by += (i > 0 ? "\n" : "") + data[seek++];
            Deadline deadline = new Deadline(objective, LocalDateTime.parse(by, DATE_IN_FMT));
            deadline.done = done;
            return deadline;
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
            throw new TaskParseException("Deadline data is malformed:\n" + ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "D " + done
                + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (by.format(DATE_IN_FMT).codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        Collections.addAll(repres, by.format(DATE_IN_FMT).split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + by.format(DATE_OUT_FMT) + ")";
    }
}
