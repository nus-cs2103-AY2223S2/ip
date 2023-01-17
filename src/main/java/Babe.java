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

    /** A string Babe received from the user. */
    private String memory = "";

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
     * Receives user's input.
     * Receives input from the user and stores it in memory.
     */
    private void listen() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().stripTrailing();
        memory = userInput;
    }

    /**
     * Prints string previously received from user.
     * Prints the string stored in memory.
     */
    private void print() {
        Babe.drawLine();
        System.out.println(memory);
        Babe.drawLine();
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
            chatBot.listen();
            if (chatBot.memory.toLowerCase().equals("bye")) {
                chatBot.sayBye();
            } else {
                chatBot.print();
            }
        }

    }
}
