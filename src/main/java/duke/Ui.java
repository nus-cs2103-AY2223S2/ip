package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    public static String HELLO = "Hello! I am Duke Nice To Meet You\n";
    public static String BYE = "Bye! Hope to See You Again!";
    public static String ADD = "Got it fam! I've added this task:\n ";


    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getCommand() {
        return sc.nextLine();
    }

    public void showError(String e_message) {
        System.out.println(e_message);
    }

    public void greet() {
        System.out.println(HELLO);
    }

    public void bye() {
        System.out.println(BYE);
    }

    public void showList(TaskList taskList) {
        try {
            taskList.printTasks();
        } catch (DukeException e) {
            this.showError(e.getMessage());
        }
        System.out.println("");
    }

    public void printMarkTask(Task markedTask) {
        System.out.println("Nice! I have marked this task as Done:\n" + "[X] " +
                markedTask.getDescription() + "\n");
    }

    public void printAddTask(Task to_add, Integer num_tasks) {
        System.out.println("Got it fam! I've added this task:\n " + to_add.getDescription());
        System.out.println("You currently have " + num_tasks + " tasks in this list!\n");
    }

    public void printUnmarkTask(Task unmarkedTask) {
        System.out.println("Ok! I have marked this task as not done yet:\n" +
                "[ ] " + unmarkedTask.getDescription() + "\n");
    }

    public void printDeleteTask(Task removedTask, Integer num_tasks) {
        System.out.println("Noted!I have deleted the task for you:\n " +
                removedTask.getDescription() + "\nyou currently have " + num_tasks +
                " tasks in this list!\n");
    }
}
