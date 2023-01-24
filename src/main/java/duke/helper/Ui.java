package duke.helper;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final String line = "_______________________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showErrorMsg(String errorType, Exception e, int size) {
        switch(errorType) {
            case "IO":
                e.printStackTrace();
                break;
            case "duke.Duke":
                System.out.println(e);
                break;
            case "NAN":
                System.out.println("Mark commands need to be followed by an integer!");
                break;
            case "OutOfBounds":
                System.out.println(String.format("Sorry but there are only %d tasks stored!", size));
                break;
        }
    }

    public String getNextLine() {
        return this.scanner.nextLine();
    }

    public void closeScanner() {
        this.scanner.close();
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(this.line);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showMark(boolean isDone, Task taskToMark) {
        if(isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark);
    }

    public void showTaskOutput(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
