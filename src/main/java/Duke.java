import java.util.*;

public class Duke {
    private static boolean isRunning = true;
    private static List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        String separator = "____________________________________________________________";
        String duke = "Duke: ";
        String you = "You: ";
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println(duke);
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);

        while (isRunning) {
            System.out.println(you);
            String command = sc.nextLine();
            System.out.println(separator);
            System.out.println(duke);

            if (command.equals("bye")) {
                isRunning = false;
                System.out.println("Bye! Hope to see you again soon!");
            } else if (command.equals("list")) {
                if (!tasks.isEmpty()) {
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(String.format("%d.%s", i, tasks.get(i - 1).printTask()));
                    }
                } else {
                    System.out.println("You currently have no items in your to-do list!");
                }
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.get(taskIndex).setDone(true);
                System.out.println("Okay! I've marked this task as done!");
                System.out.println(tasks.get(taskIndex).printTask());
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.get(taskIndex).setDone(false);
                System.out.println("Okay! I've marked this task as not done yet!");
                System.out.println(tasks.get(taskIndex).printTask());
            } else {
                tasks.add(new Task(command));
                System.out.println(String.format("Added: %s", command));
            }

            System.out.println(separator);
        }
    }
}
