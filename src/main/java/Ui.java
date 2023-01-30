import java.util.Scanner;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String DIVIDER_LINE = "_________________________________________________\n";
    private final String BYE_MSG = "Bye. Hope to see you again soon!";

    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public void printWelcomeMsg() {
        System.out.println("Hello from\n" + LOGO + "\nWhat can I do for you?");
    }

    public void printLine() {
        System.out.println(DIVIDER_LINE);
    }

    public void printAddTaskMsg(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task:\n  " +
                task.toString() + "\n Now you have " + taskList.getSize() + " tasks in the list");
    }

    public void printMarkTaskMsg(boolean isMarked, Task task) {
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
        }
    }

    public void printDeleteTaskMsg(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n  " +
                task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }

}
