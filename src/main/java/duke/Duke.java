package duke;

import duke.functions.Parser;
import duke.functions.Storage;
import duke.functions.UI;

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
        ToDoList ls;

        ls = Duke.startUp(storage);
        Duke.input(sc, ls);
        Duke.shutDown(storage, ls);
    }

    private static ToDoList startUp(Storage storage) {
        try {
            UI.welcomeMessage();
            return storage.load();
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    private static void shutDown(Storage storage, ToDoList ls) {
        try {
            storage.save(ls);
            UI.exitMessage();
        } catch (Exception e) {

        }
    }

    private static void input(Scanner sc, ToDoList ls) {
        boolean isDone = false;
        while (!isDone) {
            try {
                String[] input = Parser.inputHandler(sc.nextLine(), " ", 2, 1);
                isDone = Parser.commandHandler(input, ls);
            } catch (Exception e) {
                UI.errorMessage(e.getMessage());
            }
        }
    }
}
