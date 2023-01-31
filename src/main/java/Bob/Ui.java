package Bob;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private int spacing;

    private String wrapper;

    public Ui(int spacing, String deco, int length) {
        this.spacing = spacing;
        this.wrapper = padLeft(deco.repeat(length));
    }

    private String padLeft (String s) {
        return " ".repeat(spacing) + s;
    }

    private String getTaskDescription(Task t) {
        return String.format("[%s][%s] %s", t.getTaskType(), t.getStatusIcon(), t);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void errorPrint(BobException e) {
        formattedPrint("Sorry! An error has occured :(\n"
            + e.getMessage());
    }

    // Accepts string that can be separated by \n
    private void formattedPrint(String s) {
        String[] lines = s.split("\n");
        System.out.println(wrapper);
        for (String line : lines) {
            System.out.println(padLeft(line));
        }
        System.out.println(wrapper);
    }

    public void printList(TaskList taskList) {
        System.out.println(wrapper);
        System.out.println(padLeft("Current task list: "));

        ArrayList<Task> list = taskList.getList();
        // Iterate through list items sequentially
        for (int i = 0, n = list.size(); i < n; i++) {
            Task t = list.get(i);
            String s = String.format("%d. %s", i + 1, getTaskDescription(t));
            System.out.println(padLeft(s));
        }
        System.out.println(wrapper);
    }

    public void printIntroduction() {
        formattedPrint("Hi, my name is Bob :)\n" +
        "How may I help you?");
    }

    public void printTaskAdded(Task t) {
        formattedPrint("Successfully added a new task :)\n" +
                getTaskDescription(t));
    }

    public void printMarkTask(Task t) {
        formattedPrint("Successfully marked a task :)\n" +
                getTaskDescription(t));
    }

    public void printUnmarkTask(Task t) {
        formattedPrint("Successfully unmarked a task :)\n" +
                getTaskDescription(t));
    }
    public void printDeleteTask(Task t) {
        formattedPrint("Successfully deleted a task :)\n" +
                getTaskDescription(t));
    }
    public void printGoodbye() {
        formattedPrint("Goodbye :)");
    }
}
