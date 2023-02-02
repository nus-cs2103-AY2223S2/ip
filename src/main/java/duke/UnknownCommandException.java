package duke;
public class UnknownCommandException extends DukeException{

    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    @Override
    public String toString() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }
}
