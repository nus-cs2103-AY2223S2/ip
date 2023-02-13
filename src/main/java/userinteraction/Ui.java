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
    public void printWelcomeMsg() {
        String str = LOGO + "\t Hello! I am duke.Duke.\n"  + "\t What can I do for you?\n";
        System.out.println(str);
    }
    public void printLine() {
        System.out.println(SEPARATE_LINE);
    }
    public void printAddTaskMsg(TaskList taskList, Task task) {
        String str = "\t Got it. I've added this task:\n  " + "\t\t " +
                task.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list\n";
        System.out.println(str);
    }
    public void printMarkTaskMsg(boolean isMarked, Task task) {
        String str = "";
        if (isMarked) {
            str =  "\t Nice! I've marked this task as done: \n" + "\t\t " + task.toString() + "\n";
        } else {
            str =  "\t OK, I've marked this task as not done yet: \n" + "\t\t " + task.toString() + "\n";
        }
        System.out.println(str);
    }
    public void printDeleteTaskMsg(Task task, int size) {
        String str = "\t Noted. I've removed this task:\n" + "\t\t " +
                task.toString() + "\n\t Now you have " + size + " tasks in the list.\n";
        System.out.println(str);
    }

    public void printFindTaskMsg() {
        String str = "\t Here are the matching tasks in your list:";
        System.out.println(str);
    }
    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }
}