import java.util.Scanner;

/**
 * Project Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying as many
 * Java and SE techniques as possible along the way.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        //echoes user commands entered by user, unless command is "bye"
        while (true) {
            boolean dontEnd = takeCommand();
            if (!dontEnd) {
                break;
            }
        }

        bidFarewell();
    }

    /**
     * Waits for user input and calls makeResponse() if the input is not 'bye'. Else, exits
     * the program.
     *
     * @return true if user input was not 'bye'. false otherwise.
     */
    public static boolean takeCommand() {
        Scanner inputTaker = new Scanner(System.in);
        String userInput = inputTaker.nextLine();
        makeSeperation();

        if (userInput.equals("bye")) {
            return false;
        }

        makeResponse(userInput);
        return true;
    }

    /**
     * Prints out the resposne to the user input, command.
     *
     * @param command The user input received.
     */
    public static void makeResponse(String command) {
        System.out.println(command);
        makeSeperation();
    }

    /**
     * Prints out the greeting message and a line separation.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        makeSeperation();
    }

    /**
     * Prints out the goodbye message and a line separation.
     */
    public static void bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
        makeSeperation();
    }

    /**
     * Prints out a line separation.
     */
    public static void makeSeperation() {
        System.out.println("____________________________________________________________");
    }
}
