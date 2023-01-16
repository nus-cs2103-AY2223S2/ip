import model.Task;
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

    public static void main(String[] args) {
        Printer.println("Welcome to\n" + LOGO);
        Printer.println("How may I assist you today?");
        Scanner scanner = new Scanner(System.in);

        loop: while (scanner.hasNext()) {
            String input = scanner.nextLine();
            switch (input) {
                case TERMINATE_KEY:
                    Printer.printlnIndent("Have a good day! Good bye!");
                    break loop;
                case LIST_KEY:
                    Printer.printIndent("Here are all your tasks:");
                    Printer.listPrint(Task.listAll());
                    break;
                default:
                    Task task = Task.create(input);
                    Printer.printlnAdded(task.toString());
                    break;
            }
        }
    }
}
