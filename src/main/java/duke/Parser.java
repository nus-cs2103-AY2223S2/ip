package duke;

/**
 * deals with understanding the instructions given to Duke
 */
public class Parser {

    private static Ui ui = new Ui();

    /**
     * Interprets the user input and executes the corresponding actions
     * 
     * @param command  input given by the user
     * @param taskList the tasklist to be changed according to the user input
     * @throws DukeException invalid input
     */
    public static void parse(String command, TaskList taskList) throws DukeException {
        if (command.equals("bye")) {

            ui.exit();

        } else if (command.equals("list")) {

            ui.printMessage("Here are the tasks in your list:\n" + taskList.toString());

        } else if (command.startsWith("mark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                taskList.markTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to mark!");
            }

        } else if (command.startsWith("unmark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                taskList.unmarkTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to unmark!");
            }

        } else if (command.startsWith("todo")) {

            String description = command.replace("todo", "");
            taskList.addTask(description, "todo");

        } else if (command.startsWith("deadline")) {

            String description = command.replace("deadline", "");
            taskList.addTask(description, "deadline");

        } else if (command.startsWith("event")) {

            String description = command.replace("event", "");
            taskList.addTask(description, "event");

        } else if (command.startsWith("delete")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                taskList.delete(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to delete!");
            }
        } else {
            throw new UnknownCommandException();
        }
    }

}
