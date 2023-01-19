import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Homie {

    private static final List<Task> list = new ArrayList<>();

    public static void print(String s) {
        System.out.println(s);
    }

    public static void listTasks() {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            Homie.print("   > " + (i + 1) + ". " + task.toString());
        }
    }

    public static void interact() {
        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                Homie.print("   > Aight imma head out");
                break;
            }

            if (input.equals("list")) {
                Homie.listTasks();
                continue;
            }

            Homie.print("   > added: " + input);
            Homie.list.add(new Task(input));
        }
    }

    public static void main(String[] args) {

        Homie.print("   > What's up dawg");
        Homie.interact();
    }
}
