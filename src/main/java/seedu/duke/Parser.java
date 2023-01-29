/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

/**
 * Represents the String of input from the user.
 * A <code>Parser</code> object corresponds to an input.
 * e.g., <code>"todo read books"</code>
 */
public class Parser {

    protected String input;
    protected String[] inputArr;
    protected String command;

    /**
     * Constructor for the Parser class.
     *
     * @param input The input from the user.
     */
    public Parser(String input) {
        this.input = input;
        this.inputArr = input.split(" ");
        this.command = this.inputArr[0];
    }

    /**
     * Returns the String description of the task from the input.
     * If the description is empty, throw a DukeException.
     *
     * @param str The description of the task.
     * @param task The task name.
     * @return The description of the task.
     * @throws DukeException If str is empty.
     */
    public String checkDescription(String str, String task) throws DukeException {
        if (str.equals("")) {
            String message = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    /**
     * Returns the String time of the task from the input.
     * If the time is empty, throw a DukeException.
     *
     * @param str The time of the task.
     * @param task The task name.
     * @param type The task type. e.g., by
     * @return The time of the task in String.
     * @throws DukeException If str is empty.
     */
    public String checkTime(String str, String task, String type) throws DukeException {
        if (str.equals("")) {
            String message = "☹ OOPS!!! The /" + type + " part of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    /**
     * Returns the Duke command of the task from the input.
     * If the command is unknown, throw a DukeException.
     *
     * @param command The command from the user.
     * @return The Duke command.
     * @throws DukeException If command is unknown.
     */
    public Duke.Commands checkCommand(String command) throws DukeException {
        boolean flag = true;
        for (Duke.Commands c : Duke.Commands.values()) {
            if (command.equals(c.name())) {
                flag = false;
            }
        }
        if (flag) {
            String message = "____________________________________________________________\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException(message);
        }
        return Duke.Commands.valueOf(command);
    }

    /**
     * Returns the String for find.
     *
     * @return The String to match in find.
     */
    public String findMatchDescription() {
        String temp = "";
        for (int i = 1; i < inputArr.length; i++) {
            temp = temp + inputArr[i];
        }
        return temp.trim();
    }
}
