import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List myList = new List();
        System.out.println("Hi! I am Duke. How may I help you today?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!\n");
                return;
            }
            if (cmd.equals("list")) {
                myList.print();
            }
            else if (cmd.startsWith("mark")) {
                int index = Integer.parseInt(cmd.substring(4).strip());
                myList.mark(index);
            }
            else if (cmd.startsWith("unmark")) {
                int index = Integer.parseInt(cmd.substring(6).strip());
                myList.unmark(index);
            }
            else {
                try {
                    myList.add(cmd);
                }
                catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
