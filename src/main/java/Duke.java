import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String dialogSeparator = "____________________________________________________________";

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String dialogSeparator = "____________________________________________________________";
        String greeting = logo + "\n" + dialogSeparator + "\nHello! I'm Duke\nWhat can I do for you?\n" + dialogSeparator;
        System.out.println(greeting);

    }

    public static void main(String[] args) {
        greeting();
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(dialogSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + dialogSeparator);
                break;
            } else if (input.equals("list")) {
                int i = 1;
                System.out.println(dialogSeparator);
                System.out.println("Here are the tasks in your list:");
                for (Task item : tasks) {
                    System.out.println(i + "." + item);
                    i++;
                }
                System.out.println(dialogSeparator);
            } else if (input.split(" ")[0].equals("mark")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                Task t = tasks.get(i - 1);
                t.markAsDone();
                System.out.println(dialogSeparator);

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
                System.out.println(dialogSeparator);

            } else if (input.split(" ")[0].equals("unmark")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                Task t = tasks.get(i - 1);
                t.markAsUnDone();
                System.out.println(dialogSeparator);

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t);
                System.out.println(dialogSeparator);

            }else {
                Task t;
                switch (input.split(" ")[0]) {
                    case "deadline":
                        String cmd = input.substring(input.indexOf("deadline") +8);
                        String[] temp = cmd.split("/by");
                        t = new Deadline(temp[0],temp[1] );
                        tasks.add(t);
                        break;
                    case "event":
                        cmd = input.substring(input.indexOf("event") + 5);
                        temp = cmd.split("/from ");
                        String[] temp2 = temp[1].split("/to");
                        t = new Event(temp[0],temp2[0],temp2[1]);
                        tasks.add(t);
                        break;
                    default:
                        t = new Todo(input.substring(input.indexOf("todo") + 4));
                        tasks.add(t);
                        break;
                }
                System.out.println(dialogSeparator);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                System.out.println(dialogSeparator);


            }
        }
    }
}
