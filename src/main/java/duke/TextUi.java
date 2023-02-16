package duke;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This is a TextUi that contains methods read and write
 * on Duke.
 */
class TextUi {

    public final Scanner in;

    public TextUi() {
        this(System.in);
    }

    public TextUi(InputStream in) {
        this.in = new Scanner(in);
    }

    public String getGoodbyeMessage() {
        return "Bye. Go do your task now!";
    }

    public String getTaskAddedMessage(Task task, int sizeOfList) {
        return String.format("Noted. I've added this task:\n%s\nNow you have %s task%s in the list. :<",
                task,
                sizeOfList,
                sizeOfList > 1 ? "s" : "");
    }

    public String getTaskRemovedMessage(Task task, int sizeOfList) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %s task%s in the list. :>",
                task,
                sizeOfList,
                sizeOfList > 1 ? "s" : "");
    }

}
