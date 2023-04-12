package exceptions;

public class DukeEventBadInput extends DukeException{
    public DukeEventBadInput() {
        super("Invalid Event\n" +
                "Correct Usage: event <description> /from <from time> /to <to time>\n" +
                "Input format for dates: yyyy-mm-dd");
    }
}
