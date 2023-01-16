import model.Task;
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
                    } else {
                        Task task = Task.create(input);
                        Printer.printIndent("[new] " + task);
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
