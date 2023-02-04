package duke;

import java.util.Scanner;
import java.lang.String;

import duke.Commands.Parser;
import duke.Commands.Command;
import duke.Commands.Exit;
import duke.DukeException.DukeException;

/**
 * @author Shi Jiaao
 *
 * Welcome to Duke
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        Storage storage = new Storage("data");
        TaskList toDoList;
        try {
            toDoList = storage.initialise();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            Parser parser = new Parser(command);
            Command curCommand;
            try {
                curCommand = parser.process();
                curCommand.execute(toDoList);
                ui.printCommandMessage(curCommand);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            if (curCommand instanceof Exit) {
                ui.printGoodbye();
                storage.update(toDoList);
                break;
            }
        }
        sc.close();
    }
}
