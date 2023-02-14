package duke;

public class NoDate extends Exception {
    public NoDate() {
        super("No deadline date found. Please input a date in this format: yyyy-mm-dd");
    }
}
