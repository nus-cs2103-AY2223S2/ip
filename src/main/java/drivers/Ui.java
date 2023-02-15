package drivers;

import java.util.Scanner;

import support.Instructions;
import task.TaskList;

/**
 * Deprecated. Converted to GUI.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private Parser parser = new Parser();

    /**
     * Manages CLI commands for Bond.
     */
    public Ui() {
        System.out.println("Echo! I'm Bond.");
        System.out.println("---------------");
        System.out.println(Instructions.generate());
    }

    /**
     * Continues to prompt user for inputs until it receives command to stop.
     *
     * @param l reference to a record of list of tasks
     */
    public void nextMission(TaskList l) {
        boolean isCompleted = true;

        while (isCompleted) {
            System.out.println("\n----- Awaiting command -----");
        }
    }
}
