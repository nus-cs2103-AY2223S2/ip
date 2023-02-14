package genie.main;

import genie.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private StringBuilder response;
    /**
     * A constructor for Ui class.
     */
    public Ui() {
        response = new StringBuilder();
    }

    /**
     * Prints greet message.
     */
    public void appendGreetingMessage() {
        response.append("Hello! This is Genie, your personal task tracker!\n\n");
        response.append("To get started, here is a list of commands that I recognise:\n");
        response.append(showAllCommands());
    }

    /**
     * Prints task list if it is not empty. Otherwise, prints an empty task message.
     * @param tasks
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            response.append("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                String numberedTaskInfo = appendNumberedTaskInfo(i, t);
                response.append(numberedTaskInfo);
            }
        } else {
            showEmptyListMessage();
        }
    }

    /**
     * Prints mark done message.
     * @param t task to be marked done
     */
    public void showMarkDoneMessage(Task t) {
        response.append("Nice! I've marked this task as done:\n" + "  " + t.toString() + "\n");
    }

    /**
     * Prints add task message.
     * @param t task to be added
     * @param size number of tasks in the list
     */
    public void appendAddTaskMessage(Task t, int size) {
        response.append("Got it. I've added this task:\n  " + t.toString() + "\n");
        response.append("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints unmark done message.
     * @param t task to be unmarked done
     */
    public void appendUnmarkDoneMessage(Task t) {
        response.append("Okay, I've marked this task as not done yet:\n" + "  " + t.toString() + "\n");
    }
    /**
     * Prints delete task message.
     * @param t task to be deleted
     * @param size number of tasks in the list
     */
    public void showDeleteTaskMessage(Task t, int size) {
        response.append("Noted. I've removed this task:\n" + "  " + t.toString() +
                "\nNow you have " + size + " tasks in the list.\n");
    }
    /**
     * Prints add farewell message.
     */
    public void showFarewellMessage() {
        response.append("Bye. Hope to see you again soon!\n");
    }

    /**
     * Returns general error message.
     */
    public void showErrorMessage() {
        response.append("Something went wrong here xx...\n");
    }
    /*
    public void printLoadedTaskList(ArrayList<String> tl) {
        if (tl.isEmpty()) {
            showEmptyListMessage();
        } else {
            response.append("Here is a record of your task list from where you had previously left off:\n");
            for (int i = 0; i < tl.size(); i++) {
                response.append("  " + tl.get(i) + "\n");
            }
            response.append("\nNow, what can I do for you?\n");
    }
    */

    /**
     * Prints empty list message.
     */
    public void showEmptyListMessage() {
        response.append("Your task list is currently empty! Let's get started! ^-^\n");
    }
    public void printMatchingTaskList(ArrayList<Task> tasks) {
        if(tasks.size() > 0) {
            response.append("Here are the matching tasks in your list:\n");
            for(int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                String numberedTaskInfo = appendNumberedTaskInfo(i, t);
                response.append(numberedTaskInfo);
            }
        } else {
            showEmptyMatchingTasksMessage();
        }
    }
    public void showEmptyMatchingTasksMessage() {
        response.append("There are no matching tasks for your search :(\n");
    }
    public void appendHelpMessage() {
        response.append("No problem! Here is a list of commands I can recognise:\n");
        response.append("\n");
        response.append(showAllCommands());
    }
    public String getResponse() {
        return response.toString();
    }
    public void clearResponse() {
        response.setLength(0);
    }
    public String appendNumberedTaskInfo(int index, Task t) {
        String numberedTaskInfo = index + ". " + t.toString() + "\n";
        return numberedTaskInfo;
    }
    public String showAllCommands() {
        String allCommands = "* todo <task>\n" +
                "* event <task> /from <time> /to <time>\n" +
                "* deadline <task> /by <time>\n" +
                "    - <time> (optional): YYYY-MM-DD HH:MM\n" +
                "\n" +
                "* delete <list number>\n" +
                "    - deletes that task on the list\n" +
                "* mark <list number>\n" +
                "    - marks that task as done\n" +
                "* unmark <list number>\n" +
                "    - marks that task as undone\n" +
                "* list\n" +
                "    - shows your consolidated task list\n" +
                "* find <keyword>\n" +
                "    - finds all tasks with the specified keyword\n" +
                "* help\n" +
                "    - shows help page";
        return allCommands;
    }
}
