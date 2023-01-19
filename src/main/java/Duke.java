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
                Task newTask = new Task(input);
                list.add(newTask);
                System.out.println("added: " + newTask);
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

    }



}
