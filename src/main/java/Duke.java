import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\nL"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("    Hello I'm Monkey D Luffy\n    What can I do for you?");

        ArrayList<Task> Tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < Tasks.size(); i++) {
                    int num = i + 1;
                    System.out.print("    " + num + ". " + Tasks.get(i) + "\n");
                }
            } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                int index_by = input.indexOf("/");
                Deadline d = new Deadline(input.substring(9, index_by-1),
                                        input.substring(index_by+4, input.length()));
                Tasks.add(d);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + d);
                System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");
            } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                int index_from = input.indexOf("/");
                int index_to = input.lastIndexOf("/");
                Event e = new Event(input.substring(6, index_from-1),
                                    input.substring(index_from+6, index_to-1),
                                    input.substring(index_to+4, input.length()));
                Tasks.add(e);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + e);
                System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");
            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                Task task = Tasks.get(index - 1);
                task.unmark();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + task);
            } else if (input.length() >= 4) {
                if (input.substring(0, 4).equals("mark")) {
                    int index = Integer.parseInt(input.substring(5));
                    Task task = Tasks.get(index - 1);
                    task.mark();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + task);
                }
                if (input.substring(0, 4).equals("todo")) {
                    Todo td = new Todo(input.substring(5, input.length()));
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + td);
                    Tasks.add(td);
                    System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");
                }
            }
//            } else {
//                Task task = new Task(input);
//                Tasks.add(task);
//                System.out.println("    added: " + task);
//            }
            input = sc.nextLine();
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }
}
