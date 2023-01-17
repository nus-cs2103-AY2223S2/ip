import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Init
        displayMessage("Hello! I'm Duke\nWhat can i do for you?");
        Scanner sc = new Scanner(System.in);

        // Application main loop
        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) break;

            displayMessage(input);
        }

        displayMessage("Bye! Hope to see you again soon!");
    }


    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg + wrapBottom);
    }
}
