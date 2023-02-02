import java.util.Arrays;

public class Parser {

    TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void parse(String commandLine) throws DukeException {

        System.out.printf(Duke.DIV_OPEN);

        String[] command = commandLine.split(" ");

        switch (command[0]) {

        case "list":
            tasks.printList();
            break;

        case "mark":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.markTask(command[1]);

            break;

        case "unmark":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.unmarkTask(command[1]);
            break;

        case "todo":
            if (command.length < 2) {
                throw new DukeException("The description of a todo cannot be empty!");
            }
            tasks.addTodo(command);
            break;

        case "deadline":
            int byIndex = Arrays.asList(command).indexOf("/by");
            if (command.length < 4 || byIndex == -1) {
                throw new DukeException("Too few arguments!");
            }
            if (byIndex == command.length - 1) {
                throw new DukeException("Check the format again!");
            }
            tasks.addDeadline(command, byIndex);
            break;

        case "event":
            int fromIndex = Arrays.asList(command).indexOf("/from");
            int toIndex = Arrays.asList(command).indexOf("/to");
            if (command.length < 6 || fromIndex == -1 || toIndex == -1) {
                throw new DukeException("Too few arguments!");
            }
            if (fromIndex + 1 >= toIndex || toIndex == command.length - 1) {
                throw new DukeException("Check the format again!");
            }
            tasks.addEvent(command, fromIndex, toIndex);
            break;

        case "delete":
            if (command.length != 2) {
                throw new DukeException("Please check the number of your arguments!");
            }
            tasks.deleteTask(command[1]);
            break;

        default:
            throw new DukeException("Invalid/Unknown command.");
        }

        System.out.println(Duke.DIV_CLOSE); // DIV_CLOSE for output

    }

}
