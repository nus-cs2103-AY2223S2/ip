package panav.ui;

import panav.task.Task;
import panav.task.TaskList;

import java.util.Scanner;
public class Ui {

    Scanner sc = new Scanner(System.in);
    private static final String LINE_DIVIDER = "____________________________________________________________";
    public void showLoadingError() {
        System.out.println("Oops!! There was an error in loading text from your file");
        System.out.println("Make sure your file exists in the correct location");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello hello! I'm Panav");
        System.out.println("What's up bro");
        showLine();
    }
    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }
    public String readCommand() {
        return sc.nextLine();
    }

    public void showAddTaskMessage(TaskList tasks, Task task) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
        showLine();
    }
}
