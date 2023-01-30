package Exceptions;

public class MissingInputException extends WessyException {
    static String ENDING = " cannot be empty.";
    private final String cmd;

    public MissingInputException(String cmd) {
        super(cmd.substring(cmd.length() - 4).equals("mark") ? "The chosen task number of " : "The description of ");
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        String article = "a ";
        if (cmd.equals("mark") || cmd.equals("unmark")) {
            article = cmd.equals("mark") ? "a " : "an ";
        } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")){
            article = cmd.equals("event")?"an ":"a ";
        }
        return super.toString() + article + cmd + ENDING;
    }
}
