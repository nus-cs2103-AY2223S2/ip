import java.util.Scanner;

public class Duke {
    final static String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String WELCOME_MSG = "Greetings!\n" + LOGO + "this is\n" + "For you, What can I do?";
    final static String BANNER = "____________________________________________________________";
    final static String BYE_MSG = "Be Gone, You Must. May the Force be with You!";
    public static void main(String[] args) {
        Duke.displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String response = "";

        while (!response.equals("bye")) {
            response = scanner.nextLine();
            Duke.respond(response, true);
        }
        Duke.respond(BYE_MSG, false);
    }

    /**
     * Displays the welcome message when launched.
     */
    public static void displayWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s\n", BANNER, WELCOME_MSG, BANNER));
    }

    /**
     * Responds to the command given by standard input with the appropriate formatting.
     * @param command the input that is retrieved from standard input
     * @param nextLine provides necessary indent should requested
     */
    public static void respond(String command, boolean nextLine) {
        String answer = "";
        if (nextLine) {
            answer += BANNER;
        }
        answer += "\n" + command + "\n" + BANNER;
        System.out.println(answer);
    }
}
