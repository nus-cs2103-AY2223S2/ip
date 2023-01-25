public class MissingClauseException extends DukeException {
    public MissingClauseException(String clause) {
        super("You have to include your '" + clause +  "' clause ><");
    }
}
