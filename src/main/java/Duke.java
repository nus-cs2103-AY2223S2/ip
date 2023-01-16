import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------");

        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.nextLine();
            System.out.println("----------------------------------------");
            String[] split = in.split(" ");
            if (split[0].equals("mark")) {
                Integer i = Integer.parseInt(split[1]);
                Task task = list.get(i-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (split[0].equals("unmark")) {
                Integer i = Integer.parseInt(split[1]);
                Task task = list.get(i-1);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + ".[" + curr.getStatusIcon() + "] " + curr.description);
                }
            } else {
                list.add(new Task(in));
                System.out.println(in);
            }
            System.out.println("----------------------------------------");
        }
        sc.close();
    }
}
