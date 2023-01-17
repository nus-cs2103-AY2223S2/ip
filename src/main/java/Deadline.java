import java.util.HashMap;

public class Deadline extends Task{

    String by;

    public Deadline(HashMap<String, String> parsed) throws Exception {
        super(parsed.get("deadline"));
        by = parsed.get("/by");
        abbreviation = 'D';
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

