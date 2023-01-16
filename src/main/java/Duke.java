import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner (System.in);
        String command = "";
        String[] taskList = new String[100];
        int taskCount = 0;
        while (!command.equals("bye")) {
            command = sc.nextLine();
            if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(String.format("%d. %s", i + 1, taskList[i]));
                }
            } else if (command.equals("blah")) {
                System.out.println(command);
            } else if (command.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                taskList[taskCount] = command;
                System.out.println(String.format("added: %s", command));
                taskCount++;
            }
        }
    }
}
