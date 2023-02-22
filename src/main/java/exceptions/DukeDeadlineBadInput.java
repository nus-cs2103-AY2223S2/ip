package exceptions;

public class DukeDeadlineBadInput extends DukeException{

    public DukeDeadlineBadInput() {
        super("Incorrect format for deadline. Did you mark the deadline with \"/by\"?\n" +
                "Correct usage: deadline <task> /by <deadline>");
    }
}
