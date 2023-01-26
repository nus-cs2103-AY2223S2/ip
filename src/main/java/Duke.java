import exceptions.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.logging.Logger;
import java.util.logging.Level;


public class Duke {
    private static void printMsg(String[] msgs) {
        System.out.println("____________________________________________________________");
        for (String msg : msgs) {
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

    private static void saveTasks(List<Task> tasks) {
        try {
            OutputStream output = new FileOutputStream("./data.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(output);
            objOut.writeObject(tasks);
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Task> loadTasks() {
        InputStream input = null;
        try {
            input = new FileInputStream("./data.txt");
            ObjectInputStream objIn = new ObjectInputStream(input);
            @SuppressWarnings("unchecked")
            List<Task> tasks = (List<Task>) objIn.readObject();
            objIn.close();
            return tasks;
        } catch (FileNotFoundException fnfe) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(Duke.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Duke.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {

        String[] welcomeMsg = {"Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);

        List<Task> data = loadTasks();

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
                        case "delete":
                            idx = Integer.parseInt(cmd[1]) - 1;
                            tsk = data.get(idx);
                            data.remove(idx);
                            tskNum = String.format("Now you have %d tasks in the list.", data.size());
                            String[] delMsg = {"Noted. I've removed this task:", tsk.toString(), tskNum};
                            printMsg(delMsg);
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    saveTasks(data);
                } catch (DukeException e) {
                    printMsg(e.getMessage());
                }
            }

    }
}
