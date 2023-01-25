package drivers;

import task.TaskList;

import java.util.Scanner;

/**
 * Main program user sees and interacts with.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private Parser parser= new Parser();

    public Ui() {
        System.out.println("Echo! I'm Bond.");
        System.out.println(".....");
    }

    /**
     * Continues to prompt user for inputs until it receives command to stop.
     *
     * @param l reference to a record of list of tasks
     */
    public void nextMission(TaskList l) {
        boolean check = true;

        while (check) {
            System.out.println("Awaiting command");
            check = this.parser.continueCommand(sc.nextLine(), l);
        }
    }
}
