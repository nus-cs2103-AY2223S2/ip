import java.util.Scanner;

import duke_exception.DukeException;
import tasklist.TaskList;
import utility.parser.CommandMap;
import utility.parser.Parser;
import utility.storage.Storage;
import utility.ui.Ui;
import utility.ui.UiMessage;

/**
 * <h1>Duke Chatbot</h1>
 * The Duke chatbot is a bot that is capable to keep
 * track of tasks from the users.
 * 
 * @author Brian Quek
 */

public class Duke {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Ui ui = new Ui();
        Ui.welcomeMessage();
        TaskList list = Storage.readData();
        UiMessage response = new UiMessage();

        while (response.TYPE != CommandMap.bye) {
            try {
                response = Parser.readCommand(inputScanner.nextLine(), list);
                ui.load(response, list);
            } catch (DukeException e) {
                System.out.println(e);
            }

            Storage.writeData(list);
        }

        inputScanner.close();
    }
}