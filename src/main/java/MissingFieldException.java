public class MissingFieldException extends DukeException{
    public MissingFieldException(String field) {
        super(field + " cannot be empty.");
    }
}
