import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "  /\\_/\\\n"
                + " /◞   ◟\\ \n"
                + "( ◕   ◕ )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    ●\n";
        System.out.println(logo + "BorzAI\n"); // Intro
        System.out.println("\tWhen all I do is for you, Kermie ♥\n"
                + "\tWhat can I do for you?\n"); // Greeting
        Scanner scan = new Scanner(System.in); // Allow user input
        ArrayList<Task> taskList = new ArrayList<>(); // Create list
        while (true) {
            String input = scan.nextLine();
            String[] parts = input.split(" ", 2);
            if (input.equals("bye")) { // Exit
                break;
            }
            if (input.equals("list")) { // Display list
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task curr = taskList.get(i);
                    int index = i + 1;
                    if (i == taskList.size() - 1) { // Last item
                        System.out.println("\t" + index + "." + curr + "\n");
                        break;
                    }
                    System.out.println("\t" + index + "." + curr);
                }
                continue;
            }
            if (parts[0].equals("mark")) {
                int i = Integer.parseInt(parts[1]);
                Task t = taskList.get(i - 1);
                t.markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + t + "\n");
                continue;
            }
            if (parts[0].equals("unmark")) {
                int i = Integer.parseInt(parts[1]);
                Task t = taskList.get(i - 1);
                t.unmarkAsDone();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + t + "\n");
                continue;
            }
            Task t;
            if (parts[0].equals("todo")) {
                t = new ToDo(parts[1]);
                taskList.add(t);
            }
            else if (parts[0].equals("deadline")) {
                String[] d_parts = parts[1].split(" /by ");
                t = new Deadline(d_parts[0], d_parts[1]);
                taskList.add(t);
            }
            else if (parts[0].equals("event")) {
                String[] e1_parts = parts[1].split(" /from ");
                String[] e2_parts = e1_parts[1].split(" /to ");
                t = new Event(e1_parts[0], e2_parts[0], e2_parts[1]);
                taskList.add(t);
            }
            else {
                t = new ToDo(input);
                taskList.add(t);
            }
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t  " + t);
            if (taskList.size() == 1) {
                System.out.println("\tNow you have " + taskList.size() + " task in the list.\n");
                continue;
            }
            System.out.println("\tNow you have " + taskList.size() + " tasks in the list.\n");
        }
        System.out.println("\tWoof (╯ᆺ╰๑)"); // Outro
    }
}
