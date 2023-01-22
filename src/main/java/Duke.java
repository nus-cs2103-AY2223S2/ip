import Features.DukeException;
import Features.Storage;
import Features.TaskList;
import Features.Ui;

import java.util.Scanner;

public class Duke {

    static Storage dukeSave = new Storage();
    static TaskList taskList = dukeSave.loadTaskList();
    static boolean loopEnd = false;

    public static void main(String[] args) {

        // welcome message
        new Ui().welcome();
        // initialise Scanner
        Scanner userScan = new Scanner(System.in);

        // while LoopEnd = true loop to accept user input
        while (!loopEnd) {
            // try block to catch DukeException and prevent program from terminating itself.
            try {
                new Parser().parse(userScan);
            }
            // Catches DukeException if thrown and prevents program from terminating.
            catch(DukeException ex){
                    // error message can be formatted in many possible ways beforehand, so println is used.
                    System.out.println(ex.printErrorMessage());
                }
            }

    }
}

