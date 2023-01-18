import java.util.HashMap;

public class ToDo extends Task {

    public ToDo(HashMap<String, String> parsed) throws DukeException{
        super(parsed.get("todo"));
        abbreviation = 'T';
    }

}
