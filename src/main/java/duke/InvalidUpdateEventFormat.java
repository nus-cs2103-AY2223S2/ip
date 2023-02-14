package duke;

public class InvalidUpdateEventFormat extends Exception {
    public InvalidUpdateEventFormat() {
        super("Wrong format to update Event tasks. Use this format: update (index) /from X /to Y");
    }
}
