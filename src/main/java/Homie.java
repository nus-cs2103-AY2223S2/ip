import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Homie {

    private static final List<Task> taskList = new ArrayList<>();

    public static void print(String s) {
        System.out.println(s);
    }

    public static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Homie.print("   > " + (i + 1) + ". " + task.toString());
        }
    }

    public static void modifyTask(String[] request) {
        String operation = request[0];
        int idx = Integer.parseInt(request[1]);
        Task t = taskList.get(idx - 1);

        if (operation.equals("mark")) {
            t.markAsDone();
            Homie.print("   > Task masked as done: " + t.toString());
        } else {
            t.markAsUndone();
            Homie.print("   > Task masked as undone: " + t.toString());
        }
    }

    public static void shutdown() {
        Homie.print("   > Aight imma head out");
    }

    public static void interact() {
        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                Homie.shutdown();
                break;
            }

            if (input.equals("list")) {
                Homie.listTasks();
                continue;
            }

            String[] split = input.split(" ", 2);
            if (split[0].equals("mark") || split[0].equals("unmark")) {
                Homie.modifyTask(split);
                continue;
            }

            // If string is a task, add to task list
            Homie.print("   > added: " + input);
            Homie.taskList.add(new Task(input));
        }
    }

    public static void main(String[] args) {

        Homie.print("   > What's up dawg");
        Homie.interact();
    }
}
