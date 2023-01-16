import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println(command);
            } else if (command.equals("blah")) {
                System.out.println(command);
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
        }
    }
}
