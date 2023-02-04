package Duke;
import java.util.Scanner;

/**
 * Main driver class for Duke.
 * @author Bryan Juniano
 */

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner myObj = new Scanner(System.in);
        Saver saver = new Saver();
        Handler handler = new Handler();
        saver.load(taskList);

        while (true) {
            String input = myObj.nextLine();
            System.out.println(handler.processCommand(input, taskList));
            saver.save(taskList);
        }
    }
}
