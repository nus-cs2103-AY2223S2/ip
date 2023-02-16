package duke;

/**
 * Parser class
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * Parses the user's input and executes the corresponding actions
     * @param userInput user's input
     * @param lst user's task list
     * @return String response from parse
     * @throws DukeException
     */
    public static String parse(String userInput, TaskList lst) throws DukeException {
        if (userInput.equals("bye")) {
            return ui.exit();
        } else if (userInput.equals("list")) {
            return ui.listCommandMessage() + lst.getTaskList();
        } else if (userInput.startsWith("mark")) {
            return lst.markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            return lst.unmarkTask(userInput);
        } else if (userInput.startsWith("todo")) {
            return lst.addToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return lst.addDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            return lst.addEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            return lst.deleteTask(userInput);
        } else if (userInput.startsWith("find")) {
            return lst.findTask(userInput);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

