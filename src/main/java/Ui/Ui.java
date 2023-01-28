package Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Exceptions.DukeException;
import Tasks.Task;

public class Ui {
    protected static String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String OUTLINES = "____________________________________________________________";
    protected static String INTRODUCTION = "Hello! I'm Duke\nWhat can I do for you?";
    protected static String READ_LIST = "Here are the tasks in your list:";
    protected static String FAREWELL = "Bye. Hope to see you again soon!";
    protected static String DONE_TASK = "Nice! I've marked this task as done:";
    protected static String UNDONE_TASK = "Nice! I've marked this task as not done yet:";
    protected static String DELETED_TASK = "Noted. I've removed this task:";
    protected static String ADDED_TASK = "Got it. I've added this task:";

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Ui() {
        System.out.println(OUTLINES + "\n" + INTRODUCTION + "\n" + OUTLINES);
    }
    
    public String readCommand() throws IOException {
        return br.readLine();
    }

    public void showLine() {
        System.out.println(OUTLINES);
    }

    public void showList() {
        System.out.println(READ_LIST);
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showFarewell() {
        System.out.println(FAREWELL);
    }

    public void showMarkTask(Task task) {
        System.out.println(DONE_TASK + "\n" + task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println(UNDONE_TASK + "\n" + task);
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println(DELETED_TASK + "\n" + task + "\n" +  "Now you have " + size + " tasks in the list");
    }

    public void showAddTask(Task task, int size) {
        System.out.println(ADDED_TASK + "\n" + task + "\n" + "Now you have " + size + " tasks in the list");
    }
}
