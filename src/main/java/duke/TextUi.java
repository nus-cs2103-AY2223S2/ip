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

    public String getWelcomeMessage() {
        return "Hello! I'm Grand Duke.\nWhat can I do for you?";
    }

    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String getTaskAddedMessage(Task task, int sizeOfList) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %s task%s in the list.",
                task,
                sizeOfList,
                sizeOfList > 1 ? "s" : "");
    }

    public String getTaskRemovedMessage(Task task, int sizeOfList) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %s task%s in the list.",
                task,
                sizeOfList,
                sizeOfList > 1 ? "s" : "");
    }

}
