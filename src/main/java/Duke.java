import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks();
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }

        printMsg("Bye. Hope to see you again soon!");

    }

    private static void addTask(String task) {
        printMsg("added: " + task);
        tasks.add((task));
    }

    private static void printTasks() {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        for (String task : tasks) {
            System.out.println("     " + count + ". " + task);
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void printMsg(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    ____________________________________________________________");
    }
}
