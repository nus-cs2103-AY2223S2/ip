import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        DukeMessage initMessage = new DukeMessage("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(initMessage);

        while (!end) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();
            if (userMessage.equals("bye")) {
                end = true;
            }
            DukeMessage dukeResponse = new DukeMessage(userMessage);
            System.out.println(dukeResponse);
        }


    }
}
