import controllers.*;
import database.Database;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;
import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();
    private static final Database database = new Database("duke.txt");
    private static final Scanner in = new Scanner(System.in);

    private static Command parseInput(String cmd, String arguments) {
        switch(cmd) {
            case "bye": return new GoodbyeCommand();
            case "list": return new ListCommand();
            case "mark": return new MarkCommand(arguments);
            case "unmark": return new UnmarkCommand(arguments);
            case "delete": return new DeleteCommand(arguments);
            case "todo": return new TodoCommand(arguments);
            case "deadline": return new DeadlineCommand(arguments);
            case "event": return new EventCommand(arguments);
            default: return new Command(CommandType.INVALID) {
                @Override
                public void execute() throws DukeException {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            };
        }
    }

    public static void main(String[] args) {
        try {
            database.connect();
            System.out.println("Successfully connected to database.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return; // fatal error, cannot continue.
        }
        System.out.println("Hello! I'm Duke. What can i do for you?");
        System.out.println(
                "These are the available commands:" +
                        "\n\t todo [description]" +
                        "\n\t event [description] \\from [date] \\to [date]" +
                        "\n\t deadline [description] \\by [date]" +
                        "\n----------------------------------------");
        while (in.hasNext()) {
            String input = in.nextLine().toLowerCase();
            String cmdType = input.split(" ")[0];
            Command cmd = parseInput(cmdType, input);
            try {
                cmd.execute();
                if (cmd.isTerminating()) {
                    database.write(taskList);
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
