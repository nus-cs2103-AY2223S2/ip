import java.util.ArrayList;

/**
 * The Feedback class implements the responses given by a chatbot.
 *
 * @author Chia Jeremy
 */

public class Feedback {

    private static final String INDENT = "    ";
    private static final String LINE = INDENT + "______________________________";

    private static void display(String message) {
        System.out.println(LINE);
        String[] lines = message.split("\n");
        for (String s : lines) {
            System.out.println(INDENT + s);
        }
        System.out.println(LINE + "\n");
    }

    public static void greet(String logo) {
        display("Hello I'm\n" + logo + "What can I do for you?\n\n" + commands());
    }

    public void addedTask(Task task, int size) {
        display("Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    public void deletedTask(Task task, int size) {
        display("Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.");
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

    public void help() {
        display(commands());
    }

    public static void exit() {
        display("Bye. Hope to see you again soon!");
    }

    public void invalid() {
        display("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void warning(String errorMessage) {
        display(errorMessage);
    }

    private static String commands() {
        return "MY COMMANDS ARE:\n\n"
                + "ADD TODO TASK:     todo [description]\n"
                + "ADD DEADLINE TASK: deadline [description] /by [date time]\n"
                + "ADD EVENT TASK:    event [description] /from [date time] /to [date time]\n"
                + "MARK A TASK:       mark [index]\n"
                + "UNMARK A TASK:     unmark [index]\n"
                + "DELETE A TASK:     delete [index]\n"
                + "LIST ALL TASKS:    list\n"
                + "DISPLAY COMMANDS:  help\n"
                + "EXIT PROGRAM:      bye";
    }
}
