import java.util.HashMap;

public class Deadline extends Task{

    private String by;

    public Deadline(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("deadline"));
        by = parsed.get("/by");
        abbreviation = 'D';
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    public String getBreakdown() {
        return "deadline " + task + " /by " + by;
    }
}

