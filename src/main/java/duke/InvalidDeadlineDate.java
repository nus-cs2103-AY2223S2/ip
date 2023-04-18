package duke;

public class InvalidDeadlineDate extends Exception {
    public InvalidDeadlineDate() {
        super("Wrong format! Please input in this format: yyyy-mm-dd");
    }
}
