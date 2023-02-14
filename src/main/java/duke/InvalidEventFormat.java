package duke;

public class InvalidEventFormat extends Exception {
    public InvalidEventFormat() {
        super("Wrong format for Event tasks. Use this format: event (description) /from X /to Y");
    }
}
