import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> listOfCommands = new ArrayList<String>();
    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }
    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm C-3PO, Human Cyborg Relations.\n\tWhat can I do for you?");
        printLine();
    }
    public static void exit() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }
    public static void saveCommand(String command) {
        listOfCommands.add(command);
        printLine();
        System.out.println("\tadded: " + command);
        printLine();
    }
    public static void listCommands() {
        printLine();
        for (int i = 1; i <= listOfCommands.size(); i++) {
            System.out.println("\t" + i + ". " + listOfCommands.get(i-1));
        }
        printLine();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        greet();
        while (true) {
            String command = input.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listCommands();
            } else {
                saveCommand(command);
            }
        }
        input.close();
    }
}
