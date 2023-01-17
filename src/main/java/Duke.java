import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        */

        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting + "\n");

        String[] commands = {"bye", "list"};
        ArrayList<String> taskStorage = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks(taskStorage);
            } else {
                taskStorage.add(input);
                String output = String.format("Task added: %s", input);
                System.out.println(output);
            }
            input = sc.nextLine();
        }

        printGoodbye();
        sc.close();
    }

    public static void printGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        return;
    }

    public static void printTasks(ArrayList<String> taskStorage) {
        int count = 1;
        for (String task : taskStorage) {
            String output = String.format("%d. %s", count++, task);
            System.out.println(output);
        }
        return;
    }

    public static boolean isCommand(String input, String[] commands) {
        for (String s : commands) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }
}
