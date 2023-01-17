import java.util.HashMap;

public class ToDo extends Task {

    public ToDo(HashMap<String, String> parsed) {
        super(parsed.get("todo"));
        abbreviation = 'T';
    }

}
