/**
 * Project name: Duke
 * @author Tan Jun Da
 * Student Number: A0234893U
 */

package seedu.duke;

import seedu.duke.task.Task;

/**
 * Represents an Ui interface.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Welcome message.
     */
    public void welcome() {
        //Logo of Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
    }

    /**
     * Loading error message.
     */
    public void showLoadingError() {
        System.out.println("File not found");
        System.out.println("Creating temporary Task List.");
    }

    /**
     * Task Marked message.
     *
     * @param tasks The TaskList.
     * @param userParse The input from the user.
     */
    public void markDisplay(TaskList tasks, Parser userParse) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString());
    }

    /**
     * Task Unmarked message.
     *
     * @param tasks The TaskList.
     * @param userParse The input from the user.
     */
    public void unmarkDisplay(TaskList tasks, Parser userParse) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString());
    }

    /**
     * List message.
     *
     * @param tasks The TaskList.
     */
    public void list(TaskList tasks) {
        int numbering = 1;
        for (int i = 0; i < tasks.counter; i++) {
            System.out.println(numbering + ". " + tasks.tasksList.get(i).toString());
            numbering++;
        }
    }

    /**
     * Added Todo message.
     *
     * @param tasks The TaskList.
     */
    public void addTodoMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    /**
     * Added Deadline message.
     *
     * @param tasks The TaskList.
     */
    public void addDeadlineMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    /**
     * Added Event message.
     *
     * @param tasks The TaskList.
     */
    public void addEventMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    /**
     * Delete message.
     *
     * @param tasks The TaskList.
     * @param deleted The Task deleted.
     */
    public void deleteMessage(TaskList tasks, Task deleted) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted.toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    /**
     * Goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Found or not found matching tasks message.
     *
     * @param tasks The temporary TaskList for the find.
     */
    public void findMessage(TaskList tasks) {
        if (tasks.tasksList.isEmpty()) {
            System.out.println("Sorry! There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            list(tasks);
        }
    }
}
