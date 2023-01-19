import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void printList(ArrayList<String> stored) {
        for (int i = 0; i < stored.size(); i++) {
            System.out.println((i+1) + ". " + stored.get(i));
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();
        String input;
        while(true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else if (input.equals("list")) {
                printList(myList);
                System.out.println(separator);
            } else {
                myList.add(input);
                System.out.println("added: " + input);
                System.out.println(separator);
            }
        }
    }
}
