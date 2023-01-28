import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "    ______________________________________________________\n";
        String indent = "      ";
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(line + indent + "Hello! I'm\n" + logo);
        System.out.println(indent + "What can I do for you?\n" + line);

        Scanner sc = new Scanner(System.in);

        String instruction;
        String[] instrSplit;
        String completed;
        String command;
        String description;
        Task task;

        while (true) {
            instruction = sc.nextLine().toLowerCase();
            instrSplit = instruction.split(" ");
            command = instrSplit[0];
            if (command.compareTo("bye") == 0) {
                System.out.println(line + indent + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (command.compareTo("list") == 0) {
                System.out.print(line);
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 1; i <= Task.taskList.size(); i++) {
                    task = Task.getTask(i-1);
                    completed = task.getStatusIcon();
                    description = task.getDescription();
                    System.out.println(indent + i + "." + "[" + completed + "] " + description);
                }
                System.out.println(line);
            } else if (command.compareTo("mark") == 0) {
                int index = Integer.parseInt(instrSplit[1]);
                task = Task.getTask(index - 1);
                task.setDone();
                System.out.println(line + indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  " + "[X] " + task.getDescription() + "\n" + line);
            } else if (command.compareTo("unmark") == 0) {
                int index = Integer.parseInt(instrSplit[1]);
                task = Task.getTask(index - 1);
                task.setUndone();
                System.out.println(line + indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + "  " + "[ ] " + task.getDescription() + "\n" + line);
            } else {
                task = new Task(instruction);
                System.out.println(line + indent + "added: " + task.getDescription() + "\n" + line);
            }
        }
    }
}
