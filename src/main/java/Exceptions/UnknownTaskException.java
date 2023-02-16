package exceptions;

public class UnknownTaskException extends DukeException {
    public UnknownTaskException(String s) {
        super("OH NO!! I DONT KNOW WHAT " + s.toUpperCase() + " MEANS!!");
    }
}