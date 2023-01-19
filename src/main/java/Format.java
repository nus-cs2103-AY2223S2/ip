import java.util.ArrayList;

public class Format {
    // Create lines
    public static void line() {
        System.out.println("====================================");
    }

    // Create indents
    public static void indent(String output) {
        System.out.println("    " + output);
    }

    // Check for grammar task(s)
    public static void checkList(ArrayList<Task> taskList) {
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else if (taskList.size() == 0) {
            System.out.println("You have no tasks in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        line();
    }
}
