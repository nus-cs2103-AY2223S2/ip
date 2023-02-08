package duke;

/**
 * deals with understanding the instructions given to Duke
 */
public class Parser {

    /**
     * Interprets the user input and executes the corresponding actions
     * 
     * @param command input given by the user
     * @param tasks   the tasklist to be changed according to the user input
     * @throws DukeException invalid input
     */
    public static String parse(String command, TaskList tasks) throws DukeException {
        if (command.equals("bye")) {

            return "Bye. Hope to see you again soon!";

        } else if (command.equals("list")) {

            return "Here are the tasks in your list:\n" + tasks.toString();

        } else if (command.startsWith("mark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                return tasks.markTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please give the index of the task you wish to mark!";
            }
        } else if (command.startsWith("unmark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                return tasks.unmarkTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please give the index of the task you wish to unmark!";
            }

        } else if (command.startsWith("todo")) {

            String description = command.replace("todo", "");
            return tasks.addTask(description, "todo");

        } else if (command.startsWith("deadline")) {

            String description = command.replace("deadline", "");
            return tasks.addTask(description, "deadline");

        } else if (command.startsWith("event")) {

            String description = command.replace("event", "");
            return tasks.addTask(description, "event");

        } else if (command.startsWith("delete")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                return tasks.deleteTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please give the index of the task you wish to delete!";
            }
        } else if (command.startsWith("find")) {
            try {
                String keyword = command.split(" ", 2)[1];
                return tasks.findTask(keyword);

            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please input the keyword!";
            }
        } else {
            throw new UnknownCommandException();
        }
    }
}
