package seedu.duke;

import java.util.Scanner;

/**
 * Ui object class.
 */
public class Ui {
    /** Scanner to take user input */
    private Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String userCommand() {
        String s = this.sc.nextLine();
        return s;
    }

    /**
     * Returns text to display on bot initialization.
     */
    public void spawnBot() {
        line();
        greet();
        line();
    }

    /**
     * Returns text to display on bot exit.
     */
    public void displayExit() {
        line();
        bye();
        line();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @param taskList the TaskList to return the number of tasks from.
     */
    public void displayTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            line();
            System.out.println("You have no tasks. Hooray!");
            line();
        } else {
            int index = 1;
            String list = "";
            while (index < (taskList.size() + 1)) {
                list = list + "\n   " + index + ". " + taskList.get(index - 1).toString();
                index++;
            }
            line();
            System.out.println("Here are your tasks:" + list);
            checkList(taskList);
            line();
        }
    }

    /**
     * Returns text when a task is marked.
     *
     * @param taskList the TaskList to mark the task.
     * @param index    the index of the task to be marked.
     */
    public static void displayMarked(TaskList taskList, int index) {
        if (taskList.get(index).isDone) {
            line();
            System.out.println("You've already marked this task as done.");
            line();
        } else {
            line();
            System.out.println("Alright, marking this task as done:");
            taskList.get(index).markAsDone();
            indent("" + taskList.get(index));
            line();
        }
    }

    /**
     * Returns text when a task is unmarked.
     *
     * @param taskList the TaskList to unmark the task.
     * @param index    the index of the task to be unmarked.
     */
    public static void displayUnmarked(TaskList taskList, int index) {
        if (!taskList.get(index).isDone) {
            line();
            System.out.println("You've already marked this task as not done yet.");
            line();
        } else {
            line();
            System.out.println("Alright, marking this task not done yet:");
            taskList.get(index).markAsUndone();
            indent("" + taskList.get(index));
            line();
        }
    }

    /**
     * Returns text when a task is deleted.
     *
     * @param taskList the TaskList to delete the task from.
     * @param index    the index of the task to be removed.
     */
    public static void displayDelete(TaskList taskList, int index) {
        line();
        System.out.println("Noted, removing this task:");
        indent("" + taskList.get(index));
        taskList.remove(index);
        line();
    }

    /**
     * The welcome text.
     */
    // Greet format
    public static void greet() {
        System.out.println("HEY! I'm GRUMMY!\nHow can I help you?");
    }

    /**
     * The exit text.
     */
    // Bye format
    public static void bye() {
        System.out.println("Goodbye! Hope to see you again :>");
    }

    /**
     * Lines for readability.
     */
    // Create lines
    public static void line() {
        System.out.println("-------------------------");
    }

    /**
     * Indents the current line.
     *
     * @param output String of the response to be indented.
     */
    // Create indents
    public static void indent(String output) {
        System.out.println("    " + output);
    }

    /**
     * Checks the grammar of the TaskList return.
     *
     * @param taskList the TaskList to check.
     */
    public static void checkList(TaskList taskList) {
        if (taskList.size() == 1) {
            System.out.println("You have 1 task in the list.");
        } else if (taskList.size() == 0) {
            System.out.println("You have no tasks in the list.");
        } else {
            System.out.println("You have " + taskList.size() + " tasks in the list.");
        }
    }


    /**
     * The text when a task is added.
     */
    public static void addedTask() {
        System.out.println("Got it. I've added this task:");
    }
}
