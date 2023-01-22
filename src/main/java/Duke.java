import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Ui ui = new Ui();
    static Storage dukeSave = new Storage();
    static ArrayList<Task> taskList = dukeSave.loadTaskList();
    static Scanner userScan = new Scanner(System.in);
    static boolean loopEnd = false;

    public static void main(String[] args) {

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

