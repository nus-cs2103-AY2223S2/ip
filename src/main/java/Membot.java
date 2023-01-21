import model.Deadline;
import model.Event;
import model.Task;
import model.ToDo;
import utils.*;

import java.time.DateTimeException;
import java.util.Scanner;

public class Membot {
    private static final String LOGO =
              "                             _             _   \n"
            + " _ __ ___    ___  _ __ ___  | |__    ___  | |_ \n"
            + "| '_ ` _ \\  / _ \\| '_ ` _ \\ | '_ \\  / _ \\ | __|\n"
            + "| | | | | ||  __/| | | | | || |_) || (_) || |_ \n"
            + "|_| |_| |_| \\___||_| |_| |_||_.__/  \\___/  \\__|\n";

    public static void main(String[] args) {
        Printer.println("Welcome to\n" + LOGO);
        Printer.println("How may I assist you today?");
        Scanner scanner = new Scanner(System.in);

        loop: while (scanner.hasNext()) {
            String input = scanner.nextLine().trim();
            Command command = Command.UNDEFINED;

            try {
                command = InputValidator.extractCommandFromInput(input);
            } catch (EmptyInputException | InvalidCommandException e) {
                Printer.printlnError("Sorry I do not understand what to do!");
            }

            switch (command) {
                case BYE:
                    Printer.printlnIndent("Have a good day! Good bye!");
                    break loop;
                case LIST:
                    printTasks();
                    break;
                case DONE:
                    if (InputValidator.isCheckInputValid(input)) {
                        int taskId = Integer.parseInt(input.split(" ")[1]);
                        try {
                            Task.setStatusCompleted(taskId);
                            Printer.printIndent("Well done! The task is marked as completed:");
                            Printer.printIndent(Task.listOne(taskId));

                            Printer.printIndent("");
                            printTasks();
                        } catch (IndexOutOfBoundsException e) {
                            Printer.printlnError("Invalid Task ID!");
                        }
                    } else {
                        Printer.printlnError("Invalid Syntax - \"done [Task ID]\" (e.g. \"done 4\")");
                    }

                    break;
                case UNDONE:
                    if (InputValidator.isCheckInputValid(input)) {
                        int taskId = Integer.parseInt(input.split(" ")[1]);
                        try {
                            Task.setStatusNew(taskId);
                            Printer.printIndent("The task is now marked as not done yet:");
                            Printer.printIndent(Task.listOne(taskId));

                            Printer.printIndent("");
                            printTasks();
                        } catch (IndexOutOfBoundsException e) {
                            Printer.printlnError("Invalid Task ID!");
                        }
                    } else {
                        Printer.printlnError("Invalid Syntax - \"undone [Task ID]\" (e.g. \"undone 3\")");
                    }

                    break;
                case DELETE:
                    if (InputValidator.isCheckInputValid(input)) {
                        int taskId = Integer.parseInt(input.split(" ")[1]);
                        try {
                            Task deletedTask = Task.delete(taskId);
                            Printer.printIndent("Deleted! The task has been deleted:");
                            Printer.printIndent(deletedTask.toString());

                            Printer.printIndent("");
                            printTasks();
                        } catch (IndexOutOfBoundsException e) {
                            Printer.printlnError("Invalid Task ID!");
                        }
                    } else {
                        Printer.printlnError("Invalid Syntax - \"delete [Task ID]\" (e.g. \"delete 1\")");
                    }

                    break;
                case TODO:
                    try {
                        String[] normalised = InputValidator.normaliseTodoInput(input);
                        ToDo task = new ToDo(normalised[1]);
                        Printer.printIndent(task.toString());

                        Printer.printIndent("");
                        printTasks();
                    } catch (IndexOutOfBoundsException | InvalidCommandException e) {
                        Printer.printlnError("Invalid Syntax - \"todo [title]\" (e.g. \"todo math homework\")");
                    }

                    break;
                case DEADLINE:
                    try {
                        String[] normalised = InputValidator.normaliseDeadlineInput(input);
                        Deadline task = new Deadline(normalised[1], normalised[2]);
                        Printer.printIndent(task.toString());

                        Printer.printIndent("");
                        printTasks();
                    } catch (IndexOutOfBoundsException | NoDeadlineFoundException | InvalidCommandException e) {
                        Printer.printlnError("Invalid Syntax - \"deadline [title] /by [deadline]\"" +
                                "(e.g. \"deadline physics project /by tomorrow 3pm\")");
                    }

                    break;
                case EVENT:
                    try {
                        String[] normalised = InputValidator.normaliseEventInput(input);
                        Event task = new Event(normalised[1], normalised[2], normalised[3]);
                        Printer.printIndent(task.toString());

                        Printer.printIndent("");
                        printTasks();
                    } catch (IndexOutOfBoundsException | InvalidCommandException | NoStartDateTimeFoundException |
                             NoEndDateTimeFoundException e) {
                        Printer.printlnError("Invalid Syntax - \"event [title] /from [start] /to [end]\"" +
                                "(e.g. \"event piano concert /from tomorrow 3pm /to tomorrow 6pm\")");
                    } catch (DateTimeException e) {
                        Task.deleteLast();
                        Printer.printlnError(e.getMessage());
                    }

                    break;
            }
        }

        scanner.close();
    }

    private static void printTasks() {
        String[] tasks = Task.listAll();
        if (tasks.length == 0) {
            Printer.printlnIndent("Excellent! You do not have any tasks at hand!");
        } else {
            Printer.printIndent("Here are your updated tasks:");
            Printer.listPrint(Task.listAll());
        }
    }
}
