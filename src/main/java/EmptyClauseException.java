public class EmptyClauseException extends DukeException {
    public EmptyClauseException(String clause) {
        super("You cannot leave your '" + clause + "' clause empty ><");
    }
}
