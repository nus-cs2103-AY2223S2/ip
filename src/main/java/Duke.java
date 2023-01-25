import exceptions.DukeException;

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
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] welcomeMsg = {"Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);

        List<Task> data = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                try{
                    String msg = sc.nextLine();
                    String cmd[] = msg.split(" ", 2);

                    if (cmd[0].equals("bye")) {
                        printMsg("Bye. Hope to see you again soon!");
                        break;
                    }
                    int idx;
                    Task tsk;
                    String markMsg;
                    String cfmMsg = "Got it. I've added this task:";
                    String tskNum;
                    String[] desc;


                    switch (cmd[0]) {
                        case "list":
                            String[] list = new String[data.size()];
                            for (int i = 0; i < data.size(); i++) {
                                tsk = data.get(i);
                                list[i] = String.format(" %s", tsk);
                            }
                            printList(list);
                            break;
                        case "mark":
                            idx = Integer.parseInt(cmd[1]) - 1;
                            tsk = data.get(idx);
                            tsk.mark();
                            markMsg = "Nice! I've marked this task as done:";
                            String markedTask = String.format(" %s", tsk);
                            String[] markedMsg = {markMsg, markedTask};
                            printMsg(markedMsg);
                            break;
                        case "unmark":
                            idx = Integer.parseInt(cmd[1]) - 1;
                            tsk = data.get(idx);
                            tsk.unmark();
                            markMsg = "OK, I've marked this task as not done yet:";
                            String unmarkedTask = String.format(" %s", tsk);
                            String[] unmarkedMsg = {markMsg, unmarkedTask};
                            printMsg(unmarkedMsg);
                            break;
                        case "todo":
                            if (cmd.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            tsk = new Todo(cmd[1]);
                            data.add(tsk);
                            tskNum = String.format("Now you have %d tasks in the list.", data.size());
                            String[] todoMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                            printMsg(todoMsg);
                            break;
                        case "deadline":
                            desc = cmd[1].split(" /by ");
                            tsk = new Deadline(desc[0], desc[1]);
                            data.add(tsk);
                            tskNum = String.format("Now you have %d tasks in the list.", data.size());
                            String[] deadlineMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                            printMsg(deadlineMsg);
                            break;
                        case "event":
                            desc = cmd[1].split(" /from | /to ");
                            tsk = new Event(desc[0], desc[1], desc[2]);
                            data.add(tsk);
                            tskNum = String.format("Now you have %d tasks in the list.", data.size());
                            String[] eventMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                            printMsg(eventMsg);
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    printMsg(e.getMessage());
                }
            }

    }
}
