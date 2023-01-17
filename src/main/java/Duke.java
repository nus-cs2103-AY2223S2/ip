import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");

        DukeList dukeList = new DukeList();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            // More cases to be added.
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    dukeList.list();
                    break;
                default:
                    dukeList.addTask(command);
                    break;
            }
        }

    }
}
