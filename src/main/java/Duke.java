import java.util.ArrayList;
import java.util.Scanner;

/*
May want to catch NumberFormatException for Integer.parseInt
 */

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();
    public static Scanner sc = new Scanner(System.in);
    public static boolean exitApp = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        init();

        // App loop
        while (!exitApp) {
            update();
        }

        // Exit message
        displayMessage("Bye! Hope to see you again soon!");
    }

    public static void init() {
        displayMessage("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void update() {
        String input = sc.nextLine();
        if (input.isEmpty()) {
            return;
        }

        String[] words  = input.split(" ");
        String firstWord = words[0];

        if (firstWord.equalsIgnoreCase("bye")) {
            exitApp = true;
            return;
        }

        if (firstWord.equalsIgnoreCase("list")) {
            String output = "";
            for (int i = 0; i != list.size();++i) {
               Task t = list.get(i);
               output += (i + 1) + ". " + t.toString() + "\n";
            }
            displayMessage(output);
            return;
        }

        if (firstWord.equalsIgnoreCase("mark")) {
            int target = Integer.parseInt(words[1]) - 1;
            if (target < 0 || target >= list.size()) {
                displayMessage("This task does not exist!");
                return;
            }
            Task t = list.get(target);
            t.mark();
            String output = "I've marked this task as done!\n" + t.toString();
            displayMessage(output);
            return;
        }

        if (firstWord.equalsIgnoreCase("unmark")) {
            int target = Integer.parseInt(words[1]) - 1;
            if (target < 0 || target >= list.size()) {
                displayMessage("This task does not exist!");
                return;
            }
            Task t = list.get(target);
            t.unmark();
            String output = "I've marked this task as not done!\n" + t.toString();
            displayMessage(output);
            return;
        }

        list.add(new Task(input));
        displayMessage("Added " + input);
    }

    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
