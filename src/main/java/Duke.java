import java.util.Scanner;

public class Duke {
    private static Task[] task = new Task[100];

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    public static void add(String s) {
        int numOfTask = Task.getNumOfTask();
        task[numOfTask] = new Task(s);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + s);
        System.out.println("\t____________________________________________________________");
    }

    public static void list() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int numOfTask = Task.getNumOfTask();
        for(int i = 0; i < numOfTask; i++) {
            int tmp = i + 1;
            Task currentTask = task[i];
            String msg = tmp + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
            System.out.println("\t" + msg);
        }
        System.out.println("\t____________________________________________________________");
    }
    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void mark(int i) {
        task[i].setDone(true);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done: ");
        String msg = "  [" + task[i].getStatusIcon() + "] " + task[i].getDescription();
        System.out.println("\t" + msg);
        System.out.println("\t____________________________________________________________");
    }

    public static void unmark(int i) {
        task[i].setDone(false);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        String msg = "  [" + task[i].getStatusIcon() + "] " + task[i].getDescription();
        System.out.println("\t" + msg);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list();
            } else if (input.contains("mark")) {
                String[] split = input.split(" ", 2);
                int index = Integer.parseInt(split[1]) - 1;
                if (input.contains("unmark")) {
                    unmark(index);
                } else {
                    mark(index);
                }
            } else {
                add(input);
            }
            input = scanner.nextLine();
        }

        exit();
    }
}
