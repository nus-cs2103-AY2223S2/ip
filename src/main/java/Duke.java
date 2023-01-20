import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm tyy\nWhat can I do for you?");

        ArrayList<Task> Task = new ArrayList<Task>();

        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < Task.size(); i++) {
                    int numOfTask = i + 1;
                    System.out.println(" " + numOfTask +". " + Task.get(i) + "\n");
                }
            } else {
                Task task = new Task(input);
                Task.add(task);
                System.out.println(" added: " + task);
            }

            input = scan.next();
        }
        System.out.println(" " + "Ciao. Hope to see you again soon!");
    }
}
