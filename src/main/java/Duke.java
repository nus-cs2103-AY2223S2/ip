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
            String[] inputWords = input.split(" ");
            if (input.equals("bye")) { // Exit
                break;
            }
            if (input.equals("list")) { // Display list
                System.out.println("\tHere are the tasks in your list:");
                for (int i=0; i<taskList.size(); i++) {
                    Task curr = taskList.get(i);
                    int index = i+1;
                    if (i == taskList.size()-1) { // Last item
                        System.out.println("\t" + index + ".[" + curr.getStatusIcon() + "] " + curr + "\n");
                        break;
                    }
                    System.out.println("\t" + index + ".[" + curr.getStatusIcon() + "] " + curr);
                }
                continue;
            }
            if (inputWords[0].equals("mark")) {
                int i = Integer.parseInt(inputWords[1]);
                Task t = taskList.get(i-1);
                t.markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + "[" + t.getStatusIcon() + "] " + t + "\n");
                continue;
            }
            if (inputWords[0].equals("unmark")) {
                int i = Integer.parseInt(inputWords[1]);
                Task t = taskList.get(i-1);
                t.unmarkAsDone();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + "[" + t.getStatusIcon() + "] " + t + "\n");
                continue;
            }
            Task t = new Task(input);
            taskList.add(t);
            System.out.println("\t" + input + "\n"); // Echo
        }
        System.out.println("\tWoof (╯ᆺ╰๑)"); // Outro
    }
}
