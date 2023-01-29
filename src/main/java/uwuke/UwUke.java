package uwuke;

import java.util.Scanner;

import uwuke.input.Advisor;
import uwuke.input.Command;
import uwuke.output.DukeException;
import uwuke.output.Printer;
import uwuke.output.Storage;
import uwuke.task.TaskList;

public class UwUke {

    private final static int CAPACITY = 100;

    private static Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.matches("^mark\\s\\d+$")) {
            return Command.MARK;
        } else if (input.matches("^unmark\\s\\d+$")) {
            return Command.UNMARK;
        } else if (input.matches("^deadline\\s.+/by\\s.+$")) {
            return Command.DEADLINE;
        } else if (input.matches("^event\\s.+/from\\s.+/to\\s.+$")) {
            return Command.EVENT;
        } else if (input.matches("^todo\\s.+$")) {
            return Command.TODO;
        } else if (input.matches("^delete\\s\\d+$")) {
            return Command.DELETE;
        } else if (input.matches("find\\s.+")) {
            return Command.FIND;
        } else {
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Printer.uwu();
        TaskList tasks = new TaskList(CAPACITY);
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file");
            tasks = new TaskList();
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.contains(",")) { // Can potentially cause fatal errors when trying to read files if commas were allowed.
                    throw new DukeException("Please do not use reserved character \',\'.");
                }
                switch (getCommand(input)) {
                case LIST:
                    Printer.printTasks(tasks.getList());
                    break;
                case DEADLINE:
                    tasks.addDeadline(input);
                    break;
                case EVENT:
                    tasks.addEvent(input);
                    break;
                case TODO:
                    tasks.addTodo(input);
                    break;
                case MARK:
                    tasks.markTask(input);
                    break;
                case UNMARK:
                    tasks.unmarkTask(input);
                    break;
                case DELETE:
                    tasks.deleteTask(input);
                    break;
                case FIND:
                    tasks.findTask(input);
                    break;
                default:
                    Printer.printWithDecorations(Advisor.advise(input));
                }
            } catch (Exception e) {
                Printer.printError(e.getMessage());
            } 

            try {
                input = sc.nextLine();
            } catch (Exception e) {
                input = "";
            }
        }

        try {
            Storage.saveTasks(tasks.getList());
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }

        sc.close();
        Printer.printBye();
    }
}