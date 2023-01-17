import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> list = new ArrayList<String>();
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

        if (input.equalsIgnoreCase("bye")) {
            exitApp = true;
            return;
        }

        if (input.equalsIgnoreCase("list")) {
            String output = "";
            for (int i = 0; i != list.size();++i) {
               String item = list.get(i);
               output += (i + 1) + ". " + item + "\n";
            }
            displayMessage(output);
            return;
        }

        list.add(input);
        displayMessage("Added " + input);
    }

    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
