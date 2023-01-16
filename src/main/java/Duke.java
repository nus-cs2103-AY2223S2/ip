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
        ArrayList<String> taskList = new ArrayList<String>(); // Create list
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) { // Exit
                break;
            }
            if (input.equals("list")) { // Display list
                for (int i=0; i<taskList.size(); i++) {
                    String curr = taskList.get(i);
                    int index = i+1;
                    if (i == taskList.size()-1) { // Last item
                        System.out.println("\t" + index + ". " + curr + "\n");
                        break;
                    }
                    System.out.println("\t" + index + ". " + curr);
                }
                continue;
            }
            taskList.add(input);
            System.out.println("\t" + input + "\n"); // Echo
        }
        System.out.println("\tWoof (╯ᆺ╰๑)"); // Outro
    }
}
