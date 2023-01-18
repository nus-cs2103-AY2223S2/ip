import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Willy {
    public static void main(String[] args) {
        String logo = "Willy";
        System.out.println("Hello! I'm " + logo +
                "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        List<Task> lst = new ArrayList<Task>();

        while (true) {
            String command = sc.nextLine();

            if (command.contains("mark")) {
                String[] temp = command.split(" ");
                Task tempTask = lst.get(Integer.parseInt(temp[1]) - 1);
                tempTask.mark();
            } else if (command.contains("unmark")) {
                String[] temp = command.split(" ");
                Task tempTask = lst.get(Integer.parseInt(temp[1]) - 1);
                tempTask.unmark();
            } else if (command.equals("list")) {
                // System.out.println("list\n");
                for (int index = 0; index < lst.size(); index++) {
                    Task curr = lst.get(index);
                    System.out.println((index + 1) + " " + curr.getStatusIcon() + curr.getMsg());
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                sc.close();
            } else {
                lst.add(new Task(command));
                System.out.println("added: " + command);
            }

            // if (command.equals("read book")) {
            // System.out.println("added: read book\n");
            // lst.add(new Task("read book"));
            // }
            // if (command.equals("return book")) {
            // System.out.println("added: return book\n");
            // lst.add(new Task("return book"));
            // }

        }

    }
}
