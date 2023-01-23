import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "____________________________________________________\n";
    private static ArrayList<Task> TASKS = new ArrayList<Task>();

    private static void displayIntro() {
        System.out.println(reply("Hello! I'm Duke\n What can I do for you?" + "\n"));
    }

    private static void displayOutro() {
        System.out.println(reply("Bye. Hope to see you again soon!" + "\n"));
    }

    private static void addTask(Task newTask) {
        TASKS.add(newTask);
        System.out.println(reply("added: " + newTask.getDescription() + "\n"));
    }

    private static void mark(String command) {
        int index = Integer.valueOf(command.substring(5)) - 1;
        Task target = TASKS.get(index);
        target.mark();
        System.out.println(reply("Nice! I've marked this task as above:\n  " + target + "\n"));
    }

    private static void unmark(String command) {
        int index = Integer.valueOf(command.substring(7)) - 1;
        Task target = TASKS.get(index);
        target.unmark();
        System.out.println(reply("OK, I've marked this task as not done yet:\n  " + target + "\n"));
    }

    private static void displayTasks() {
        String list_of_tasks = "";
        Task currentTask;
        for (int i = 0; i < TASKS.size(); i++) {
            currentTask = TASKS.get(i);
            list_of_tasks += i + 1 + "." + currentTask.toString() + "\n";
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

            if (command.startsWith("mark ")) {
                mark(command);
                command = input.nextLine();
                continue;
            } else if (command.startsWith("unmark ")) {
                unmark(command);
                command = input.nextLine();
                continue;
            }

            Task currentTask = new Task(command);
            if (!TASKS.contains(currentTask)) {
                addTask(currentTask);
            }
            command = input.nextLine();
        }

        displayOutro();
    }
}
