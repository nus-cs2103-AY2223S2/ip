package duke.ui;

import duke.tasks.Task;

public class Ui {

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    // private static final String NEW_LINE = "\n";

    private static void printBar() {
        System.out.println(BAR);
    }

    private static void printWithIndentation(String text) {
        System.out.println(INDENTATION + text);
    }

    /**
     * Prints the welcome message for Duke.
     */
    public static void greet() {
        prettyPrint("Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * Prints the newly added task.
     * 
     * @param task
     * @param numTasks
     */
    public static void printAddTask(Task task, int numTasks) {
        prettyPrint("Got it. I've added this task:", task.toString(),
                "Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints the desired messages by indenting them and putting them in between bars.
     * 
     * @param textArray
     */
    public static void prettyPrint(String... textArray) {
        printBar();
        for (int i = 0; i < textArray.length; i++) {
            printWithIndentation(textArray[i]);
        }
        printBar();
    }
}
