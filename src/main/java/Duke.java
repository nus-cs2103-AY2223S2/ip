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
        String greeting = "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
        String separator = "____________________________________________________________\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String endCommand = "bye";
        String listCommand = "list";
        ArrayList<Task> textStore = new ArrayList<>(100);
        System.out.println(greeting + separator);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals(endCommand)) {
                sc.close();
                System.out.println(goodbye + separator);
                break;
            } else if (command.equals(listCommand)) {
                for (int i = 0; i < textStore.size(); i++) {
                    System.out.println((i + 1) + ". " + textStore.get(i));
                }
                System.out.println(separator);
            } else {
                textStore.add(new Task(command));
                System.out.println("added: " + command + "\n" + separator);
            }
        }
    }
}
