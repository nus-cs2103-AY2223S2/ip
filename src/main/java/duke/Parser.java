package duke;

public class Parser {

    private static Ui ui = new Ui();

    public static void parse(String command, TaskList tasks) throws DukeException {
        if (command.equals("bye")) {

            ui.exit();

        } else if (command.equals("list")) {

            ui.printMessage("Here are the tasks in your list:\n" + tasks.toString());

        } else if (command.startsWith("mark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                tasks.markTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to mark!");
            }

        } else if (command.startsWith("unmark")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                tasks.unmarkTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to unmark!");
            }

        } else if (command.startsWith("todo")) {

            String description = command.replace("todo", "");
            tasks.addTask(description, "todo");

        } else if (command.startsWith("deadline")) {

            String description = command.replace("deadline", "");
            tasks.addTask(description, "deadline");

        } else if (command.startsWith("event")) {

            String description = command.replace("event", "");
            tasks.addTask(description, "event");

        } else if (command.startsWith("delete")) {
            try {
                String str = command.split(" ", 2)[1];
                int index = Integer.parseInt(str);
                tasks.deleteTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please give the index of the task you wish to delete!");
            }
        } else {
            throw new UnknownCommandException();
        }
    }

}
