package duke;

public class Parser {
    private static Ui ui = new Ui();

    public static void parse(String userInput, TaskList lst) throws DukeException {

        String[] inputArr = userInput.split(" ", 2);

        if (userInput.equals("bye")) {
            ui.exit();
        } else if (userInput.equals("list")) {

            lst.printTaskList();

        } else if (userInput.startsWith("mark")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            lst.markTask(taskNum);

        } else if (userInput.startsWith("unmark")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            lst.unmarkTask(taskNum);

        } else if (userInput.startsWith("todo")) {

            String details = inputArr[1];
            lst.addTask(details, "todo");

        } else if (userInput.startsWith("deadline")) {

            String details = inputArr[1];
            lst.addTask(details, "deadline");

        } else if (userInput.startsWith("event")) {

            String details = inputArr[1];
            lst.addTask(details, "event");

        } else if (userInput.startsWith("delete")) {

            String details = inputArr[1];
            int taskNum = Integer.parseInt(details);
            lst.deleteTask(taskNum);

        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

