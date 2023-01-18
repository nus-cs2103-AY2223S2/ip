import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean program_running_status = true;

        String echo;
        Scanner scan = new Scanner(System.in);

        ArrayList<String> list_to_store = new ArrayList<String>();


        while (program_running_status) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }

            if (echo.equals("list")) {
                for (int i = 0; i < list_to_store.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + list_to_store.get(i));
                }
                // put in loop to read the list
                continue;
            }

            list_to_store.add(echo);

            System.out.println("    Duke says:");
            System.out.println("    Added " + echo);
            
        }
        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
