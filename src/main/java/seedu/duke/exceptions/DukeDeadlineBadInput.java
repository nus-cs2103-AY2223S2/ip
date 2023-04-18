package seedu.duke.exceptions;

public class DukeDeadlineBadInput extends DukeException{

    public DukeDeadlineBadInput() {
        super("Incorrect format for deadline. Did you mark the deadline with \"/by\"?\n" +
                "Correct usage: deadline <task> /by <deadline>\n" +
                "Input format for dates: yyyy-mm-dd");
    }
}
