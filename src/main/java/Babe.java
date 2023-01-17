import java.util.Scanner;

/**
 * <h1> Hi Babe! </h1>
 * Babe is a chat bot created as part of the individual project (iP) under the course CS2109T.
 * It is a duplicate of Duke with some personal flair.
 *
 * @author Shan Hern Hng
 * @version 1.0
 * @since 17 January 2023
 */
public class Babe {

    /**
     * Draws a horizontal line.
     * Draws a line for cosmetic purposes.
     */
    private static void drawLine() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Welcome users of Babe.
     * Greets user and prompts for help. This method does not expect a response.
     */
    private void welcome() {
        Babe.drawLine();
        System.out.println("HELLO! Greetings from Babe.");
        System.out.println("How may I help you?");
        Babe.drawLine();
        return;
    }

    /**
     * Repeats users' input after them until termination.
     * Receives input from the user and prints the input. Terminates the chat once the user
     * keys in "bye" (case-insensitive).
     */
    private void listenAndRepeat() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().stripTrailing();

        if (userInput.toLowerCase().equals("bye")) {
            sayBye();

        } else {
            Babe.drawLine();
            System.out.println(userInput);
            Babe.drawLine();
        }
    }

    /**
     * Bids farewell to the user.
     * Prints a line of farewell before ending the program.
     */
    private void sayBye() {
        Babe.drawLine();
        System.out.println("Bye, babyboo. Can't wait to meet you again!");
        Babe.drawLine();
        System.exit(0);
    }

    public static void main(String[] args) {

        Babe chatBot = new Babe();
        chatBot.welcome();

        while (true) {
            chatBot.listenAndRepeat();
        }

    }
}
