import java.util.ArrayList;

/**
 * The Feedback class implements the responses given by a chatbot.
 *
 * @author Chia Jeremy
 */

public class Feedback {

    private static final String INDENT = "    ";
    private static final String LINE = "    ______________________________";

    private void display(String message) {
        System.out.println(LINE);
        String[] lines = message.split("\n");
        for (String s : lines) {
            System.out.println(INDENT + s);
        }
        System.out.println(LINE + "\n");
    }

    public void greet(String logo) {
        display("Hello I'm\n" + logo + "What can I do for you?");
    }

    public void exit() {
        display("Bye. Hope to see you again soon!");
    }

    public void addedTask(int index, Task task) {
        display("Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + index + " tasks in the list.");
    }

    public void markedTask(Task task) {
        display("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void unmarkedTask(Task task) {
        display("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public void listTask(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String s = (i + 1) + ". " + tasks.get(i) + "\n";
            sb.append(s);
        }
        display(sb.toString());
    }
}
