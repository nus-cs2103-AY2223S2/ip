import java.util.Scanner;

public class Duke {
    private static Task[] task = new Task[100];

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    public static void add(Task t) {
        int numOfTask = Task.getNumOfTask();
        task[numOfTask - 1] = t;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task: \n\t  " + t + "\n\tNow you have " + numOfTask + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void list() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int numOfTask = Task.getNumOfTask();
        for(int i = 0; i < numOfTask; i++) {
            int tmp = i + 1;
            System.out.println("\t" + task[i]);
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
        System.out.println("\t  " + task[i]);
        System.out.println("\t____________________________________________________________");
    }

    public static void unmark(int i) {
        task[i].setDone(false);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task[i]);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] split = input.split(" ", 2);
            if (input.equals("list")) {
                list();
            } else if (input.contains("mark")) {
                int index = Integer.parseInt(split[1]) - 1;
                if (input.contains("unmark")) {
                    unmark(index);
                } else {
                    mark(index);
                }
            } else {
                Task t = null;
                switch (split[0]) {
                    case "todo":
                        //System.out.println("todo");
                        t = new ToDos(split[1]);
                        break;
                    case "deadline":
                        //System.out.println("deadline");
                        String[] s1 = split[1].split("/by ", 2);
                        t = new Deadlines(s1[0], s1[1]);
                        break;
                    case "event":
                        String[] s2 = split[1].split("/from ", 2);
                        String[] s3 = s2[1].split("/to ", 2);
                        t = new Events(s2[0], s3[0], s3[1]);
                        break;
                }
                add(t);
            }
            input = scanner.nextLine();
        }

        exit();
    }
}
