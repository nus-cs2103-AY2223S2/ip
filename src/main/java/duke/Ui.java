package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void displayIntro() {
        String logo
                = " ___________________\n"
                + " | _______________ |\n"
                + " | |             | |\n"
                + " | |     MEL     | |\n"
                + " | |     <3      | |\n"
                + " | |   CS2103    | |\n"
                + " | |_____________| |\n"
                + " |_________________|\n"
                + "     _[_______]_\n"
                + " ___[___________]___\n"
                + "|         [_____] []|__\n"
                + "|         [_____] []|  \\__\n"
                + "L___________________J     \\ \\___\\/\n"
                + " ___________________      / \\\n"
                + "/###################\\    (__)\n";
        System.out.println(logo + "Hello! I'm MEL\nWhat can I do for you?\n-----------------------");
    }

    public void displayBye() {
        System.out.println("MEL: Bye. Hope to see you again soon!");
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void displayRepeatedBlank() {
        System.out.print("> ");
    }

    public void displayInvalidInputFormat() {
        System.out.println("MEL: Invalid format of input, please try again!");
    }

    public void displayInvalidIntFormat() {
        System.out.println("MEL: Invalid integer, please try again!");
    }

    public void displayInvalidFromToFormat() {
        System.out.println("Invalid format, please try again using [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD] format!");
    }

    public void displayInvalidByFormat() {
        System.out.println("Invalid format, please try again using [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD] format!");
    }

    public void displayAdded(String taskName, int size) {
        System.out.println("Got it. I've added this task:\n" + taskName);
        displayTotalNumList(size);
    }

    public void displayDeleted(String taskName) {
        System.out.println("Noted. I've removed this task:\n" + taskName);
    }

    public void displayTotalNumList(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void displayMarked(String taskMarked) {
        System.out.println("Nice! I've marked this task as done:\n" + taskMarked);
    }

    public void displayUnmarked(String taskUnmarked) {
        System.out.println("OK, I've marked this task as not done yet:\n" + taskUnmarked);
    }
}
