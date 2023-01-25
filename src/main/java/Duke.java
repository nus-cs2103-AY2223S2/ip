import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printMsg(String[] msgs) {
        System.out.println("____________________________________________________________");
        for (String msg :  msgs) {
            System.out.println(msg);
        }
        System.out.println("____________________________________________________________");
    }

    private static void printMsg(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    private static void printList(String[] list) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] welcomeMsg = {logo, "Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);

        List<String> data = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                String[] list = new String[data.size()];
                list = data.toArray(list);
                printList(list);
            } else {
                data.add(cmd);
                printMsg("added: " + cmd);
            }
        }
    }
}
