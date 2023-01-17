import java.util.HashMap;

public class Event extends Task {

    String from;
    String to;

    public Event(HashMap<String, String> parsed) {
        super(parsed.get("event"));
        from = parsed.get("/from");
        to = parsed.get("/to");
        abbreviation = 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
