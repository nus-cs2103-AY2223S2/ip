package book;

import book.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <\n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    private static final String LINE =
            "________________________________________________________________________________";
    private static final String INDENT = "    ";
    private static final Scanner input = new Scanner(System.in);
    public String readCommand() {
        return input.nextLine();
    }
    public void showLine() {
        System.out.println(LINE);
    }
    public void showWelcome() {
        System.out.println("Good day! This is\n" + LOGO + "What may I help you with?");
    }
    public void showExit() {
        System.out.println("Bye! Pick up Book.Book again soon!");

    }
    public void showAdd(Task task) {
        System.out.println("Understood, adding\n" + INDENT + task + "\nto Book.Book.");
    }
    public void showDelete(Task task) {
        System.out.println("Acknowledged, striking the following task off of Book.Book's pages:\n"
                + INDENT + task);
    }
    public void showMark(Task task) {
        System.out.println("The following task has been marked complete:\n" + INDENT + task);
    }
    public void showUnmark(Task task) {
        System.out.println("The following task has been marked incomplete:\n" + INDENT + task);
    }
    public void showTaskListSize(TaskList list) {
        System.out.println(list.listSize() + " task(s) exist in Book.Book's pages.");
    }
    public void showList(TaskList list) {
        System.out.println("Here are the tasks stored in Book.Book:");
        System.out.print(list);
    }
    public void showError(String message) {
        System.out.println(message);
    }
}
