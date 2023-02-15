package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Duke  {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {

    }
    public Duke(String filepath) throws IOException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    public String run() {
        return this.ui.welcome();
//        Scanner userInputObj = new Scanner(System.in);
//        String userInput = "";
//        Parser parser = new Parser();
//        while (!userInput.equals("bye")) {
//            userInput = userInputObj.nextLine();
//            parser.parse(userInput, tasks, ui, storage);
//        }
    }

    public String feedingIntoInterface(String userInput) throws DukeException, FileNotFoundException {
        return this.parser.parse(userInput, tasks, ui, storage);
//        return "test";
    }

    public static void main(String[] args) throws DukeException, IOException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke("data/tasks.txt");
    }
}
