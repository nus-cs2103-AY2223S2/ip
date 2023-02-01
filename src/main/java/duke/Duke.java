package duke;

import duke.functions.Parser;
import duke.functions.Storage;
import duke.functions.Ui;

import java.util.Scanner;

/**
 * The main class that the Duke program will run on.
 * It includes the operation for starting up, receiving
 * and decoding user inputs and shut down.
 */
public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage("./ip-data/ip-data.txt");
        Scanner sc = new Scanner(System.in);
        ToDoList list;

        list = Duke.startUp(storage);
        Duke.input(sc, list);
        Duke.shutDown(storage, list);
    }

    private static ToDoList startUp(Storage storage) {
        try {
            Ui.welcomeMessage();
            return storage.load();
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    private static void shutDown(Storage storage, ToDoList list) {
        try {
            storage.save(list);
            Ui.exitMessage();
        } catch (Exception e) {

        }
    }

    private static void input(Scanner sc, ToDoList list) {
        boolean isProgramDone = false;
        while (!isProgramDone) {
            try {
                String[] inputs = Parser.handleInput(sc.nextLine(), " ", 2, 1);
                isProgramDone = Parser.handleCommand(inputs, list);
            } catch (Exception e) {
                Ui.errorMessage(e.getMessage());
            }
        }
    }
}
