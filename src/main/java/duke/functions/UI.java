package duke.functions;

import duke.ToDoList;
import duke.tasks.Task;

public class UI {
    private static String startEndDivider = "____________________________________________________________\n";
    private static String mainDivider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
    private static String errorDivider =  "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n";

    private UI() {
    }

    public static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(startEndDivider + "What can the Duke help you with today?\n" + startEndDivider);
    }

    public static void exitMsg() {
        System.out.println(startEndDivider + "Goodbye, feel free to call the Duke again whenever you need.\n"
                + startEndDivider);
    }

    public static void taskAddDelete(ToDoList ls, Task task, String command) {
        System.out.println(mainDivider + "The Duke has " + command + " the following task:\n"
                + " - " + task + "\n"
                + "You now have " + ls.getToDoCount() + " task!\n" + mainDivider);
    }

    public static void taskMarking(ToDoList ls, int index, String command) {
        System.out.println(mainDivider + "The Duke has " + command + " the following task:\n"
                + " - " + ls.getTask(index) + "\n" + mainDivider);
    }

    public static void listMsg(String msg) {
        System.out.println(mainDivider + "TO DO LIST:\n" + msg + mainDivider);
    }

    public static void findResultMsg(String msg, String keyword) {
        System.out.println(mainDivider + "The keyword given is:\n\n\"" + keyword
                + "\"\n\nThe Duke has found the following tasks:\n" + msg + mainDivider);
    }

    public static void errorMsg(String msg) {
        System.out.println("!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n"
                + msg + "\n"
                +  "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n");
    }
}
