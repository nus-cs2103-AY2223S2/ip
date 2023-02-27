import java.util.ArrayList;

public class Parser {
    public Parser() {

    }


    // TASKLIST CLASS validate parse function

    public static void validateInput(String input, ArrayList<Task> storage) throws DukeException {
        if (!input.contains("todo") && !input.contains("event") && !input.contains("display") && !input.contains("deadline") && !input.contains("mark") && !input.contains("bye") && !input.contains("delete")) {
            throw new DukeException("OOPS! invalid command la bro");
        }
        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("todo") && input.length() <= 5) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \ntodo xxx");
        }

        if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("event") && (!input.contains("/from") || input.indexOf("/from") == 6 || !input.contains("/to") || input.indexOf("/from") > input.indexOf("/to"))) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }

        if (input.length() > 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            try {
                Integer.parseInt(input.substring(7));
            } catch(NumberFormatException e) {
                throw new DukeException("OOPS wrong format my brother! consider this format: \ndelete INSERT_NUMBER");
            }
            if (Integer.parseInt(input.substring(7)) > storage.size()) {
                throw new DukeException("OOPS inserted number is invalid");
            }
        }

        if (input.length() > 8 && input.substring(0, 8).equalsIgnoreCase("deadline") && (!input.contains("/by") || input.indexOf("/by") == 9)) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }
    }
}
