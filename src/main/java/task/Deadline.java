package task;

import exception.TaskParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String objective, LocalDateTime by) {
        super(objective);
        this.by = by;
    }

    public static Deadline parseArgs(String args[]) throws TaskParseException {
        String objective = "";
        String by = "";
        boolean isInTokenBy = false;
        if (Collections.frequency(Arrays.asList(args), "/by") > 1) {
            throw new TaskParseException("This deadline has too many timings!");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/by")) {
                isInTokenBy = true;
                continue;
            }
            if (isInTokenBy) {
                by += (by.isEmpty() ? "" : " ") + args[i];
            } else {
                objective += (objective.isEmpty() ? "" : " ") + args[i];
            }
        }
        if (objective.isEmpty()) {
            throw new TaskParseException("This deadline is missing its body text!");
        }
        if (by.isEmpty()) {
            throw new TaskParseException("This deadline is missing its deadline! Use /by [date]");
        }
        try {
            return new Deadline(objective, LocalDateTime.parse(by, DATE_IN_FMT));
        } catch (DateTimeParseException ex) {
            throw new TaskParseException(by + " needs to be formatted as " + DATE_IN_FMT_STR + "!");
        }
    }

    public static Deadline parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("D")) {
                throw new TaskParseException("Invalid deadline data format");
            }
            boolean isDone = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            int byLines = Integer.parseInt(header[3]);
            String objective = "";
            String by = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) {
                objective += (i > 0 ? "\n" : "") + data[seek++];
            }
            for (int i = 0; i < byLines; i++) {
                by += (i > 0 ? "\n" : "") + data[seek++];
            }
            Deadline deadline = new Deadline(objective, LocalDateTime.parse(by, DATE_IN_FMT));
            deadline.isDone = isDone;
            return deadline;
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
            throw new TaskParseException("Deadline data is malformed:\n" + ex.getMessage());
        }
    }

    @Override
    public boolean isBeforeDate(LocalDateTime date) {
        return by.isBefore(date) || by.isEqual(date);
    }

    @Override
    public boolean isAfterDate(LocalDateTime date) {
        return by.isAfter(date) || by.isEqual(date);
    }

    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "D " + isDone
                + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (by.format(DATE_IN_FMT).codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        Collections.addAll(repres, by.format(DATE_IN_FMT).split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + by.format(DATE_OUT_FMT) + ")";
    }
}
