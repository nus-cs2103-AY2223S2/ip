package duke;

import java.util.Objects;
import java.util.Scanner;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * A class for managing tasks
 */
public class Duke {
    public Duke() {}

    /**
     * Function to run the Duke CLI
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser();
        storage.populate(tl);
        ui.showIntro();
        while (true) {
            try {
                String str = sc.nextLine();
                System.out.println(str);
                Command command = parser.parse(str);
                if (Objects.equals(command, null)) {
                    break;
                }
                command.execute(tl, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

        }
        ui.showOutro();
    }

    /**
     * Main function to run Duke CLI
     *
     * @param args Input to main function
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
