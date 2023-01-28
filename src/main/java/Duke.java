import java.awt.image.AreaAveragingScaleFilter;
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

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            instruction = sc.nextLine();
            instrSplit = instruction.split(" ");
            command = instrSplit[0];
            if (command.compareTo("bye") == 0) {
                System.out.println(line + indent + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (command.compareTo("list") == 0) {
                System.out.print(line);
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(indent + i + "." + taskList.get(i-1));
                }
                System.out.println(line);
            } else if (command.compareTo("mark") == 0) {
                int index = Integer.parseInt(instrSplit[1]);
                task = taskList.get(index - 1);
                task.setDone();
                System.out.println(line + indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  " + task + "\n" + line);
            } else if (command.compareTo("unmark") == 0) {
                int index = Integer.parseInt(instrSplit[1]);
                task = taskList.get(index - 1);
                task.setUndone();
                System.out.println(line + indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + "  " + task + "\n" + line);
            } else {
                System.out.print(line);
                System.out.println(indent + "Got it. I've added this task:");
                if (command.compareTo("todo") == 0) {
                    Todo todo = new Todo(instruction.substring(5));
                    taskList.add(todo);
                    System.out.println(indent + "  " + todo);
                } else if (command.compareTo("deadline") == 0) {
                    Deadline deadline = new Deadline(instruction.substring(9));
                    taskList.add(deadline);
                    System.out.println(indent + "  " + deadline);
                } else if (command.compareTo("event") == 0) {
                    Event event = new Event(instruction.substring(6));
                    taskList.add(event);
                    System.out.println(indent + "  " + event);
                } else {
                    System.out.println(indent + line + "Cancel last, invalid command" + line);
                }
                String s = taskList.size() > 1 ? "tasks" : "task";
                System.out.println(indent + "Now you have " + taskList.size() + " " + s + " in the list.");
                System.out.println(line);
            }

//            task = new Task(instruction);
//            taskList.add(task);
//            System.out.println(line + indent + "added: " + task.getDescription() + "\n" + line);
        }
    }
}
