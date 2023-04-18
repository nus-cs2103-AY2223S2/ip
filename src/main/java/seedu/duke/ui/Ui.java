package seedu.duke.ui;

import seedu.duke.tasklist.TaskList;

import java.io.File;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static final String MESSAGE_LINE = "____________________________________________________________";
    private static final String MESSAGE_WELCOME = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Hewwo?!?1 I'm seedu.duke.Duke\nWhat c-can I do fow you?!?!";
    private static final String MESSAGE_LOAD_ERROR = "Loading Error";

    public void showTasks(TaskList tasks) {
        StringBuilder response = new StringBuilder("Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int current = i + 1;
            response.append(current + ". " + tasks.get(i) + "\n");
        }
        System.out.println(response);
    }

    public void showLoadComplete() {
        System.out.println("Load Completed. ");
    }

    public void showSaveComplete() {
        System.out.println("Save Completed. ");
    }

    public void showOverwrite() {
        System.out.println("Save file already exists, overwriting...");
    }

    public void showSaveFileCreated(String name) {
        System.out.println("Save file created: " + name);
    }

    public void showUnknownCommand() {
        System.out.println("Unknown command.");
    }

    public void showExit() {
        System.out.println("Bye.");
    }

    public void debugHere() {
        System.out.println("Debug Line");
    }

    public void debugFiles() {
        File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);
    }

    public void showAddTaskSuccess(String desc) {
        System.out.println("added: " + desc);
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void showMark(String desc) {
        System.out.println("Alright, I've marked this task as done!\n" + desc);
    }

    public void showUnmark(String desc) {
        System.out.println("Aight, I marked the task as not done.\n" + desc);
    }

    public void showLine() {
        System.out.println(MESSAGE_LINE);
    }

    public void showWelcome() {
        System.out.println(MESSAGE_WELCOME);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println(MESSAGE_LOAD_ERROR);
    }

    public void showDelete() {
        System.out.println("Removed");
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public Ui() {
        scanner = new Scanner(System.in);
    }
}
