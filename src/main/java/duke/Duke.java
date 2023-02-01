package duke;

import duke.functions.Parser;
import duke.functions.Storage;
import duke.functions.Ui;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Storage storage = new Storage("./ip-data/Ip-data.txt");
        Scanner sc = new Scanner(System.in);
        ToDoList list;

        list = Duke.startUp(storage);
        Duke.input(sc, list);
        Duke.shutDown(storage, list);
    }

    private static ToDoList startUp(Storage storage) {
        try {
            Ui.welcomeMsg();
            return storage.load();
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    private static void shutDown(Storage storage, ToDoList list) {
        try {
            storage.save(list);
            Ui.exitMsg();
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
                Ui.errorMsg(e.getMessage());
            }
        }
    }
}
