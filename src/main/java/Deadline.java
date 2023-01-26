import java.text.ParseException;
import java.util.ArrayList;
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
            Deadline deadline = new Deadline(objective, by);
            deadline.done = done;
            return deadline;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new TaskParseException("Deadline data is malformed:\n" + ex.getMessage());
        }
    }

    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "D " + done
                + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (by.codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        Collections.addAll(repres, by.split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
