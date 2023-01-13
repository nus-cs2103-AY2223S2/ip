import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "What can I do for you?\n";
        System.out.println(greeting);

        List<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        convo: while (true) {
            System.out.print("You : ");
            String[] input = sc.nextLine().toLowerCase().split(" ");
            int idx = 0;
            switch (input[0]) {
                case "bye":
                    System.out.println("Duke: Bye. Hope to see you again soon!");
                    break convo;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                    }
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    idx = Integer.parseInt(input[1]);
                    list.get(idx - 1).markAsDone();
                    System.out.println(list.get(idx - 1));
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    idx = Integer.parseInt(input[1]);
                    list.get(idx - 1).unmarkDone();
                    System.out.println(list.get(idx - 1));
                    break;
                case "":
                    break;
                default:
                    System.out.println("Duke: added " + input[0]);
                    list.add(new Task(input[0]));
                    break;
            }
        }

        sc.close();
    }
}
