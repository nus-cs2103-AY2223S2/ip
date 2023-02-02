package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;
import duke.utils.TaskList;

public class Ui {
    //TODO: Reassess the value of this class

    private static Scanner sc = new Scanner(System.in);

    /**
     * Listens to and returns the user input.
     *
     * @return the String representing the user input.
     */
    public static String listen() {
        return sc.nextLine();
    }

}
