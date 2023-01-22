import java.util.Scanner;

public class Duke {
    public static String projName = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    public boolean readInput(String input) {
        if (input.equals("bye")) {
            System.out.println("It was a pleasure to help, goodbye!");
            return false;
        } else {
            System.out.println(input);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("Yo! The name is\n" + projName);
        System.out.println("How might I help you today?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        boolean cont = true;

        while (cont) {
            System.out.println("\nQuery:");
            String input = scanner.nextLine();
            cont = duke.readInput(input);
        }
    }
}
