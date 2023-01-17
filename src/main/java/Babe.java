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
     * A string input from user.
     */
    private String userInput = "";

    /**
     * List of strings Babe received from the user.
     */
    private String[] memory = new String[100];

    /**
     * Number of strings currently stored in this Babe.
     */
    private int memoryCount = 0;

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
    }

    /**
     * Receives user's input.
     * Receives input from the user and stores it in input field.
     */
    private void listen() {
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine().stripTrailing();
    }

    /**
     * Stores user's latest input into memory array of this Babe.
     * Adds userInput to memory and prints a message to indicate that the item has been stored.
     */
    private void addItem() {
        this.memory[memoryCount++] = userInput;
        Babe.drawLine();
        System.out.printf("added %s \n", userInput);
        Babe.drawLine();
    }

    /**
     * Prints list of strings stored in this Babe.
     * Prints a numbered list of strings stored in memory.
     */
    private void printList() {
        Babe.drawLine();
        if (memoryCount == 0) {
            System.out.println("Nothing added yet. Add something hon.");
        }
        for (int i = 0; i < this.memoryCount; i++) {
            System.out.printf("%d. %s\n", i + 1, this.memory[i]);
        }
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
            switch (chatBot.userInput.toLowerCase()) {
            case "bye":
                chatBot.sayBye();
                break;
            case "list":
                chatBot.printList();
                break;
            default:
                chatBot.addItem();
            }
        }

    }
}
