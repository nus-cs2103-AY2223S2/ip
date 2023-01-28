import java.awt.image.AreaAveragingScaleFilter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String indent = "      ";

        System.out.println(line + indent + "Hello! I'm DUKE");
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
                if (command.compareTo("todo") == 0) {
                    if (instrSplit.length == 1) {
                        System.out.println(indent + "☹ OOPS!!! The description of a todo cannot be empty.\n" + line);
                        continue;
                    }
                    System.out.println(indent + "Got it. I've added this task:");
                    Todo todo = new Todo(instruction.substring(5));
                    taskList.add(todo);
                    System.out.println(indent + "  " + todo);
                } else if (command.compareTo("deadline") == 0) {
                    if (instrSplit.length == 1) {
                        System.out.println(indent + "☹ OOPS!!! The description of a deadline cannot be empty.\n" + line);
                        continue;
                    }
                    System.out.println(indent + "Got it. I've added this task:");
                    Deadline deadline = new Deadline(instruction.substring(9));
                    taskList.add(deadline);
                    System.out.println(indent + "  " + deadline);
                } else if (command.compareTo("event") == 0) {
                    if (instrSplit.length == 1) {
                        System.out.println(indent + "☹ OOPS!!! The description of an event cannot be empty.\n" + line);
                        continue;
                    }
                    System.out.println(indent + "Got it. I've added this task:");
                    Event event = new Event(instruction.substring(6));
                    taskList.add(event);
                    System.out.println(indent + "  " + event);
                } else {
                    System.out.println(indent + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
                    continue;
                }
                String s = taskList.size() > 1 ? "tasks" : "task";
                System.out.println(indent + "Now you have " + taskList.size() + " " + s + " in the list.");
                System.out.println(line);
            }
        }
    }
}
