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

        List<Task> data = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            String cmd[] = msg.split(" ", 2);
            if (cmd[0].equals("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            } else if (cmd[0].equals("list")) {
                String[] list = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    Task tsk = data.get(i);
                    list[i] = String.format("[%s] %s", tsk.getStatusIcon(), tsk);
                }
                printList(list);
            } else if (cmd[0].equals("mark")) {
                int idx = Integer.parseInt(cmd[1]) - 1;
                Task tsk = data.get(idx);
                tsk.mark();
                String cfmMsg = "Nice! I've marked this task as done:";
                String markedTask = String.format(" [%s] %s", tsk.getStatusIcon(), tsk);
                String[] markedMsg = {cfmMsg, markedTask};
                printMsg(markedMsg);
            } else if (cmd[0].equals("unmark")) {
                int idx = Integer.parseInt(cmd[1]) - 1;
                Task tsk = data.get(idx);
                tsk.unmark();
                String cfmMsg = "OK, I've marked this task as not done yet:";
                String unmarkedTask = String.format(" [%s] %s", tsk.getStatusIcon(), tsk);
                String[] unmarkedMsg = {cfmMsg, unmarkedTask};
                printMsg(unmarkedMsg);
            } else {
                Task tsk = new Task(msg);
                data.add(tsk);
                printMsg("added: " + tsk);
            }
        }
    }
}
