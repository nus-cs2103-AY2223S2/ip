package dude.ui;

import dude.Task;
import dude.TaskList;

import java.util.Scanner;

public class Ui {

    public void showLine() {
        System.out.println(" _______________________________________________________________________");
    }

    public void showWelcome() {
        String logo = "  _____           __     \n" +
                " |  __ \\ __ __    | | ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        showLine();
        System.out.println("\tEh hello! I'm dude.");
        System.out.println("\tWhat you want me do for you?");
        showLine();
    }

    public void showList(TaskList tasks) {
        System.out.print(tasks);
    }

    public void showAdd(Task newTask) {
        System.out.println("\tGot it. I've added this task already:");
        System.out.println("\t" + newTask);
        System.out.println("\tNow got " + Task.count + " tasks in your list liao.");
    }

    public void showDelete(Task currentTask) {
        System.out.println("\tOkay can. I've removed this task already:");
        System.out.println("\t" + currentTask);
        System.out.println("\tNow only left with " + Task.count + " tasks in your list liao.");
    }

    public void showMark(Task currentTask) {
        System.out.println("\tSwee! I've marked this task as done loh:");
        System.out.println("\t" + currentTask);
    }

    public void showUnmark(Task currentTask) {
        System.out.println("\tOkay liar, I've marked this task as undone liao:");
        System.out.println("\t" + currentTask);
    }

    public void showError(String error) {
        System.out.println("\tERROR: " + error);
    }

    public void showGoodbye() {
        System.out.println("\tCiaos! See you next time.");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = "";
        while(command.equals("")) {
            command = sc.nextLine();
        }
        return command;
    }

}
