package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


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
    /*
    public static void main(String[] args) {
        Storage storage = new Storage("./ip-data/ip-data.txt");
        Scanner sc = new Scanner(System.in);
        ToDoList list;

        list = Duke.startUp(storage);
        Duke.input(sc, list);
        Duke.shutDown(storage, list);
    }*/

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
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
            Ui.exitMessage();
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
