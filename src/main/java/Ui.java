import java.util.Scanner;

public class Ui {

    public static boolean isRunning = true;
    public static String line;
    public static Scanner sc = new Scanner(System.in);


    public static void displayNumTask(int numTask) {
        displayMsg("Now you have " + numTask + " task" + (numTask == 1 ? "" : "s") + " in the list.");
    }

    public static void greetUser() {
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        line = sc.nextLine();
    }

    public static String getNextCommand() {
        System.out.println("");
        line = sc.nextLine();
        return line;
    }

    public static void displayMsg(String msg) {
        System.out.println(indentString(wrapMessageBorder(msg), 1));
    }

    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }

    public static String indentString(String msg, int indendationLevel) {
        String indent = "  " .repeat(indendationLevel);
        String[] lines = msg.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(indent + lines[i] + (i + 1 < lines.length ? "\n" : ""));
        }
        return result.toString();
    }

    public static void shutDown() {
        isRunning = false;
        displayMsg("Bye. Hope to see you again soon!");
    }
}
