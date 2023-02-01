package duke.functions;

import duke.ToDoList;

import duke.tasks.Task;

public class Ui {
    private static final String DIVIDER_START_END
            = "____________________________________________________________\n";
    private static final String DIVIDER_MAIN
            = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
    private static final String DIVIDER_ERROR
            = "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n";

    private Ui() {
    }

    public static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER_START_END
                + "What can the Duke help you with today?\n"
                + DIVIDER_START_END);
    }

    public static void exitMsg() {
        System.out.println(DIVIDER_START_END
                + "Goodbye, feel free to call the Duke again whenever you need.\n"
                + DIVIDER_START_END);
    }

    public static void taskAddDelete(ToDoList list, Task task, String command) {
        System.out.println(DIVIDER_MAIN + "The Duke has " + command + " the following task:\n"
                + " - " + task + "\n"
                + "You now have " + list.getToDoCount() + " task!\n" + DIVIDER_MAIN);
    }

    public static void taskMarking(ToDoList list, int index, String command) {
        System.out.println(DIVIDER_MAIN
                + "The Duke has " + command + " the following task:\n"
                + " - " + list.getTask(index) + "\n"
                + DIVIDER_MAIN);
    }

    public static void listMsg(String msg) {
        System.out.println(DIVIDER_MAIN
                + "TO DO LIST:\n"
                + msg
                + DIVIDER_MAIN);
    }

    public static void errorMsg(String msg) {
        System.out.println(DIVIDER_ERROR
                + msg + "\n"
                +  DIVIDER_ERROR);
    }
}
