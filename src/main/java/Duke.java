import java.util.Scanner;  // Import the Scanner class

public class Duke {

    private static Scanner getInput = new Scanner(System.in); // Create a static Scanner object

    public static void main(String[] args) {
        System.out.println(intro());
        String s = askForInput();
        while(!s.equals("bye")) {
            System.out.println("\n" + s + "\n");
            s = askForInput();
        }
        System.out.println("Good Riddance!");
    }

    private static String logo() {
        return " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    }

    private static String intro() {
        return "Hello! I'm\n" + logo() + "\nWhat can I do for you?";
    }

    private static String askForInput() {
        System.out.print("> ");
        return getInput.nextLine();
    }
}
