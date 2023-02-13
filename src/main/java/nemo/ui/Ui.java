package nemo.ui;

import java.util.ArrayList;
import java.util.Arrays;

import nemo.task.Task;

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
                for (int i = 0; i < numNewLines; i++) {
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
     * Get welcome message.
     *
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nemo \nWhat can I do for you?";
        return welcomeMessage;
    }
    /**
     * Get exit message.
     *
     * @return Exit message
     */
    public String getExitMessage() {
        String exitMessage = "Bye! Hope to see you again soon!";
        return exitMessage;
    }

    /**
     * Get add task message.
     * @param task New task to be added.
     * @param taskCount Number of tasks after new task is added.
     * @return Add task message.
     */
    public String getAddTaskMessage(Task task, int taskCount) {
        String addTaskMessage = "Got it. I've added this task:\n     " + task //
                + "\nNow you have " + taskCount + " tasks in the list";
        return addTaskMessage;
    }

    /**
     * Get delete task message.
     * @param task Task to be deleted.
     * @param taskCount Number of tasks after task is deleted.
     * @return Delete task message.
     */
    public String getDeleteTaskMessage(Task task, int taskCount) {
        String deleteTaskMessage = "Noted. I've removed this task:\n     " + task //
                + "\nNow you have " + taskCount + " tasks in the list";
        return deleteTaskMessage;
    }

    /**
     * Get mark task message.
     *
     * @param task Task to be marked as done.
     * @return Mark task message.
     */
    public String getMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Get unmark task message.
     *
     * @param task Task to be marked as undone.
     * @return Unmark task message.
     */
    public String getUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Get undo message.
     *
     * @param didUndo Boolean representing successful undo.
     * @return Undo message.
     */
    public String getUndoMessage(boolean didUndo) {
        return didUndo ? "Okay, I have undone your previous change" : "There is nothing to be undone";
    }

    /**
     * Get redo message.
     *
     * @param didRedo Boolean representing successful redo.
     * @return Redo message.
     */
    public String getRedoMessage(boolean didRedo) {
        return didRedo ? "Sure, I have redone your previous change" : "There is nothing to redo";
    }

}
