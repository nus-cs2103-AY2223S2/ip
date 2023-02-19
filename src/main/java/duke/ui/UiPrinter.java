package duke.ui;

import duke.tasks.Task;

/**
 * A class for adding indentation and printing messages to fit Duke's style.
 */
public class UiPrinter {



    /**
     * Adds a new line to all the string in textArray, joins them then returns the final string.
     *
     * @param textArray
     */
    public static String addLineBreak(String... textArray) {
        String finalString = "";
        for (int i = 0; i < textArray.length; i++) {
            finalString += textArray[i] + "\n";
        }
        return finalString;
    }


    /**
     * Creates a message to signal that this task has been created and how many tasks are there in
     * the list.
     *
     * @param task
     * @param numTasks
     * @return
     */
    public static String getTaskCreationMessage(Task task, int numTasks) {
        return addLineBreak("Got it. I've added this task:", task.toString(),
                "Now you have " + numTasks + " tasks in the list.");
    }
}
