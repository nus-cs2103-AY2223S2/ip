import commands.Command;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {


        Scanner usrInput = new Scanner(System.in);
        Ui ui = new Ui(usrInput);

        ui.welcome();

        Storage storage = new Storage("data.txt", ui);

        TaskList taskList = new TaskList(storage);

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
