import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

/**
 * @author Shi Jiaao
 */

public class Duke {
    private static String printList(ArrayList<Task> toDoList) {
        String res = "";
        res += "here are your tasks\n";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        return res;
    }

    private static String printTaskAdd(Task task, ArrayList<Task> toDoList) {
        return String.format("Got it! I've added this task:\n" +
                "    %s\n" +
                "Now you have %d tasks in the list.",
                task.toString(), toDoList.size());
    }

    private static void checkLength(String str) throws DukeException {
        if (str.length() == 0) {
            throw new DukeException("empty String");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        /* project starts here */
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();
        while (true) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            int editIndex = Character.getNumericValue
                    (command.charAt(command.length() - 1)) - 1;
            switch (commandArr[0]) {
                case "list":
                    System.out.println(printList(toDoList));
                    break;
                case "mark":
                    toDoList.get(editIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "    " +
                            toDoList.get(editIndex));
                    break;
                case "unmark":
                    toDoList.get(editIndex).markUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "    " +
                            toDoList.get(editIndex));
                    break;
                case "todo":
                    String taskName = "";
                    for (int i = 1; i < commandArr.length; i++) {
                        taskName += commandArr[i] + " ";
                    }
                    taskName = taskName.trim();
                    try {
                        checkLength(taskName);
                    } catch (DukeException ex) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    Task toAdd = new ToDo(taskName);
                    toDoList.add(toAdd);
                    System.out.println(printTaskAdd(toAdd, toDoList));
                    break;
                case "deadline":
                    // separate with whitespace
                    // name -> go from start to /by
                    // time -> go from after /by to end
                    taskName = "";
                    int curInd = 1;
                    while (curInd < commandArr.length && !commandArr[curInd].equals("/by")) {
                        taskName += commandArr[curInd] + " ";
                        curInd += 1;
                    }
                    taskName = taskName.trim();
                    try {
                        checkLength(taskName);
                    } catch (DukeException ex) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    curInd += 1; // skips "/by"
                    String taskDeadline = "";
                    while (curInd < commandArr.length) {
                        taskDeadline += commandArr[curInd] + " ";
                        curInd += 1;
                    }
                    taskDeadline = taskDeadline.trim();
                    toAdd = new Deadline(taskName, taskDeadline);
                    toDoList.add(toAdd);
                    System.out.println(printTaskAdd(toAdd, toDoList));
                    break;
                case "event":
                    taskName = "";
                    curInd = 1;
                    while (curInd < commandArr.length && !commandArr[curInd].equals("/from")) {
                        taskName += commandArr[curInd] + " ";
                        curInd += 1;
                    }
                    taskName = taskName.trim();
                    try {
                        checkLength(taskName);
                    } catch (DukeException ex) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    curInd += 1;
                    String from = "";
                    while (curInd < commandArr.length && !commandArr[curInd].equals("/to")) {
                        from += commandArr[curInd] + " ";
                        curInd += 1;
                    }
                    from = from.trim();
                    curInd += 1;
                    String to = "";
                    while (curInd < commandArr.length) {
                        to += commandArr[curInd] + " ";
                        curInd += 1;
                    }
                    to = to.trim();
                    toAdd = new Event(taskName, from, to);
                    toDoList.add(toAdd);
                    System.out.println(printTaskAdd(toAdd, toDoList));
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                    return;
                case "delete":
                    System.out.println(String.format("Noted. I've removed this task:\n" +
                                    "    %s\n" +
                                    "Now you have %d tasks in the list.",
                            toDoList.get(editIndex), toDoList.size() - 1));
                    toDoList.remove(editIndex);
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
