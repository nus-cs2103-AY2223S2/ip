import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "____________________________________________________\n";

    private static void displayIntro() {
        System.out.println(reply("Hello! I'm Duke\n What can I do for you?"));
    }

    private static void displayOutro() {
        System.out.println(reply("Bye. Hope to see you again soon!"));
    }

    private static String reply(String command) {
        return DIVIDER_LINE + command + "\n" + DIVIDER_LINE;
    }

    public static void main(String[] args) {
        displayIntro();

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        while (!command.equals("bye")) {
            String reply = reply(command);
            System.out.println(reply);
            command = input.nextLine();
        }

        displayOutro();
    }
}
