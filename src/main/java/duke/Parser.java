package duke;

/**
 * Parser is a program that help identify the command type of user inputs
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Parser {
    public Parser() {
    }

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param input input from user.
     * @return Type of the input/command in String form.
     */
    public String parse(String input) {
        int len = input.length();
        if (input.equals("bye")) {
            return "BYE";
        } else if (input.equals("list")) {
            return "LIST";
        } else if (len >= 8 && input.substring(0, 6).equals("delete")) {
            return "DELETE";
        } else if (len >= 6 && input.substring(0, 4).equals("mark")) {
            return "MARK";
        } else if (len >= 8 && input.substring(0, 6).equals("unmark")) {
            return "UNMARK";
        } else if (len >= 6 && input.substring(0, 4).equals("todo")) {
            return "TODO";
        } else if (len >= 10 && input.substring(0, 8).equals("deadline")) {
            return "DEADLINE";
        } else if (len >= 7 && input.substring(0, 5).equals("event")) {
            return "EVENT";
        } else if (len >= 6 && input.substring(0, 4).equals("find")) {
            return "FIND";
        } else {
            return "ERROR";
        }
    }
}
