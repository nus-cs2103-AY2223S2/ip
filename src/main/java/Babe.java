import java.util.Locale;
import java.util.Objects;
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
     * String icon for marked.
     */
    private String MARKED = "[X]";

    /**
     * String icon for unmarked.
     */
    private String UNMARKED = "[ ]";

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
     * Boolean array that keeps track of marked item on the list.
     */
    private boolean[] doneStatus = new boolean[100];

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
     * Prints a numbered list of strings stored in memory. Item will be checked accordingly to its corresponding
     * status stored in doneStatus.
     */
    private void printList() {
        Babe.drawLine();
        if (memoryCount == 0) {
            System.out.println("Nothing added yet. Add something hon.");
        }
        for (int i = 0; i < this.memoryCount; i++) {
            System.out.printf("%d." + (this.doneStatus[i] ? MARKED : UNMARKED) + " %s\n",
                    i + 1,
                    this.memory[i]);
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

    /**
     * Checks if the user decides to change status for any item.
     * Checks whether mark or unmark action is requested by user. Mark/Unmark will result in return of True/False.
     * Null is returned if no such action is detected.
     *
     * @return True if "mark", False for "unmark", null otherwise.
     */
    private Boolean checkChangeStatus() {
        Boolean toMark = null;
        if (userInput.substring(0, 4).equalsIgnoreCase("mark")) {
            toMark = true;
        } else if (userInput.length() >= 6
                && userInput.substring(0, 6).equalsIgnoreCase("unmark")) {
            toMark = false;
        }
        return toMark;
    }

    /**
     * Marks/Unmarks the item of given index in Babe's list as Done/Undone.
     * If user keys in "mark", this function will extract the index to be marked and sets the index to True in
     * doneStatus. Sets the index to False if "unmark"is keyed in.
     */
    private void changeStatus(boolean toMark) {
            userInput = toMark ? userInput.substring(5) : userInput.substring(7);
            int index = Integer.parseInt(userInput);
            this.doneStatus[index - 1] = toMark;
            Babe.drawLine();
            System.out.println(toMark ? "Okay, babygorl. I've marked this as Done:" : "We have un-Done this for you:");
            System.out.printf((toMark ? this.MARKED : this.UNMARKED) + " %s\n", this.memory[index - 1]);
            Babe.drawLine();
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
                if (chatBot.userInput.length() >= 4) {
                    Boolean toMark = chatBot.checkChangeStatus();
                    if (!Objects.isNull(toMark)) {
                        chatBot.changeStatus(toMark);
                    } else {
                        chatBot.addItem();
                    }
                } else {
                    chatBot.addItem();


                }
            }
        }

    }
}
