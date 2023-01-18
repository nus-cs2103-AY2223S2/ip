import java.util.Objects;
import java.util.Scanner;

public class Duke {
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

    public static void reply(String message) {
        System.out.println(message);
    }
}
