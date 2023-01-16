import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List li = new ArrayList<String>();

    public static void addTask(String task) {
        li.add(task);
        System.out.println("new task added: " + task);
        System.out.println("What else can I do for you?");
    }

    public static void listCommand() {
        if (li.size() == 0) {
            System.out.println("You have not added any tasks.");
        } else {
            for (int i = 0; i < li.size(); i++) {
                System.out.println(i+1 + ". " + li.get(i));
            }
        }
        System.out.println("What else can I do for you?");
    }

    public static void blahCommand() {
        System.out.println("blah");
        System.out.println("What else can I do for you?");
    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
                command = sc.nextLine();
            } else if (command.equals("blah")) {
                blahCommand();
                command = sc.nextLine();
            } else {
                addTask(command);
                command = sc.nextLine();
            }
        }
        byeCommand();

    }
}
