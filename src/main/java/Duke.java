import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> actions = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println("Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n");
        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.nextLine();
            String[] input_arr = input.split(" ");
            if (input.equals("bye")) {
                System.out.print("Bye I'm gonna go eat cookies. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                int len = actions.size();
                for (int i = 0; i < len; i++) {
                    System.out.println(i + 1 + ". " + actions.get(i).toString());
                }

            } else if (input_arr[0].equals("mark") || input_arr[0].equals("unmark")) {
                int index = Integer.valueOf(input_arr[1]) - 1;
                Task task = actions.get(index);
                if (input_arr[0].equals("mark")) {
                    System.out.println(task.mark());
                } else {
                    System.out.println(task.unmark());
                }
            }
            else {
                Task newTask = new Task(input);
                actions.add(newTask);
                System.out.println("NOM NOM NOM added: " + input);
            }

        }

    }
}
