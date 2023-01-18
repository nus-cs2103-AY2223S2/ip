import java.util.Objects;
import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /**
     * This is the main method which starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        System.out.println("Hello. This is Duke.");
        Scanner scanner = new Scanner(System.in);
        String endMessage = "Chat with Duke has ended";

        while (true) {
            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                reply(endMessage);
                break;
            }

            reply(input);
        }
    }

    /**
     * This method is used to output the reply from the chatbot
     * to the user.
     *
     * @param message the string the chatbot will reply.
     */
    public static void reply(String message) {
        System.out.println(message);
    }
}
