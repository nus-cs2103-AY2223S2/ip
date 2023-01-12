import java.util.Scanner;

public class Duke {
    public static void returnFormattedResponse(String response) {
        final int INDENTS = 4;

        String lineAddedResponse = "____________________________________________________________\n"
                + " " + response.replace("\n", "\n" + " ")
                + "\n____________________________________________________________\n";
        String indentedResponse = " ".repeat(INDENTS)
                + lineAddedResponse.replace("\n", "\n" + " ".repeat(INDENTS));

        System.out.println(indentedResponse);;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String INTRODUCTION_MESSAGE = "Hello! I'm Duke\n"
                + "What can I do for you?";
        final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

        System.out.println(logo);
        returnFormattedResponse(INTRODUCTION_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                break;
            }
        }

        returnFormattedResponse(EXITING_MESSAGE);
    }
}
