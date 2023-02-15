import java.util.Scanner;

import commands.Command;
import dukeexceptions.DukeException;
import elems.Parser;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

/**
 * The main program that runs the task list chatbot Duke
 * @author clydelhui
 */
public class Duke {

    /**
     * The main method
     * @param args nil
     */
    public static void main(String[] args) {


        Scanner usrInput = new Scanner(System.in);
        Ui ui = new Ui(usrInput);

        ui.welcome();

        Storage storage = new Storage("data.txt", ui);

        TaskList taskList = new TaskList(storage, ui);

        Parser parser = new Parser();

        String input = "";

        while (true) {
            input = ui.getInput();
            try {
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.errorDisplay(e);
            }
        }

    }
}
