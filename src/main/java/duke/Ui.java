package duke;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/***
 * A class that deals with interactions with the user
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initialises a new UI with a scanner to get user input
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /***
     * read command of user
     * @return input in the form of a string
     */
    public String readCommand() {
        String s = "";
        try {
            s = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.print(e.getMessage());
        }
        return s;
    }

    /**
     * prints greet message
     *
     * @return a string
     */
    public static String greet() {
        return "Hello! I'm Duke.\n" + "What can I do for you?";
    }

    /**
     * prints a farewell message
     *
     * @return a string
     */
    public String exit() {
        this.scanner.close();
        return "Bye. Hope to see you again soon!";
    }

    /***
     * prints loading error message
     */

    public void showLoadingError() {
        System.out.println("Loading Error Encountered");
    }

    /**
     * List the items in the list
     *
     * @param taskList
     * @return
     */
    public String printList(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return s;
    }

    /**
     * prints message after task is marked
     *
     * @param index
     * @param task
     * @return
     */
    public String printMark(int index, Task task) {
        String response = "Nice! I've marked this task as done:\n";
        response += index + ". " + task;
        return response;
    }

    /**
     * prints message after task is unmarked
     *
     * @param index
     * @param task
     * @return
     */
    public String printUnmark(int index, Task task) {
        String response = "OK, I've marked this task as not done yet:\n";
        response += index + ". " + task;
        return response;

    }

    /**
     * prints message after task is added to taskList
     *
     * @param size
     * @param task
     * @return
     */
    public String printAddTask(int size, Task task) {
        String response = "Got it. I've added this task: \n" + task + "\n";
        response += String.format("Now you have %s tasks in the list.\n", size);
        return response;

    }

    /**
     * prints message when task is deleted
     *
     * @param index
     * @param task
     * @param taskList
     */
    public String printDelete(int index, Task task, TaskList taskList) {
        String s = "Noted. I've removed this task: \n";
        s += index + ". " + task + "\n";
        s += String.format("Now you have %d tasks in the list.", taskList.size());
        return s;
    }

    /**
     * Prints the error message when Exception occurred
     *
     * @param s string that gets printed
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * prints a line
     *
     * @return a string
     */
    public String showLine() {
        return "____________________________________________________________________________";
    }

    /**
     * prints tasks that has matching keywords
     *
     * @param res
     * @return
     */
    public String printFind(ArrayList<Task> res) {
        String s = "Here are the matching tasks in your list: \n";
        for (int j = 0; j < res.size(); j++) {
            s += (j + 1) + ". " + res.get(j) + "\n";
        }
        return s;
    }

    /**
     * Prints a duplicate if found
     *
     * @param task
     * @return
     */
    public String printDuplicates(Task task) {
        return String.format("Duplicate task detected\n 1. %s", task.toString());
    }
}