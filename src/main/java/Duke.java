import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String horizontalLine = "************************";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(horizontalLine);
        System.out.println("HELLO! I'M DUKE!");
        System.out.println("HOW CAN I HELP?");
        System.out.println(horizontalLine);
        echo();
    }

    private static void echo() {
        String input = scanner.nextLine();

        // If user inputs bye to close program
        if (input.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println("BYE!");
            System.out.println(horizontalLine);
        } else {
            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
            echo();
        }
    }

}
