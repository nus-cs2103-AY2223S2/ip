package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This is a TextUi that contains methods read and write
 * on Duke.
 */
class TextUi {

    public final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void getWelcomeMessage() {
        out.println("Hello! I'm duke.Duke.");
        out.println("What can I do for you?");
    }

    public void getGoodbyeMessage() {
        out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        out.println("____________________");
    }

    public void getCustomMessage(String message) {
        out.println("____________________");
        out.println(message);
        out.println("____________________");
    }

    public void getTaskAddedMessage(Task task, int sizeOfList) {
        out.println("____________________");
        out.println("Got it. I've added this task:");
        out.println(task);
        out.println("Now you have " + sizeOfList
                + " task" + (sizeOfList > 1 ? "s" : "") + " in the list.");
        out.println("____________________");
    }

    public void getTaskRemovedMessage(Task task, int sizeOfList) {
        out.println("____________________");
        out.println("Noted. I've removed this task:");
        out.println(task);
        out.println("Now you have " + sizeOfList
                + " task" + (sizeOfList > 1 ? "s" : "") + " in the list.");
        out.println("____________________");
    }

}
