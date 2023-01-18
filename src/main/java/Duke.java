import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String reply = "";

        printTextWithLines("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            reply = input.nextLine();
            if (reply.equals("bye")) {
                printTextWithLines("Bye. Hope to see you again soon!");
                break;
            }
            printTextWithLines(reply);
        }

    }

    static void printTextWithLines(String text) {
        printLineBreak();
        System.out.println(text);
        printLineBreak();
        System.out.println();
    }
    static void printLineBreak() {
        System.out.println("____________________________________________________________");
    }
}
