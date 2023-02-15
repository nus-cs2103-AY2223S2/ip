package userinteraction;

import storage.TaskList;
import task.Task;

import java.util.Scanner;

public class Ui {
    private final String LOGO = "\t  ____        _        \n"
            + "\t |  _ \\ _   _| | _____ \n"
            + "\t | | | | | | | |/ / _ \\\n"
            + "\t | |_| | |_| |   <  __/\n"
            + "\t |____/ \\__,_|_|\\_\\___|\n";
    private final String SEPARATE_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private final String BYE_MSG = "\t Bye. Hope to see you again soon!";

    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public String printWelcomeMsg() {
        String str = LOGO + "\t Hello! I am Duke.\n"  +
                "\t What can I do for you?\n";
        return str;
    }
    public String printLine() {
        return SEPARATE_LINE;
    }
    public String printAddTaskMsg(TaskList taskList, Task task) {
        String str = "\t Got it. I've added this task:\n  " + "\t\t " +
                task.toString() + "\n\t Now you have " +
                taskList.getSize() + " tasks in the list\n";
        return str;
    }
    public String printMarkTaskMsg(boolean isMarked, Task task) {
        String str = "";
        if (isMarked) {
            str = "\t Nice! I've marked this task as done: \n" +
                    "\t\t " + task.toString() + "\n";
        } else {
            str = "\t OK, I've marked this task as not done yet: \n" +
                    "\t\t " + task.toString() + "\n";
        }
        return str;
    }
    public String printDeleteTaskMsg(Task task, int size) {
        String str = "\t Noted. I've removed this task:\n" + "\t\t " +
                task.toString() + "\n\t Now you have " +
                size + " tasks in the list.\n";
        return str;
    }

    public String printFindTaskMsg() {
        String str = "\t Here are the matching tasks in your list:";
        return str;
    }

    public String printWrongMsg() {
        String str = "\t ☹ OOPS!!! I'm sorry, but I don't know what that means.\n";
        return str;
    }
    public String printByeMsg() {
        return BYE_MSG;
    }
}