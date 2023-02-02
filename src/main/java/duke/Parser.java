package duke;

public class Parser {

    private static Ui ui = new Ui();

    public static void parse(String command, TaskList taskList) throws DukeException {
        if (command.equals("bye")) {

            ui.exit();

        } else if (command.equals("list")) {

            ui.printMessage("Here are the tasks in your list:\n" + taskList.toString());

        } else if (command.startsWith("mark")) {

            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            taskList.markTask(index);

        } else if (command.startsWith("unmark")) {

            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            taskList.unmarkTask(index);

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

            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            taskList.delete(index);

        } else {
            throw new UnknownCommandException();
        }
    }

}
