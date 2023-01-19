import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String objective, String from, String to) {
        super(objective);
        this.from = from;
        this.to = to;
    }

    public static Event parseArgs(String[] args) throws TaskParseException {
        String objective = "";
        String from = "";
        String to = "";
        boolean token_from = false;
        boolean token_to = false;
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) throw new TaskParseException("This event has too many start-times!");
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) throw new TaskParseException("This event has too many end-times!");
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
            } else {
                objective += (objective.isEmpty() ? "" : " ") + args[i];
            }
        }
        if (objective.isEmpty()) throw new TaskParseException("This event is missing its body text!");
        return new Event(objective, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " | to: " + to + ")";
    }
}
