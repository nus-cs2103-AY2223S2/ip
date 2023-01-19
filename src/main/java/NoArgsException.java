public class NoArgsException extends DukeException {

    public NoArgsException(String str) {
        super("☹ OOPS!!! The description of an " + str + " cannot be empty.");
    }
}
