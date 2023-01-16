import model.Deadline;
import model.Event;
import model.Task;
import model.ToDo;
import utils.InputValidator;
import utils.Printer;
import java.util.Scanner;

public class Membot {
    private static final String LOGO =
              "                             _             _   \n"
            + " _ __ ___    ___  _ __ ___  | |__    ___  | |_ \n"
            + "| '_ ` _ \\  / _ \\| '_ ` _ \\ | '_ \\  / _ \\ | __|\n"
            + "| | | | | ||  __/| | | | | || |_) || (_) || |_ \n"
            + "|_| |_| |_| \\___||_| |_| |_||_.__/  \\___/  \\__|\n";
    private static final String TERMINATE_KEY = "bye";
    private static final String LIST_KEY = "list";
    private static final String CHECK_KEY = "done";
    private static final String UNCHECK_KEY = "undone";
    private static final String TODO_KEY = "todo";
    private static final String DEADLINE_KEY = "deadline";
    private static final String EVENT_KEY = "event";

    public static void main(String[] args) {
        Printer.println("Welcome to\n" + LOGO);
        Printer.println("How may I assist you today?");
        Scanner scanner = new Scanner(System.in);

        loop: while (scanner.hasNext()) {
            String input = scanner.nextLine();
            switch (input.trim()) {
                case TERMINATE_KEY:
                    Printer.printlnIndent("Have a good day! Good bye!");
                    break loop;
                case LIST_KEY:
                    Printer.printIndent("Here are all your tasks:");
                    Printer.listPrint(Task.listAll());
                    break;
                case CHECK_KEY:
                    Printer.printIndent("Which task would you like to mark as completed?");
                    Printer.listPrint(Task.listAll());
                    break;
                default:
                    if (input.startsWith(CHECK_KEY) && InputValidator.isCheckInputValid(input)) {
                        int taskId = Integer.parseInt(input.split(" ")[1]);
                        try {
                            Task.setStatusCompleted(taskId);
                            Printer.printIndent("Well done! The task is marked as completed:");
                            Printer.printIndent(Task.listOne(taskId));
                        } catch (IndexOutOfBoundsException e) {
                            Printer.printlnIndent("Invalid Task ID!");
                            break;
                        }
                    } else if (input.startsWith(UNCHECK_KEY)
                            && InputValidator.isCheckInputValid(input)) {
                        int taskId = Integer.parseInt(input.split(" ")[1]);
                        try {
                            Task.setStatusNew(taskId);
                            Printer.printIndent("The task is now marked as not done yet:");
                            Printer.printIndent(Task.listOne(taskId));
                        } catch (IndexOutOfBoundsException e) {
                            Printer.printlnIndent("Invalid Task ID!");
                            break;
                        }
                    } else if (input.startsWith(TODO_KEY)
                            && InputValidator.isTaskInputValid(input)) {
                        String title = input.substring(5);
                        ToDo task = new ToDo(title);
                        Printer.printIndent(task.toString());
                    } else if (input.startsWith(DEADLINE_KEY)
                            && InputValidator.isTaskInputValid(input)) {
                        int deadlineStartIndex = input.indexOf("/by ");
                        String title = input.substring(9, deadlineStartIndex - 1);
                        String deadline = input.substring(deadlineStartIndex + 4);

                        Deadline task = new Deadline(title, deadline);
                        Printer.printIndent(task.toString());
                    } else if (input.startsWith(EVENT_KEY)
                            && InputValidator.isTaskInputValid(input)) {
                        int startIndex = input.indexOf("/from ");
                        int endIndex = input.indexOf("/to ");
                        String title = input.substring(6, startIndex - 1);
                        String start = input.substring(startIndex + 6, endIndex - 1);
                        String end = input.substring(endIndex + 4);

                        Event task = new Event(title, start, end);
                        Printer.printIndent(task.toString());
                    } else {
                        ToDo task = new ToDo(input);
                        Printer.printIndent(task.toString());
                    }

                    Printer.printIndent("");
                    Printer.printIndent("Here are your updated tasks:");
                    Printer.listPrint(Task.listAll());
                    break;
            }
        }

        scanner.close();
    }
}
