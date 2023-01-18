import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String bye = "bye";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon!";
    private final static String list = "list";
    private static ArrayList<String> tasks = new ArrayList<>();

    private static void indentedPrintln(String message) {
        String indentedMessage = "   " + message;
        System.out.println(indentedMessage);
    }

    private static void list() {
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            indentedPrintln(i + ". " + tasks.get(i - 1));
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals(bye)) {
            if (!str.equals(list)) {
                tasks.add(str);
                indentedPrintln("added: " + str);
            } else {
                list();
            }

            System.out.println();
            str = sc.nextLine();
        }
        indentedPrintln(goodbyeMessage);
    }
}
