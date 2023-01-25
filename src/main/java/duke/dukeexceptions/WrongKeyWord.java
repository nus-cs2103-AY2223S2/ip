package duke.dukeexceptions;

/* When the user inputs wrong keywords*/
public class WrongKeyWord extends DukeException{

    public WrongKeyWord(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "☹ HEY stop talking rubbish!! I could be eating cookies instead";
    }
}
