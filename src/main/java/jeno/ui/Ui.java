package jeno.ui;

import jeno.task.Task;

/**
 * Class for Ui
 */
public class Ui {

    /**
     * Inform user that a new task has been added.
     * @param newTask New task.
     * @param taskCount Current number of tasks.
     * @param taskWord Task word.
     * @return Message to inform user that task has been added.
     */
    public static String addTaskMessage(Task newTask, int taskCount, String taskWord) {
        return ("Got it. I've added this task:\n   "
                + newTask
                + "\nNow you have " + taskCount + " " + taskWord + " in your list\n");
    }
}
