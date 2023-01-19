import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;

public class Deadline extends Task {
    private String by;

    public Deadline(String objective, String by) {
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
        return new Deadline(objective, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
