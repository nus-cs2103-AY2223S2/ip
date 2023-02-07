package duke;

import java.util.Scanner;

/**
 * Main class
 */
public class Duke {

    /**
     * Main method
     */
    public static void main(String[] args) throws DukeExceptions {
        Ui.intro();
        Storage.createDataDir();
        TaskList tasks = new TaskList(Storage.load());
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while (myObj.hasNext()) {
            String input = myObj.nextLine();
            tasks.handleInput(input);
        }
    }
}
