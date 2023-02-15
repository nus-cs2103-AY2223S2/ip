package task;

import duncan.Ui;

import java.io.Serializable;

/**
 * Represent a task created by the user
 */
public class Task implements Serializable {

    private String text;
    private boolean marked;

    /**
     * Creates an empty Task
     */
    public Task() {
        this.text = "";
        marked = false;
    }

    /**
     * Creates a Task with the given task description
     * @param text the description of the task
     */
    public Task(String text) {
        this.text = text;
    }

    /**
     * Marks the current task with the given mark
     * @param mark a boolean value that if true, marks the task and if false, unmarks the task
     */
    public void markOut(boolean mark, Ui ui) {
        if (mark != marked) {
            marked = !marked;
            if (marked) {
                ui.addStatement("Alright, I've marked it out : \n" + this);
            } else {
                ui.addStatement("Alright, I've erased the mark: \n" + this);
            }
        } else {
            ui.addStatement("Hey how I can change this mark?");
        }
    }

    /**
     * Checks if the current task text contains the given substring
     * @param substring a String that may be contained in this task's text
     * @return the boolean value of whether this task's text contains the substring
     */
    public boolean hasSubstring(String substring) {
        return this.text.contains(substring);
    }

    /**
     * Checks if the current task text matches the given text
     * @param inputText the String that is to be checked
     * @return the boolean value of whether the input text matches with the task's text
     */
    public boolean isCorrectTask(String inputText) {
        return inputText.equals(this.text);
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + text;
        } else {
            return "[] " + text;
        }
    }
}
