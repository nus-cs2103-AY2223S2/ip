import java.util.*;

public class Duke {
    public static void main(String[] args) {

        // Print intro
        String intro = "Hello! I'm Duke\n What can I do for you?";
        System.out.println(intro);

        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        TaskList tl = new TaskList();

        // Always ready to receive input
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    System.out.println(tl.toString());
                    break;
                default:
                    tl.addTask(input);
                    System.out.println("added: " + input);
            }
        }
    }
}