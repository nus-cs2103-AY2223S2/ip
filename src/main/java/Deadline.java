import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

    @Override
    public boolean beforeDate(LocalDateTime date) {
        return by.isBefore(date) || by.isEqual(date);
    }

    @Override
    public boolean afterDate(LocalDateTime date) {
        return by.isAfter(date) || by.isEqual(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + by.format(DATE_OUT_FMT) + ")";
    }
}
