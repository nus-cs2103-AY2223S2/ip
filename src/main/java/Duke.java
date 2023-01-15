import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String greeting = "____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    private static boolean active = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(greeting);
        int counter = 0;
        while(active) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
                case "bye":
                    active = false;
                    System.out.println(goodbye);
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println(i + 1 + "." + tasks.get(i).toString());
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "mark": {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(num - 1).markDone();
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            "   " + tasks.get(num - 1).toString() + "\n" +
                            "____________________________________________________________");
                    break;
                }
                case "unmark": {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(num - 1).markUndone();
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            "   " + tasks.get(num - 1).toString() + "\n" +
                            "____________________________________________________________");
                    break;
                }
                default:
                    counter++;
                    tasks.add(new Task(input));
                    System.out.println("____________________________________________________________\n added: "
                            + input + "\n____________________________________________________________");
                    break;
            }
        }
    }
}
