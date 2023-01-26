import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);

        String introMsg = "Hello! I'm Duke.\n" + "What can I do for you today?";
        DukeCommands.printMsg(introMsg);

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = DukeCommands.getData();
        boolean cont = true;

        while (cont) {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);
            try {
                switch (inputSplit[0]) {
                case "bye":
                    cont = false;
                    break;
                case "list":
                    DukeCommands.printTasks(tasks);
                    break;
                case "mark":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Mark command missing list numbering.");
                    }
                    DukeCommands.execMarkTask(inputSplit[1], tasks);
                    break;
                case "unmark":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Unmark command missing list numbering.");
                    }
                    DukeCommands.execUnmarkTask(inputSplit[1], tasks);
                    break;
                case "todo":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Todo command missing description.");
                    }
                    DukeCommands.execTodo(inputSplit[1], tasks);
                    break;
                case "deadline":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Deadline command missing description.");
                    }
                    DukeCommands.execDeadline(inputSplit[1], tasks);
                    break;
                case "event":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Event command missing description.");
                    }
                    DukeCommands.execEvent(inputSplit[1], tasks);
                    break;
                case "delete":
                    if (inputSplit.length < 2) {
                        throw new DukeException("Delete command missing list numbering.");
                    }
                    DukeCommands.execDeleteTask(inputSplit[1], tasks);
                    break;
                default:
                    throw new DukeException("Sorry but I don't understand what this means.");
                }
            } catch (DukeException e) {
                DukeCommands.printError(e);
            } catch (Exception e) {
                DukeCommands.printMsg("Unknown command/error not caught!\n" + "Please try again!");
            }
        }

        DukeCommands.updateData(tasks);

        String exitMsg = "Thank you for coming!\n" + "Hope to see you again soon!\n" + "~~Bye";
        DukeCommands.printMsg(exitMsg);
    }
}
