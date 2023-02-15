package duke.utils;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Formatter for Duke to prepare Strings to output
 */
public class Formatter {

    public static String formatAddTask(Task newTask, int listSize) {
        return String.format("You have added: %s\n You have %d tasks in the list", newTask.toString(), listSize);
    }

    public static String formatDeleteTask(Task deletedTask, int listSize) {
        return String.format("You have deleted: %s\n You have %d tasks in the list", deletedTask.toString(), listSize);
    }

    public static String formatMarkTask(Task markedTask) {
        return String.format("You have marked: %s as done. WELL DONE.", markedTask.toString());
    }

    public static String formatUnmarkTask(Task unmarkedTask) {
        return String.format("You have marked: %s as undone. STAY ON TARGET.", unmarkedTask.toString());
    }

    /**
     * Formats task arraylist to an indexed list of tasks
     * @param tasks task list
     */
    public static String formatIndexedList(ArrayList<Task> tasks) {
        String[] output = new String[tasks.size()];
        int count = 1;
        for (Task t : tasks) {
            output[count - 1] = (count + ". " + t.toString());
            count++;
        }
        return formatMultipleMessages(output);
    }

    /** Shows a variable number of messages to user */
    public static String formatMultipleMessages(String... message) {
        StringBuilder output = new StringBuilder();
        for (String m : message) {
            output.append(m).append('\n');
        }
        return output.toString();
    }

}
