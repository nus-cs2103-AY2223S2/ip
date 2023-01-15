import java.util.Scanner;

public class Book {
    /** Logo for Book */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <  \n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Horizontal line for separation. */
    private static final String LINE =
            "________________________________________________________________________________\n";

    private static final Scanner input = new Scanner(System.in);
    private static String command = "";
    public static void main(String[] args) {
        System.out.println(LINE + "Good day! This is\n" + LOGO + "What may I help you with?\n" + LINE);
        while (!command.equals("bye")) {
            command = input.nextLine();
            System.out.println(LINE + command + "\n" + LINE);
        }
        System.out.println("Bye. Pick up Book again soon!\n" + LINE);
    }
}
