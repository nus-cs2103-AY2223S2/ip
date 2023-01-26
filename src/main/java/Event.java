import java.text.ParseException;
import java.util.ArrayList;
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

    public static Event parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("E")) throw new TaskParseException("Invalid event data format");
            boolean done = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            int fromLines = Integer.parseInt(header[3]);
            int toLines = Integer.parseInt(header[4]);
            String objective = "";
            String from = "";
            String to = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) objective += (i > 0 ? "\n" : "") + data[seek++];
            for (int i = 0; i < fromLines; i++) from += (i > 0 ? "\n" : "") + data[seek++];
            for (int i = 0; i < toLines; i++) to += (i > 0 ? "\n" : "") + data[seek++];
            Event event = new Event(objective, from, to);
            event.done = done;
            return event;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new TaskParseException("Event data is malformed:\n" + ex.getMessage());
        }
    }
    
    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "E " + done
                + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (from.codePoints().filter(c -> c == '\n').count() + 1)
                + " " + (to.codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        Collections.addAll(repres, from.split("\n"));
        Collections.addAll(repres, to.split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " | to: " + to + ")";
    }
}
