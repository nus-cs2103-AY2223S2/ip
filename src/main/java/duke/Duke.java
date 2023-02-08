package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a program that helps users keep track of their tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * constructor for Duke
     * 
     * @param filePath string for path-to-file to read/write list of tasks from/to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {}

    /**
     * starts the Duke program
     */
//    public void run() {
//        ui.greet();
//        Scanner scn = new Scanner(System.in);
//
//        while (scn.hasNext()) {
//            String userInput = scn.nextLine();
//
//            try {
//                Parser.parse(userInput, tasks);
//            } catch (DukeException e) {
//                System.out.println(e.toString());
//            }
//
//            try {
//                storage.save(tasks);
//            } catch (DukeException e) {
//                System.out.println(e.toString());
//            }
//
//            if (userInput.equals("bye")) {
//                break;
//            }
//
//        }
//
//        scn.close();
//
//    }

    public String getResponse(String input) {
        String response = "";
        if (!input.equals("bye")) {
            try {
                response = Parser.parse(input, tasks);
                storage.save(tasks);
            } catch (DukeException e) {
                response = e.toString();
            }
        } else {
            try {
                response = Parser.parse(input, tasks);
            } catch (DukeException e) {
                response = e.toString();
            }
        }
        return response;
    }
//    public static void main(String[] args) {
//        new Duke("./data/duke.txt").run();
//    }
}
