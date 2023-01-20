import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<String> listOfThings = new ArrayList<String>();
        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();
            String bye = "bye";
            String showList = "list";
            if (bye.equalsIgnoreCase(msg)) {
                System.out.println("Bye. Hope to see Byou again soon!");
                loop = false;
            } else if (showList.equalsIgnoreCase(msg)) {
                for (String s : listOfThings) {
                    System.out.println(s);
                }
            } else {
                listOfThings.add(msg);
                System.out.println("added " + msg);
            }
        }
    }
}
