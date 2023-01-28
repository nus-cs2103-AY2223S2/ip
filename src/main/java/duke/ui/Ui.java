package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Prints messages with desired formatting.
 *
 * @author Lian Kok Hai
 */
public class Ui {
    private int maxCharPerLine = 100;
    private String horizontal;

    /**
     * Constructs default Ui object with maxCharPerLine = 100.
     */
    public Ui() {
        this.horizontal = "_".repeat(this.maxCharPerLine + 4);
    }

    /**
     * Constructs custom Ui object.
     *
     * @param maxCharPerLine Maximum number of characters per line printed.
     */
    public Ui(int maxCharPerLine) {
        this.maxCharPerLine = maxCharPerLine;
        this.horizontal = "_".repeat(maxCharPerLine + 4);
    }

    /**
     * Prints formatted string - encloses string in box.
     *
     * @param str Input string.
     */
    public void print(String str) {
        String[] lines = str.split("\n");
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            if (line.length() > maxCharPerLine) {
                int numNewLines = (int) Math.ceil(line.length() / maxCharPerLine);
                String[] newLines = new String[numNewLines];
                for (int i=0; i < numNewLines; i++) {
                    if (i < numNewLines - 1) {
                        newLines[i] = line.substring(100 * i, 100 * i + 1);
                    } else {
                        newLines[i] = line.substring(100 * i);
                    }
                }
                result.addAll(Arrays.asList(newLines));
            } else {
                int padCount = maxCharPerLine - line.length();
                String padding = " ".repeat(padCount);
                result.add(line + padding);
            }
        }
        System.out.println(horizontal);
        result.stream().map(x -> "| " + x + " |").forEach(x -> System.out.println(x));
        System.out.println(horizontal);
    }

    /**
     * Prints exit message.
     */
    public void printExit() {
        String exitMessage = "Bye! Hope to see you again soon!";
        print(exitMessage);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcome() {
        String welcomeMessage = "Hello! I'm duke.Duke \nWhat can I do for you";
        print(welcomeMessage);
    }

    /**
     * Prints add task message.
     * @param task New task to be added.
     * @param taskCount Number of tasks after new task is added.
     */
    public void printAddTaskMessage(Task task, int taskCount) {
        String addTaskMessage = "Got it. I've added this task:\n     " + task + "\nNow you have " + taskCount + " tasks in the list";
        print(addTaskMessage);
    }

    /**
     * Prints delete task message.
     * @param task Task to be deleted.
     * @param taskCount Number of tasks after task is deleted.
     */
    public void printDeleteTaskMessage(Task task, int taskCount) {
        String deleteTaskMessage = "Noted. I've removed this task:\n     " + task + "\nNow you have " + taskCount + " tasks in the list";
        print(deleteTaskMessage);
    }

    /**
     * Prints mark task message.
     *
     * @param task Task to be marked as done.
     */
    public void printMarkTaskMessage(Task task) {
        print("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Prints unmark task message.
     *
     * @param task Task to be marked as undone.
     */
    public void printUnmarkTaskMessage(Task task) {
        print("OK, I've marked this task as not done yet: \n" + task);
    }

}
