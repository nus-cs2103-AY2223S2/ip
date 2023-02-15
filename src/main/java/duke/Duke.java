package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.helpers.*;

/**
 * Main Duke class file. Sets up UI and loads up saved tasks if any.
 * Continues querying the user until bye command is entered.
 *
 * @author jengoc415
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        UI ui = new UI(new TaskList());
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String instruction;

        while (!ui.isTerminated()) {
            instruction = sc.nextLine();
            ui.process(instruction);
        }
    }
}


