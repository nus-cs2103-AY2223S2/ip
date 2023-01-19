import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?\n");
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }

            } else if (input.split(" ")[0].equals("mark")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list.get(num - 1).mark();
                    System.out.println("Nice! I've marked this task as done: \n" + list.get(num-1));

            } else if (input.split(" ")[0].equals("unmark")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list.get(num - 1).unmark();
                    System.out.println("OK, I've marked this task as not done yet: \n" + list.get(num-1));
           } else {
                Task newTask;
                String[] inputs = input.split(" ");
                String type = inputs[0];
                if (type.equals("todo")) {
                    String name = input.split(" ", 2)[1];
                    newTask = new ToDo(name);
                } else if (type.equals("deadline")) {
                    String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
                    String name = nameAndDeadline[0];
                    String deadline = nameAndDeadline[1];
                    newTask = new Deadline(name, deadline);
                } else {
                    String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
                    String name = nameAndStart[0];
                    String[] startAndEnd = nameAndStart[1].split(" /to ");
                    String start = startAndEnd[0];
                    String end = startAndEnd[1];
                    newTask = new Event(name, start, end);
                }
                list.add(newTask);
                System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have " + list.size() + " tasks in the list.");
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

    }



}
