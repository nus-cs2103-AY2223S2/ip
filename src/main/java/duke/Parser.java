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


        String[] inputArr = userInput.split(" ", 2);

        if (userInput.equals("bye")) {

            return "bye";

        } else if (userInput.equals("list")) {

            return "Here are the tasks in your list:\n" + lst.getTaskList();

        } else if (userInput.startsWith("mark")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            return lst.markTask(taskNum);

        } else if (userInput.startsWith("unmark")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            return lst.unmarkTask(taskNum);

        } else if (userInput.startsWith("todo")) {

            String details = inputArr[1];
            return lst.addTask(details, "todo");

        } else if (userInput.startsWith("deadline")) {

            String details = inputArr[1];
            return lst.addTask(details, "deadline");

        } else if (userInput.startsWith("event")) {

            String details = inputArr[1];
            return lst.addTask(details, "event");

        } else if (userInput.startsWith("delete")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            return lst.deleteTask(taskNum);

        } else if (userInput.startsWith("find")) {
            try {
                String keyword = inputArr[1];
                return lst.findTask(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please enter the correct keyword";
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

