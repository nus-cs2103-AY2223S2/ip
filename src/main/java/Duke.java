import java.util.Scanner;

public class Duke {
    public static final String LOGO = "\n ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tasks tasks = new Tasks();

        System.out.println("Hello I am" + LOGO + "What Can I do for you?");
        while(true) {
            System.out.print("> ");
            String cmd = sc.nextLine();
            if (cmd.matches("^bye$")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.matches("^list$")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(tasks);
            } else if (cmd.matches("^mark [0-9]*$")) {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                if (tasks.markTask(index)) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("=> %s\n", tasks.getTask(index));
                } else {
                    System.out.println("Sorry, the task does not exist.");
                }
            } else if (cmd.matches("^unmark [0-9]*$")) {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                if (tasks.unmarkTask(index)) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf("=> %s\n", tasks.getTask(index));
                } else {
                    System.out.println("Sorry, the task does not exist.");
                }
            } else {
                tasks.addTask(cmd);
                System.out.printf("Added: %s\n", cmd);
            }
        }
        sc.close();
    }
}
