package duke;

/**
 * Represents the type of Command when there is a String user input.
 */
public class Query {
    protected String input;

    public Query(String input) {
        this.input = input;
    }

    /**
     * Determines if a string consists of only numbers
     * @param str String to check
     * @return a boolean indicating whether the string is numeric or not.
     */
    static boolean isNumeric(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an enumeration of QueryType that tells what type of command the input is.
     * @param input String to check.
     * @return A QueryType indicating the type of command.
     */
    static QueryType queryType(String input) {
        if (input.equals("list")) {
            return QueryType.list;
        } else if (input.equals("bye")) {
            return QueryType.exit;
        }

        String[] inputArr = input.split(" ");
        if (inputArr.length == 2 && isNumeric(inputArr[1])) {
            if (inputArr[0].equals("mark")) {
                return QueryType.mark;
            } else if (inputArr[0].equals("unmark")) {
                return QueryType.unmark;
            } else if (inputArr[0].equals("delete")) {
                return QueryType.delete;
            }
        } else if (inputArr[0].equals("todo")) {
            return QueryType.todo;
        } else if (inputArr[0].equals("deadline")) {
            return QueryType.deadline;
        } else if (inputArr[0].equals("event")) {
            return QueryType.event;
        }
        return QueryType.invalid;
    }
}
