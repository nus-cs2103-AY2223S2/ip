package Ui;

import Task.Task;

import java.util.ArrayList;

public class Ui {
    boolean isStarted;

    public Ui() {
        isStarted = true;
        startUpSequence();
    }

    public void startUpSequence() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Error loading file, proceeding to rewrite the file");
    }

    public void showAddedMessage(Task item) {
        System.out.println("    Duke says:");
        System.out.println("    Added");
        System.out.println("    " + item.toString());
    }

    public static void showRemovedMessage(Task item) {
        System.out.println("    Noted. I have removed this task:");
        System.out.println("    " + item.toString());
    }

    public static void showMarkedMessage(Task item) {
        System.out.println("    OK I've marked this task as done: ");
        System.out.println("    " + item.toString());
    }

    public static void showUnmarkedMessage(Task item) {
        System.out.println("    OK I've marked this task as not done yet:");
        System.out.println("    " + item.toString());
    }

    public void showSavedMessage() {
        System.out.println("    Save complete");
    }

    public void showClosingMessage() {
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }

    public void printListNumber(ArrayList<Task> list) {
        System.out.println("    Now you have " + list.size() + " task(s) in the list.");
    }

    public void showSavingMessage() {
        System.out.println("    Saving:");
    }
}
