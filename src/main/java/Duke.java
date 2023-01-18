import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /**
     * The name of the chatbot
     */
    public String name = "Duke";

    /**
     * Storage of user's inputed texts
     */
    private final String[] textStorage = new String[100];

    /**
     * Number of text stored by the chatbot
     */
    private int textStorageSize = 0;

    /**
     * This is the main method which starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello. This is " + duke.name);
        Scanner scanner = new Scanner(System.in);
        final String endWord = "bye";
        boolean end = false;

        while (!end) {
            String input = scanner.nextLine();
            end = duke.processInput(input);
        }
    }

    /**
     * This method processes the user's input and outputs
     * the relevant reply by the chatbot
     *
     * @param text the user's input.
     * @return a boolean based on if the conversation has ended.
     */
    public boolean processInput(String text) {
        switch (text) {
            case "bye":
                endChat();
                return true;
            case "list":
                displayTextStorage();
                break;
            default:
                storeText(text);
        }

        return false;
    }

    /**
     * This method is used to output a message from the chatbot
     * to the user.
     *
     * @param message the string the chatbot will reply.
     */
    private void reply(String message) {
        System.out.println(message);
    }

    /**
     * This method is used to send a message to signal the end
     * of the conversation.
     */
    private void endChat() {
        String endMessage = "Chat with Duke has ended";
        reply(endMessage);
    }

    /**
     * This method stores the string into the chatbot's
     * text storage
     *
     * @param text the string to be stored.
     */
    private void storeText(String text) {
        this.textStorage[this.textStorageSize] = text;
        this.textStorageSize++;
        reply("added: " + text);
    }

    /**
     * This method outputs the entire list of text stored
     * by the chatbot in order
     */
    private void displayTextStorage() {
        int size = this.textStorageSize;

        if (size == 0) {
            reply("No text stored.");
            return;
        }

        for (int i = 0; i < size; i++) {
            reply((i + 1) + ". " + this.textStorage[i]);
        }
    }
}
