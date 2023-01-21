import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "____________________________________________________\n";
    private static ArrayList<String> tasks = new ArrayList<String>();

    private static void displayIntro() {
        System.out.println(reply("Hello! I'm Duke\n What can I do for you?" + "\n"));
    }

    private static void displayOutro() {
        System.out.println(reply("Bye. Hope to see you again soon!" + "\n"));
    }

    private static void addTask(String task) {
        tasks.add(task);
        System.out.println(reply("added: " + task + "\n"));
    }

    private static void displayTasks() {
        String list_of_tasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            list_of_tasks += i + 1 + ". " + tasks.get(i) + "\n";
        }
        System.out.println(reply(list_of_tasks));
    }

    private static String reply(String command) {
        return DIVIDER_LINE + command + DIVIDER_LINE;
    }

    public static void main(String[] args) {
        displayIntro();

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayTasks();
                command = input.nextLine();
                continue;
            }
            if (!tasks.contains(command)) {
                addTask(command);
            }
            command = input.nextLine();
        }

        displayOutro();
    }
}
